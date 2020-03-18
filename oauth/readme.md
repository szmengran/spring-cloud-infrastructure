#Oauth2 Client配置
1.通过maven引入对应的jar

```yaml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```

2.ResourceServe配置

```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${spring.security.resourceId}")
	private String resourceId;
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .requestMatchers()
        // 配置需要保护的资源路径，下面配置的是保护所有资源
        .antMatchers("/**");
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    	resources.resourceId(resourceId);
    }
}

```

3.在需要验证权限的方法加注释

```java
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/scope")
    public Principal scope(Principal user){
    	return user;
    }
    
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/preAuthorize")
	public Principal preAuthorize(Principal user){
			return user;
	}
    
    @Secured("('ADMIN')")
    @GetMapping("/secured")
    public Principal secured(Principal user){
    	return user;
    }
```

#密码模式请求
```shell
curl -XPOST api:api@localhost:10002/api/v1/oauth2/oauth/token -d "grant_type=password&username=admin&password=12345"
```

#客户端模式请求
```shell
curl -XPOST api:api@localhost:10002/api/v1/oauth2/oauth/token -d "grant_type=client_credentials"
```
* 获取到的token如下

```json
{"access_token":"1daf6c31-eee5-4032-9906-5dda033a3bf8","token_type":"bearer","expires_in":3599,"scope":"read write"}
```

* 通过token请求服务

```shell
curl -H 'Authorization: Bearer 2f2b934d-66be-43f2-8e33-47028802fdfd' http://localhost:10041/api/v1/email/scope
```
