package com.szmengran.security.oauth.domain.repo;

import com.szmengran.security.oauth.domain.entity.OauthRole;

import java.util.List;

/**
 * @Description 角色仓库
 * @Date 2020/5/24 1:58 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
public interface RoleRepository {

    /**
     * @Description 根据userid获取角色
     *
     * @Param [userid]
     * @Return java.util.List<com.suntak.security.oauth.domain.entity.OauthRole>
     * @Date 2:00 下午 2020/5/24
     * @Author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<OauthRole> getByUserid(String userid);
}
