package com.szmengran.mybatis.utils.reflect;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

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
	 */
	public static Map<String, Method> getFieldAndSetMethodFromObject(Class<?> cla) throws NoSuchMethodException, SecurityException {  
		Map<String, Method> map = ClassInfoCache.getFieldAndSetMethodFromObject(cla);
		if (map != null) {
			return map;
		} else {
			synchronized (ReflectHandler.class) {
				map = ClassInfoCache.getFieldAndSetMethodFromObject(cla);
				if (map == null) {
					map = new HashMap<String, Method>();
					for (Class<?> clazz = cla; clazz != Object.class; clazz = clazz.getSuperclass()) {  
			            try {  
			            	Field[] fields = clazz.getDeclaredFields();
			        		for (Field field : fields) {  
			        			String name = field.getName();
			        			if ("SerialVersionUID".equalsIgnoreCase(name)) { //通过在运行时判断类的serialVersionUID来验证版本的一致性，不与实体表对应
			        				continue;
			        			}
			                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  clazz);  
			                    Method getMethod = pd.getWriteMethod();//获得set方法  
			                    map.put(name, getMethod); 
			                } 
			            } catch (Exception e) {  
			                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
			                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
			              e.printStackTrace();
			            }  
			        }  
			        ClassInfoCache.putFieldAndSetMethodToObject(cla, map);
				}
			}
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
	 */
	public static Map<String, Method> getFieldAndGetMethodFromObject(Class<?> cla) throws NoSuchMethodException, SecurityException {  
		Map<String, Method> map = ClassInfoCache.getFieldAndGetMethodFromObject(cla);
		if (map != null) {
			return map;
		} else {
			synchronized (ReflectHandler.class) {
				map = ClassInfoCache.getFieldAndGetMethodFromObject(cla);
				if (map == null) {
					map = new HashMap<String, Method>();
					for(Class<?> clazz = cla; clazz != Object.class; clazz = clazz.getSuperclass()) {  
			            try {  
			            	Field[] fields = clazz.getDeclaredFields();
			        		for (Field field : fields) {  
			        			String name = field.getName();
			        			if ("SerialVersionUID".equalsIgnoreCase(name)) { //通过在运行时判断类的serialVersionUID来验证版本的一致性，不与实体表对应
			        				continue;
			        			}
			                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  clazz);  
			                    Method setMethod = pd.getReadMethod();//获得get方法  
			                    map.put(name, setMethod); 
			                } 
			            } catch (Exception e) {  
			                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
			                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
			              e.printStackTrace();
			            }  
			        }  
					ClassInfoCache.putFieldAndGetMethodToObject(cla, map);
				}
			}
		}
		return map;
	}
	
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
			for(Class<?> clazz = cla; clazz != Object.class; clazz = clazz.getSuperclass()){
				Field[] fields=clazz.getDeclaredFields();
				for(Field field: fields){
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
	
	/**
	 * 给对象赋值
	 * @param object
	 * @param setMethod
	 * @param strValue
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static void setValue(Object object, Method setMethod, String strValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
		Type[] ts = setMethod.getGenericParameterTypes(); 
		//只要一个参数 
		String xclass = ts[0].toString(); 
		//判断参数类型 
		if (StringUtils.isBlank(strValue)) {
			return;
		} else if (xclass.equals("class java.lang.String")) { 
			setMethod.invoke(object, strValue); 
		} else if (xclass.equals("class java.util.Date")) { 
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (strValue.length() == 10) {
				strValue = strValue+" 00:00:00";
			}
			setMethod.invoke(object, sf.parse(strValue)); 
		} else if (xclass.equals("class java.sql.Timestamp")) { 
			if (strValue.length() == 10) {
				strValue = strValue+" 00:00:00";
			} else if (strValue.length() == 16) {
				strValue = strValue+":00";
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			setMethod.invoke(object, new Timestamp(sf.parse(strValue).getTime())); 
		} else if (xclass.equals("class java.lang.Boolean")) { 
			Boolean boolname = true; 
			if (strValue.equals("false")) { 
				boolname = false; 
			} 
			setMethod.invoke(object,boolname ); 
		} else if (xclass.equals("class java.lang.Integer") || xclass.equals("int")) 	{ 
			setMethod.invoke(object,new Integer( strValue)); 
		} else if (xclass.equals("class java.lang.Long")) { 
			setMethod.invoke(object,new Long( strValue)); 
		} else if (xclass.equalsIgnoreCase("class [Ljava.lang.Double;")){
			String values[] = strValue.replace("[", "").replace("]", "").split(",");
			Double dValues[] = new Double[values.length];
			for (int i = 0; i < values.length; i++) {
				if (!(values[i] == null || values[i] == "")) {
					dValues[i] = new Double(values[i].trim());
				} else {
					dValues[i] = null;
				}
			}
			setMethod.invoke(object, (Object)dValues);
		} else if (xclass.toLowerCase().contains("double")) { 
			setMethod.invoke(object,new Double( strValue)); 
		} else if (xclass.equalsIgnoreCase("class [Ljava.lang.String;")){
			String values[] = strValue.replace("[", "").replace("]", "").split(",");
			for (int i = 0; i < values.length; i++) {
				values[i] = values[i].trim();
			}
			setMethod.invoke(object, (Object)values);
		} else {
			throw new ClassCastException("找不到匹配的字段类型，请检查对象类型是否匹配！");
		}
	}
}

