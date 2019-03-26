package com.szmengran.mybatis.utils.reflect;

import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Test;

import com.szmengran.mybatis.utils.PageInfo;

/** 
 * @Description: TODO
 * @Package com.szmengran.mybatis.utils.reflect 
 * @CreateTime Mar 22, 2019 4:06:30 PM 
 * @Author <a href="mailto:android_li@sina.cn">Joe</a> 
 */
public class ReflectHandlerTest {

    @Test
    public void testGetFieldAndSetMethodFromObject() throws NoSuchMethodException, SecurityException, IntrospectionException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ReflectHandler.getAllFields(PageInfo.class);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Map<String, Method> map = null;
        for (int i = 0; i < 100; i++) {
            map = ReflectHandler.getFieldAndGetMethodFromObject(PageInfo.class);
        }
        map.forEach((key, method) -> System.out.println(key+"---"+method.toString()));
        System.out.println(System.currentTimeMillis() - start);
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            map = ReflectHandler.getFieldAndSetMethodFromObject(PageInfo.class);
        }
        map.forEach((key, method) -> System.out.println(key+"---"+method.toString()));
        System.out.println(System.currentTimeMillis() - start);
    }
}
