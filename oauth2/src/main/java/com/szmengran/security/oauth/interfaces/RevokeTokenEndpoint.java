package com.szmengran.security.oauth.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Resource
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    /**
     * 注销token
     * @param access_token
     * @return
     */
    @DeleteMapping("/oauth/token")
    @ResponseBody
    public String revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)){
            return "注销成功";
        }else{
            return "注销失败";
        }
    }
}
