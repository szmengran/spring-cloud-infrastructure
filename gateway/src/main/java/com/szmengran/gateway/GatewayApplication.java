package com.szmengran.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Package com.szmengran.cloud.gateway
 * @Description: ZUUL api gateway
 * @date 2018年3月27日 下午3:37:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RefreshScope
public class GatewayApplication {
	
	public static void main(String args[]) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
