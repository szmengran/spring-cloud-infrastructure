package com.szmengran.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.szmengran.mybatis.utils.mapper.IMapper;
import com.szmengran.security.entity.User;

@Mapper
public interface UserMapper extends IMapper<User> {

	/**
	 * 
	 * @description 根据用户名称查询用户信息
	 * @param username
	 * @return
	 * @date Mar 6, 2020 2:45:41 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Select("select * from user.t_user where username=#{username}")
	User findByUsername(String username);
}
