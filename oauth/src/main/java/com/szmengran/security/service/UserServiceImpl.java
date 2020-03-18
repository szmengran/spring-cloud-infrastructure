package com.szmengran.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.szmengran.security.entity.Role;
import com.szmengran.security.entity.User;
import com.szmengran.security.mapper.UserMapper;

/**
 * 
 * @description 用户服务
 * @package com.szmengran.security.service 
 * @date Mar 6, 2020 1:03:09 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userMapper.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("user [" + username + "] do not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roles.add(role);
        role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        return new User(user, roles);
    }
}
