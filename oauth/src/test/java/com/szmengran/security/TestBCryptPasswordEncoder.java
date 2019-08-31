package com.szmengran.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

    @Test
    public void cryptTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("abc");
        Assert.assertTrue(bCryptPasswordEncoder.matches("abc", encode));
    }
}
