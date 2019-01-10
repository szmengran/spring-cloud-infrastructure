package com.szmengran.mybatis.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package com.szmengran.mybatis.utils
 * @Description: table annotation
 * @date Oct 30, 2018 10:28:50 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Table {

	/**
	 * setting primary key
	 * The combined primary keys are separated by commas
	 * @return
	 */
	String id() default "";
}
