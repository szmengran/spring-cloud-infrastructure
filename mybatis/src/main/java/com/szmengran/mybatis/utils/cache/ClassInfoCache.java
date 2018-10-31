package com.szmengran.mybatis.utils.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ClassInfoCache {
	@SuppressWarnings("unchecked")
	public static Map<String,Method> getFieldAndGetMethodFromObject(Object object){
		return (Map<String,Method>)DataCache.get(object.getClass().getName()+"_FGM");
	}
	public static void putFieldAndGetMethodToObject(Object object, Map<String,Method> map){
		DataCache.put(object.getClass().getName()+"_FGM", map);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Method> getFieldAndSetMethodFromObject(Object object){
		return (Map<String,Method>)DataCache.get(object.getClass().getName()+"_FSM");
	}
	public static void putFieldAndSetMethodToObject(Object object, Map<String,Method> map){
		DataCache.put(object.getClass().getName()+"_FSM", map);
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Field> getAllFieldsFromObject(Object object){
		return (Set<Field>)DataCache.get(object.getClass().getName()+"_F");
	}
	public static void putAllFieldsToObject(Object object, Set<Field> set){
		DataCache.put(object.getClass().getName()+"_F", set);
	}
	
}
