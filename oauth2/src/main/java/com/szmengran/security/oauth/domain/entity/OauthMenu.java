package com.szmengran.security.oauth.domain.entity;

import java.sql.Timestamp;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单表(OauthMenu)实体类
 *
 * @author makejava
 * @since 2020-05-23 16:41:58
 */
@Data
@ApiModel("菜单表")
public class OauthMenu implements Serializable {

    private static final long serialVersionUID = 709426664518855253L;
    
    /**
    * 菜单ID
    */    
    @ApiModelProperty("菜单ID")
    private Double menuid;
    
    /**
    * 父菜单
    */    
    @ApiModelProperty("父菜单")
    private Double pid;
    
    /**
    * 菜单名
    */    
    @ApiModelProperty("菜单名")
    private String name;
    
    /**
    * 路径
    */    
    @ApiModelProperty("路径")
    private String url;
    
    /**
    * 层级
    */    
    @ApiModelProperty("层级")
    private Double level;
    
    /**
    * 图标
    */    
    @ApiModelProperty("图标")
    private String icon;
    
    /**
    * 排序
    */    
    @ApiModelProperty("排序")
    private Double display;
    
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
    
    /**
    * 更新时间
    */    
    @ApiModelProperty("更新时间")
    private Timestamp updatestamp;
    
    /**
    * 创建人
    */    
    @ApiModelProperty("创建人")
    private String createBy;
    
}