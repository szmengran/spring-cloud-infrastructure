package com.szmengran.mybatis.utils.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ClassInfoCache {
    
    private static final String GET_METHOD_PREFIX = "1_";
    private static final String SET_METHOD_PREFIX = "2_";
    private static final String FIELD_PREFIX = "3_";
    
    @SuppressWarnings("unchecked")
    public static Map<String,Method> getFieldAndGetMethodFromObject(Class<?> cla) {
        return (Map<String,Method>)DataCache.get(GET_METHOD_PREFIX+cla.getName());
    }
    
    public static void putFieldAndGetMethodToObject(Class<?> cla, Map<String,Method> map) {
        DataCache.put(GET_METHOD_PREFIX+cla.getName(), map);
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String,Method> getFieldAndSetMethodFromObject(Class<?> cla) {
        return (Map<String,Method>)DataCache.get(SET_METHOD_PREFIX+cla.getName());
    }
    
    public static void putFieldAndSetMethodToObject(Class<?> cla, Map<String,Method> map) {
        DataCache.put(SET_METHOD_PREFIX+cla.getName(), map);
    }
    
    @SuppressWarnings("unchecked")
    public static Set<Field> getAllFieldsFromObject(Class<?> cla) {
        return (Set<Field>)DataCache.get(FIELD_PREFIX+cla.getName());
    }
    
    public static void putAllFieldsToObject(Class<?> cla, Set<Field> set) {
        DataCache.put(FIELD_PREFIX+cla.getName(), set);
    }
    
}
