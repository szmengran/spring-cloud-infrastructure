# spring cloud基础架构搭建
使用spring boot 2.0.4, spring cloud, spring oauth2等技术实现微服务

# 应用架构
* [cloud security](https://github.com/szmengran/cloud/tree/master/cloud-security) Oauth2认证服务器
* [cloud gateway](https://github.com/szmengran/cloud/tree/master/cloud-gateway) API网关
* [cloud discovery](https://github.com/szmengran/cloud/tree/master/cloud-discovery) eureka服务注册中心
* [cloud config](https://github.com/szmengran/cloud/tree/master/cloud-config) 配置中心
* [cloud trace] 使用sleuth&zipkin组件实现分布式链路
* [cloud ELK] 所有的日志文件发送的kafka，再有logbash将日志信息从kafka中读取到ES
* [cloud ribbon] 通过ribbon组件实现负载均衡

# 编译&运行

```yml
cd docker
```
* 环境变量配置介绍

```yml
ENVIRONMENT=dev/test/prod  #在本地配置环境变量
CLOUD_VERSION=0.0.1 #生成docker image的版本号设置
GITLAB_USERNAME=xxx #config server中git库的用户名
GITLAB_PASSWORD=xxx #config server中git库的密码
CONFIG_SERVER_ADDRESS=http://localhost:10001 #config server地址配置
EUREKA_SERVER_ADDRESS=http://localhost:8761/eureka #注册中心地址配置
KAFKA_SERVER=localhost:9092 #kafka服务地址配置
```

* mac或Linux系统下编译全部服务

```yml
sh build-docker/build-all.sh
docker image ls #查看当前生成的所有镜像
```


* 运行服务

```yml
docker-compose -f docker-compose.sh up -d
docker container ls #查看容器启动情况
docker container logs ad43deeb2543 #根据容器ID查看某个容器的日志
```

* 停止服务

```yml
docker-compose down
```