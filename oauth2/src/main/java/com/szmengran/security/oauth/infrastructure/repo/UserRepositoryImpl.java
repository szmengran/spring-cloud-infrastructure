package com.szmengran.security.oauth.infrastructure.repo;

import com.szmengran.security.oauth.domain.entity.OauthUser;
import com.szmengran.security.oauth.domain.repo.UserRepository;
import com.szmengran.security.oauth.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Description 用户仓库
 * @Date 2020/5/24 1:54 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public OauthUser getByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
