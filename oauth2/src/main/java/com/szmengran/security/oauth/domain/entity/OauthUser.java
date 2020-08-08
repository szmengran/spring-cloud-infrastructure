package com.szmengran.security.oauth.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class OauthUser implements UserDetails, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2638346706750990711L;
    private String userid;
    private String username;
    private String password;
    private Integer validstatus;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @Description 设置角色
     *
     * @Param [roles]
     * @Return void
     * @Date 1:46 下午 2020/5/24
     * @Author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    public void setRoles(List<OauthRole> roles) {
        this.authorities = translate(roles);
    }

    private Collection<? extends GrantedAuthority> translate(List<OauthRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (OauthRole role : roles) {
            String name = role.getName().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

