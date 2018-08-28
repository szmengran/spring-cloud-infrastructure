# spring cloud实战项目
使用spring boot, spring cloud, spring oauth2等技术实现微服务

# 应用架构
* [cloud security](https://github.com/szmengran/cloud/tree/master/cloud-security) Oauth2认证服务器
* [cloud gateway](https://github.com/szmengran/cloud/tree/master/cloud-gateway) API网关
* [cloud discovery](https://github.com/szmengran/cloud/tree/master/cloud-discovery) eureka服务注册中心
* [cloud config](https://github.com/szmengran/cloud/tree/master/cloud-config) 配置中心

# 编译&运行

```yml
cd docker
```

* mac或Linux系统下编译全部服务

```yml
sh build-docker/build-all.sh
docker image ls #查看当前生成的所有镜像
```
* 运行服务

```yml
docker-compose -f deploy-all.sh up -d
docker container ls #查看容器启动情况
docker container logs ad43deeb2543 #根据容器ID查看某个容器的日志
docker-compose -f deploy-all.sh up -d #由于其它服务的启动都是基于eureka和config服务，所以需要再执行一下该命令才能启动其它服务
```

  