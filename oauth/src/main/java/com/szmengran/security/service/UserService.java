package com.szmengran.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.szmengran.security.client.UserClient;
import com.szmengran.security.entity.CustomUserDetails;
import com.szmengran.security.entity.User;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserClient userClient;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userClient.loadUserByUsername(username);
			if (user == null) {
        		throw new UsernameNotFoundException("user [" + username + "] do not exist!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new CustomUserDetails(user);
    }
}
