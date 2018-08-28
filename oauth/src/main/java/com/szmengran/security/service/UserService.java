package com.szmengran.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.szmengran.common.orm.dao.mysql.MySqlDao;
import com.szmengran.security.entity.CustomUserDetails;
import com.szmengran.security.entity.Role;
import com.szmengran.security.entity.User;

public class UserService extends MySqlDao implements UserDetailsService {
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		String lowcaseUsername = username.toLowerCase();
        StringBuffer sql = new StringBuffer(" select userid,username,password from t_power_user where username=?");
        Object[] params = new Object[1];
        params[0] = username;
        List<User> list = null;
		try {
			list = super.findBySql(User.class, sql.toString() , params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (list == null || list.size()==0) {
        		throw new UsernameNotFoundException("用户" + lowcaseUsername + "不存在!");
        }
        User user = (User) list.get(0);
        StringBuffer roleSql = new StringBuffer();
        roleSql.append("select rolename name from t_power_role where roleid in (")
        		   .append(" select roleid from t_power_user_role_r where userid=?)");
        List<Role> roleList = null;
        Object roleParams[] = new Object[1];
        roleParams[0] = user.getUserid();
		try {
			roleList = super.findBySql(Role.class, roleSql.toString() , roleParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setRoles(roleList);
        return new CustomUserDetails(user);
    }
}
