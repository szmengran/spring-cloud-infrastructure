package com.szmengran.security.oauth.domain.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色表(OauthUserRoleR)实体类
 *
 * @author makejava
 * @since 2020-05-23 16:41:47
 */
@Data
@ApiModel("用户角色表")
public class OauthUserRoleR implements Serializable {

    private static final long serialVersionUID = -78730344868567557L;
    
    /**
    * 角色主键
    */    
    @ApiModelProperty("角色主键")
    private Double roleid;
    
    /**
    * 用户主键
    */    
    @ApiModelProperty("用户主键")
    private String userid;
    
}