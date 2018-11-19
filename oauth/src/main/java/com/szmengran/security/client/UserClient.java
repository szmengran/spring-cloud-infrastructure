package com.szmengran.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.szmengran.security.entity.User;

/**
 * @Package com.szmengran.security.client
 * @Description: user service
 * @date Nov 19, 2018 1:46:07 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "user")
public interface UserClient {
	
	/**
	 * get user info by username
	 * @param username
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value = "/api/v1/user/{username}")
	public User loadUserByUsername(@PathVariable("username") String username) throws Exception;
}
