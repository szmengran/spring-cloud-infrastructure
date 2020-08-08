package com.szmengran.security.oauth.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szmengran.security.oauth.domain.entity.OauthRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 角色持久化操作
 * @Date 2020/5/24 1:28 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
@Mapper
public interface RoleMapper extends BaseMapper<OauthRole> {
    
    /**
     * @Description 根据userid获取角色
     *
     * @Param []
     * @Return java.util.List<com.suntak.security.oauth.domain.entity.OauthRole>
     * @Date 1:31 下午 2020/5/24
     * @Author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @Select("select a.* from oauth_role a, oauth_user_role_r b where a.roleid = b.roleid and b.userid = #{userid}")
    List<OauthRole> findByUserid(String userid);

}
