package com.szmengran.security.oauth.domain.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (OauthRefreshToken)实体类
 *
 * @author makejava
 * @since 2020-05-23 16:41:55
 */
@Data
@ApiModel("$tableInfo.comment")
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = -21764594332102648L;
    
        
    @ApiModelProperty("$column.comment")
    private String tokenId;
    
        
    @ApiModelProperty("$column.comment")
    private String token;
    
        
    @ApiModelProperty("$column.comment")
    private String authentication;
    
}