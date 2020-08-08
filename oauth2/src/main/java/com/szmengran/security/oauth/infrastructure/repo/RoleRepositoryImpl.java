package com.szmengran.security.oauth.infrastructure.repo;

import com.szmengran.security.oauth.domain.entity.OauthRole;
import com.szmengran.security.oauth.domain.repo.RoleRepository;
import com.szmengran.security.oauth.infrastructure.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 角色仓库
 * @Date 2020/5/24 1:59 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<OauthRole> getByUserid(String userid) {
        return roleMapper.findByUserid(userid);
    }

}
