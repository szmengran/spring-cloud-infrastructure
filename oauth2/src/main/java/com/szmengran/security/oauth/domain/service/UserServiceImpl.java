package com.szmengran.security.oauth.domain.service;

import com.szmengran.security.oauth.domain.entity.OauthRole;
import com.szmengran.security.oauth.domain.entity.OauthUser;
import com.szmengran.security.oauth.domain.repo.RoleRepository;
import com.szmengran.security.oauth.domain.repo.UserRepository;
import com.szmengran.security.oauth.infrastructure.utils.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @description 用户服务
 * @package com.szmengran.security.service 
 * @date Mar 6, 2020 1:03:09 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OauthUser oauthUser = null;
        oauthUser = userRepository.getByUsername(username);
        if (oauthUser == null) {
            throw new UsernameNotFoundException("user [" + username + "] do not exist!");
        }
        if (oauthUser.getValidstatus() != UserStatus.VALID.getValue()) {
            throw new IllegalArgumentException("当前用户无效，请联系管理员！");
        }
        List<OauthRole> roles = roleRepository.getByUserid(oauthUser.getUserid());
        oauthUser.setRoles(roles);
        return oauthUser;
    }
}
