package com.szmengran.mybatis.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.thoughtworks.xstream.core.SequenceGenerator;

/**
 * @Package com.szmengran.mybatis.utils
 * @Description: data generate value
 * @date Jan 10, 2019 9:41:56 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface GeneratedValue {
	
	/**
     * (Optional) The primary key generation strategy
     * that the persistence provider must use to
     * generate the annotated entity primary key.
     */
    GenerationType strategy() default GenerationType.AUTO;
	
	/**
     * (Optional) The name of the primary key generator
     * to use as specified in the {@link SequenceGenerator} 
     * or {@link TableGenerator} annotation.
     * <p> Defaults to the id generator supplied by persistence provider.
     */
    String generator() default "";
}
