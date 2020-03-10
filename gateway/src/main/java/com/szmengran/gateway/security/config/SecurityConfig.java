package com.szmengran.gateway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @description SpringSecurity配置，允许授权相关路径的访问及表单登录
 * @package com.szmengran.security.config 
 * @date Mar 6, 2020 1:36:22 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.requestMatchers().anyRequest()
    	.and()
    	.authorizeRequests().antMatchers("/oauth/*").permitAll();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                                   "/swagger-resources/**",
                                   "/swagger-ui.html"
                                   );
    }
    
    // 不定义没有password grant_type
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
