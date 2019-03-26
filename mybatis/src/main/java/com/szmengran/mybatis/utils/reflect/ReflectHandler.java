package com.szmengran.mybatis.utils.reflect;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.szmengran.mybatis.utils.cache.ClassInfoCache;

/**
 * @Copyright Copyright (c) 2012
 * @Company 深圳梦燃科技有限公司（www.szmengran.com）
 * @author ★<a href="mailto:android_li@sina.cn">LiMaoYuan</a> 
 * @since  2013-12-20 上午09:07:54
 */
public class ReflectHandler {
    
    /**
     * 
     * @Description: 生成给定类的属性的字段和属性字段对应的set方法
     * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
     * @date 2016年10月26日 下午7:25:37 
     * 
     * @param cla
     * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws IntrospectionException 
     */
    public static Map<String, Method> getFieldAndSetMethodFromObject(Class<?> cla) throws NoSuchMethodException, SecurityException, IntrospectionException {  
        Map<String, Method> map = ClassInfoCache.getFieldAndSetMethodFromObject(cla);
        if (map != null) {
            return map;
        } 
        synchronized (ReflectHandler.class) {
            map = ClassInfoCache.getFieldAndSetMethodFromObject(cla);
            if (map != null) {
                return map;
            }
            map = new HashMap<String, Method>();
            Set<Field> fields = getAllFields(cla);
            for (Field field : fields) {  
                String name = field.getName();
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  cla);  
                Method getMethod = pd.getWriteMethod();//获得set方法  
                map.put(name, getMethod); 
            } 
            ClassInfoCache.putFieldAndSetMethodToObject(cla, map);
        }
        return map;  
    }
    
    /**
     * 
     * @Description: 获取类的字段属性和get方法
     * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
     * @date 2016年10月26日 下午7:24:57 
     * 
     * @param cla
     * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws IntrospectionException 
     */
    public static Map<String, Method> getFieldAndGetMethodFromObject(Class<?> cla) throws NoSuchMethodException, SecurityException, IntrospectionException {  
        Map<String, Method> map = ClassInfoCache.getFieldAndGetMethodFromObject(cla);
        if (map != null) {
            return map;
        }
        synchronized (ReflectHandler.class) {
            map = ClassInfoCache.getFieldAndGetMethodFromObject(cla);
            if (map != null) {
                return map;
            }
            map = new HashMap<String, Method>();
            Set<Field> fields = getAllFields(cla);
            for (Field field : fields) {
                String name = field.getName();
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  cla);  
                Method setMethod = pd.getReadMethod();//获得get方法  
                map.put(name, setMethod);  
            }
            ClassInfoCache.putFieldAndGetMethodToObject(cla, map);
        }
        return map;
    }
    
    /**
     * 获取对象的所有属性
     * @param cla
     * @return 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    public static Set<Field> getAllFields(Class<?> cla) {
        Set<Field> set = ClassInfoCache.getAllFieldsFromObject(cla);
        if(set != null){
            return set;
        }
        synchronized (ReflectHandler.class) {
            set = ClassInfoCache.getAllFieldsFromObject(cla);
            if(set != null){
                return set;
            }
            set = new HashSet<Field>();
            for (Class<?> clazz = cla; clazz != Object.class; clazz = clazz.getSuperclass()){
                Field[] fields=clazz.getDeclaredFields();
                for (Field field: fields) {
                    if ("SerialVersionUID".equalsIgnoreCase(field.getName())) { //通过在运行时判断类的serialVersionUID来验证版本的一致性，不与实体表对应
                        continue;
                    }
                    set.add(field);
                }
            }
            ClassInfoCache.putAllFieldsToObject(cla, set);
        }
        return set;
    }
}

