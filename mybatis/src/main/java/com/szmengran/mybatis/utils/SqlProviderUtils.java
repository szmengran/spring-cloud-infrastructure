package com.szmengran.mybatis.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.szmengran.mybatis.utils.reflect.ReflectHandler;

/**
 * @Package com.szmengran.power.util
 * @Description: TODO
 * @date Oct 30, 2018 9:01:37 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class SqlProviderUtils {
	
	/**
	 * insert a row of data to table
	 * @param object
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String insert(Object object) throws Exception {
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		return new SQL() {
			{
				INSERT_INTO(tableName);
				Set<Field> fields = ReflectHandler.getAllFields(beanClass);
				for (Field field: fields) {
					String name = field.getName();
					Annotation[] annotation = field.getAnnotations();
					if (annotation.length > 0) {
						GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
						switch (generatedValue.strategy()) {
							case IDENTITY:
								break;
							case SEQUENCE:
								VALUES(name, generatedValue.generator()+".NEXTVAL");
								break;
							default:
								VALUES(name, "#{"+name+"}");
								break;
						}
					} else {
						VALUES(name, "#{"+name+"}");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * insert batch row of data to table
	 * @param map
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String insertBatch(Map<String, List<Object>> map) throws Exception {
		List<Object> list = map.get("list");
		Object object = list.get(0);
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		
		String sql = new SQL() {
			{
				INSERT_INTO(tableName);
				Set<Field> fields = ReflectHandler.getAllFields(beanClass);
				for (Field field: fields) {
					String name = field.getName();
					Annotation[] annotation = field.getAnnotations();
					if (annotation.length > 0) {
						GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
						switch (generatedValue.strategy()) {
							case IDENTITY:
								break;
							case SEQUENCE:
								VALUES(name, generatedValue.generator()+".NEXTVAL");
								break;
							default:
								VALUES(name, "#{list[{0}]."+name+"}");
								break;
						}
					} else {
						VALUES(name, "#{list[{0}]."+name+"}");
					}
				}
			}
		}.toString();
		
		StringBuilder strSql = new StringBuilder(sql);
		MessageFormat messageFormat = new MessageFormat(strSql.toString());
		for (int i = 0; i < list.size(); i++) {
			strSql.append(" (").append(messageFormat.format(new Object[]{i})).append("),");
		}
		strSql.deleteCharAt(strSql.length()-1);
		
		return strSql.toString();
	}
	
	/**
	 * delete data by conditions
	 * @param tableName
	 * @param params
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String deleteByConditions(Map<String, Object> map) throws Exception{
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)map.get("params");
		@SuppressWarnings("rawtypes")
		String tableName = ((Class)map.get("class")).getSimpleName();
		if (params == null || params.size() == 0) {
			throw new Exception("do not allow full table delete!");
		}
		Set<String> fields = params.keySet();
		String strSql = new SQL(){
            {
            	DELETE_FROM(tableName);
                for (String field: fields) {
                	WHERE(field+" = #{params."+field+"}");
                }
            }
        }.toString();
        
        return strSql;
	}
	
	/**
	 * delete data by primary key
	 * @param bean
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String delete(Object bean) throws Exception{
		Class<?> beanClass = bean.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.id();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("The object "+beanClass.getName()+" has not yet she the primary key, can not use this method to delete data!");
		}
		return new SQL() {
			{
				DELETE_FROM(tableName);
				String keys[] = strKeys.split(",");
				for (String key: keys) {
					WHERE(key+"="+"#{"+key+"}");
				}
			}
		}.toString();
	}
	
	/**
	 * find data by conditions
	 * @param map
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String findByConditions(Map<String, Object> map) throws Exception {
		@SuppressWarnings("rawtypes")
		String tableName = ((Class)map.get("class")).getSimpleName();
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)map.get("params");
		String orderBy = (String)map.get("orderBy");
		SQL sql = new SQL() {
			{
				SELECT("*");
				FROM(tableName);
				if (params != null && params.size() > 0) {
					Set<String> fields = params.keySet();
					for (String field: fields) {
						WHERE(field + " = #{params." + field + "}");
		            }
				}
			}
		};
		StringBuilder strSql = new StringBuilder(sql.toString());
		if (StringUtils.isNotBlank(orderBy)) {
			strSql.append(" ").append(orderBy);
		}
		return strSql.toString();
	}
	
	/**
	 * find data by sql and conditions
	 * @param map
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String findBySql(Map<String, Object> map) throws Exception {
		String strSql = (String)map.get("strSql");
		
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)map.get("params");
		StringBuilder conditions = new StringBuilder();
		if (params != null && params.size() > 0) {
			Set<String> fields = params.keySet();
			for (String field: fields) {
				conditions.append(" and ").append(field).append(" = #{params.").append(field).append("}");
            }
		} 
		strSql = strSql+conditions.toString();
		return strSql;
	}
	
	/**
	 * find data by primary key
	 * @param object
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String findById(Object object) throws Exception {
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.id();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("The object "+beanClass.getName()+" has not yet she the primary key, can not use this method to find data!");
		}
		
		return new SQL() {
			{
				SELECT("*");
				FROM(tableName);
				String keys[] = strKeys.split(",");
				for (String key: keys) {
					WHERE(key+"="+"#{"+key+"}");
				}
			}
		}.toString();
	}
	
	/**
	 * update data by primary key
	 * @param object
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String update(Object object) throws Exception{
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.id();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("The object "+beanClass.getName()+" has not yet she the primary key, can not use this method to up data!");
		}
		return new SQL() {
			{
				UPDATE(tableName);
				Set<Field> fields = ReflectHandler.getAllFields(beanClass);
				String keys[] = strKeys.split(",");
				for (Field field: fields) {
					String name = field.getName();
					Boolean flag = true;
					for (String key: keys) {
						if (key.equalsIgnoreCase(name)) { //主键不用更新
							WHERE(key+"="+"#{"+key+"}");
							flag = false;
						}
					}
					if (flag) {
						SET(name, "#{"+name+"}");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * execute sql
	 * @param strSql
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String execute(@Param("strSql") String strSql) throws Exception {
		return strSql;
	}
}
