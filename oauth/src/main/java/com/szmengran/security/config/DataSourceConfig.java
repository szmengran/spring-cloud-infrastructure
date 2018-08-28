package com.szmengran.security.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Package com.szmengran.security.config
 * @Description: 数据源配置
 * @date 2018年4月10日 下午4:05:23
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@Order(-100)
public class DataSourceConfig {

    @SuppressWarnings("rawtypes")
	@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        @SuppressWarnings("unchecked")
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,swagger-ui.html*");
        return filterRegistrationBean;
    }
    
	@Bean(initMethod = "init", name = "readDataSource")  
    @ConfigurationProperties(prefix = "spring.datasource.druid.common.read")  
    public DataSource readDataSource(){  
        return DruidDataSourceBuilder.create().build();  
    }  
  
    @Bean(initMethod = "init", name = "writeDataSource")  
    @ConfigurationProperties(prefix = "spring.datasource.druid.common.write")  
    public DataSource writeDataSource(){  
            return DruidDataSourceBuilder.create().build();  
    } 
}
