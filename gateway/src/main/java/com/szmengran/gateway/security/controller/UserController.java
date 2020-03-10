package com.szmengran.gateway.security.controller;

import java.security.Principal;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Client
@RestController
public class UserController {
    /**
     * 系统中设置了只有api开头的请求才能被处理
     * @param user
     * @return      
     * @return: Principal      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    
    /**
     * 系统中设置了只有api开头的请求才能被处理
     * @return      
     * @return: String      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @GetMapping("/users")
    public String user(){
        return "limaoyuan";
    }
    
    @GetMapping("/api/users")
    public Principal users(Principal user){
            return user;
    }
}
