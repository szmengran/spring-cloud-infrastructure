package com.szmengran.gateway.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 
 * @description 资源服务器配置
 * @package com.szmengran.security.config 
 * @date Mar 6, 2020 1:18:48 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .requestMatchers()
        // 配置需要保护的资源路径
        .antMatchers("/api/**");
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("api");
    }
}
