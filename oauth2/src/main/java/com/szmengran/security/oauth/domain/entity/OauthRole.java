package com.szmengran.security.oauth.domain.entity;

import java.sql.Timestamp;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色表(OauthRole)实体类
 *
 * @author makejava
 * @since 2020-05-23 16:41:57
 */
@Data
@ApiModel("角色表")
public class OauthRole implements Serializable {

    private static final long serialVersionUID = 631440371615153490L;
    
    /**
    * 角色主键
    */    
    @ApiModelProperty("角色主键")
    private Double roleid;
    
    /**
    * 角色名称
    */    
    @ApiModelProperty("角色名称")
    private String name;
    
    /**
    * 备注
    */    
    @ApiModelProperty("备注")
    private String remark;
    
    /**
    * 状态（0-无效，1-有效）
    */    
    @ApiModelProperty("状态（0-无效，1-有效）")
    private Double validstatus;
    
    /**
    * 创建时间
    */    
    @ApiModelProperty("创建时间")
    private Timestamp createstamp;
    
}