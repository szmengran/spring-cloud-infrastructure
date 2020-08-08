package com.szmengran.security.oauth.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szmengran.security.oauth.domain.entity.OauthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<OauthUser> {

	/**
	 * 
	 * @description 根据用户名称查询用户信息
	 * @param username
	 * @return
	 * @date Mar 6, 2020 2:45:41 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from oauth_User where username=#{username}")
	OauthUser findByUsername(String username);
}
