package com.szmengran.security.oauth.interfaces;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/oauth/user")
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
    
    @GetMapping("/user")
    public Principal users(Principal user){
            return user;
    }
    
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/scope")
    public Principal scope(Principal user){
        return user;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/preAuthorize")
    public Principal preAuthorize(Principal user){
            return user;
    }
    
    @Secured("('ADMIN')")
    @GetMapping("/secured")
    public Principal secured(Principal user){
        return user;
    }
}
