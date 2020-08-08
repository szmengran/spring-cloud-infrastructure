package com.szmengran.security.oauth.infrastructure.utils;

/**
 * @Description 用户状态
 * @Date 2020/6/11 1:42 下午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
public enum UserStatus {

    DISABLED(1, "禁用"),
    VALID(1, "有效");

    private Integer value;
    private String name;

    UserStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
