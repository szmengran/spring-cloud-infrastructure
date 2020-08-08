package com.szmengran.security.oauth.domain.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色菜单表(OauthRoleMenuR)实体类
 *
 * @author makejava
 * @since 2020-05-23 16:41:54
 */
@Data
@ApiModel("角色菜单表")
public class OauthRoleMenuR implements Serializable {

    private static final long serialVersionUID = -15724621362352453L;
    
    /**
    * 菜单表_菜单ID
    */    
    @ApiModelProperty("菜单表_菜单ID")
    private Double menuid;
    
    /**
    * 角色主键
    */    
    @ApiModelProperty("角色主键")
    private Double roleid;
    
}