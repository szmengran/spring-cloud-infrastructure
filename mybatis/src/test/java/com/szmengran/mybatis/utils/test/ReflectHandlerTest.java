package com.szmengran.mybatis.utils.test;
/**
 * @Package com.szmengran.mybatis.utils.test
 * @Description: TODO
 * @date Nov 2, 2018 9:53:47 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.gson.Gson;
import com.szmengran.mybatis.utils.reflect.ReflectHandler;

public class ReflectHandlerTest {
	
	@Test
	public void testGetFieldAndSetMethodFromObject() {
		System.out.println("=========================");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i=0; i<4; i++) {
			executor.submit(() -> {
				try {
					Map<String, Method> map = ReflectHandler.getFieldAndGetMethodFromObject(Person.class);
					System.out.println(new Gson().toJson(map));
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
	
	public static void main(String args[]) {
		System.out.println("=========================");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i=0; i<3; i++) {
			executor.submit(() -> {
				try {
					Map<String, Method> map = ReflectHandler.getFieldAndGetMethodFromObject(Person.class);
					System.out.println(new Gson().toJson(map));
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
