package com.szmengran.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package com.suntak.cloud.questionnaire.config
 * @Description: 过滤器
 * @date 2018年4月20日 下午6:48:29
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
public class FilterConfig {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean myOncePerRequestFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CorsFilter());
		registration.addUrlPatterns("/*");// 拦截路径
		registration.setName("CorsFilter");// 拦截器名称
		registration.setOrder(2);// 顺序
		return registration;
	}

}
