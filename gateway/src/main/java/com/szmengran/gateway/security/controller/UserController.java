package com.szmengran.gateway.security.controller;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/api/users1")
    public Principal users1(Principal user){
    	return user;
    }
    
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/api/users2")
    public Principal users2(Principal user){
    	return user;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/users3")
    public Principal users3(Principal user){
    	return user;
    }
    
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/api/users33")
	public Principal users33(Principal user){
			return user;
	}
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api/users4")
    public Principal users4(Principal user){
    	return user;
    }
    
    @Secured("('ADMIN')")
    @GetMapping("/api/users44")
    public Principal users44(Principal user){
    	return user;
    }
}
