package com.szmengran.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

    @Test
    public void cryptTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("Suntak@123");
        System.out.println(encode);
        Assert.assertTrue(bCryptPasswordEncoder.matches("12345", encode));
    }
}
