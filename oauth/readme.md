#密码模式请求
```shell
curl -u api:api -XPOST localhost:10002/api/v1/oauth2/oauth/token -d "grant_type=password&username=admin&password=12345"
```

#客户端模式请求
```shell
curl -u api:api -XPOST localhost:10002/api/v1/oauth2/oauth/token -d "grant_type=client_credentials"
```