package com.szmengran.security.oauth.domain.repo;

import com.szmengran.security.oauth.domain.entity.OauthUser;

/**
 * @Description 用户仓库
 * @Date 2020/5/24 1:51 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
public interface UserRepository {

    /**
     * @Description 根据用户名获取用户信息
     *
     * @Param [username]
     * @Return com.suntak.security.oauth.domain.entity.OauthUser
     * @Date 1:54 下午 2020/5/24
     * @Author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    OauthUser getByUsername(String username);
}
