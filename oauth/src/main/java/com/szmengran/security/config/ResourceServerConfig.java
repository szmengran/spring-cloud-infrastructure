package com.szmengran.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//            .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .antMatchers("/css/**","/img/**","/login/**","/oauth/**").permitAll()
//                .anyRequest().authenticated()
//            .and()
//	           .formLogin().loginPage("/login")
//	           .defaultSuccessUrl("/delegate/success", true)
//	           .failureUrl("/login/fail")
//	               .permitAll()
//	        .and()
//	          .logout()
//	           .logoutUrl("/logout")
//	           .logoutSuccessUrl("/login")                                  
//	           .permitAll()
//            .and()
//                .httpBasic();
        http.requestMatchers().antMatchers("/api/**")
        .and()
        .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')")
        .antMatchers("/api/**").authenticated();
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("cloud");
    }
}
