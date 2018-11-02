package com.szmengran.mybatis.utils.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ClassInfoCache {
	@SuppressWarnings("unchecked")
	public static Map<String,Method> getFieldAndGetMethodFromObject(Class<?> cla) {
		return (Map<String,Method>)DataCache.get(cla.getName()+"_FGM");
	}
	public static void putFieldAndGetMethodToObject(Class<?> cla, Map<String,Method> map) {
		DataCache.put(cla.getName()+"_FGM", map);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Method> getFieldAndSetMethodFromObject(Class<?> cla) {
		return (Map<String,Method>)DataCache.get(cla.getName()+"_FSM");
	}
	public static void putFieldAndSetMethodToObject(Class<?> cla, Map<String,Method> map) {
		DataCache.put(cla.getName()+"_FSM", map);
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Field> getAllFieldsFromObject(Class<?> cla) {
		return (Set<Field>)DataCache.get(cla.getName()+"_F");
	}
	public static void putAllFieldsToObject(Class<?> cla, Set<Field> set) {
		DataCache.put(cla.getName()+"_F", set);
	}
	
}
