package com.szmengran.mybatis.utils;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szmengran.mybatis.utils.reflect.ReflectHandler;

/**
 * @Package com.szmengran.power.util
 * @Description: TODO
 * @date Oct 30, 2018 9:01:37 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class SqlProviderUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlProviderUtils.class);

	public String insert(Object object) throws Exception {
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		int value = table.value();
		
		StringBuilder insertSql = new StringBuilder();
		Set<Field> fields = ReflectHandler.getAllFields(object);
		insertSql.append("insert into ").append(tableName).append("(");
		StringBuilder fieldSql = new StringBuilder();
		StringBuilder valueSql = new StringBuilder();
		for (Field field: fields) {
			String name = field.getName();
			switch (value) {
				case Key.AUTOINCREMENT:
					break;
				case Key.SEQUENCE:
					fieldSql.append(name).append(",");
					valueSql.append("#{").append("seq_").append(tableName).append(".nextval").append("},");
					break;
				default:
					fieldSql.append(name).append(",");
					valueSql.append("#{").append(name).append("},");
					break;
			}
		}

		// remove last ','
		valueSql.deleteCharAt(valueSql.length() - 1);
		fieldSql.deleteCharAt(fieldSql.length() - 1);
		insertSql.append(fieldSql).append(") values (").append(valueSql).append(") ");

		LOGGER.debug("insert sql: {}", insertSql.toString());
		return insertSql.toString();
	}
	
	public String insertBatch(Map<String, List<Object>> map) throws Exception {
		List<Object> list = map.get("list");
		Object object = list.get(0);
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		int value = table.value();
		
		StringBuilder strSql = new StringBuilder();
		Set<Field> fields = ReflectHandler.getAllFields(object);
		strSql.append("insert into ").append(tableName).append("(");
		StringBuilder fieldSql = new StringBuilder();
		StringBuilder valueSql = new StringBuilder();
		for (Field field: fields) {
			String name = field.getName();
			switch (value) {
				case Key.AUTOINCREMENT:
					break;
				case Key.SEQUENCE:
					fieldSql.append(name).append(",");
					valueSql.append("#{").append("seq_").append(tableName).append(".nextval").append("},");
					break;
				default:
					fieldSql.append(name).append(",");
					valueSql.append("#'{'").append("list[{0}].").append(name).append("'}',");
					break;
			}
		}
		
		// remove last ','
		valueSql.deleteCharAt(valueSql.length() - 1);
		fieldSql.deleteCharAt(fieldSql.length() - 1);
		strSql.append(fieldSql).append(") values");
		MessageFormat messageFormat = new MessageFormat(valueSql.toString());
		for (int i = 0; i < list.size(); i++) {
			strSql.append(" (").append(messageFormat.format(new Object[]{i})).append("),");
		}
		strSql.deleteCharAt(strSql.length()-1);
		
		LOGGER.debug("insertBatch sql: {}", strSql.toString());
		return strSql.toString();
	}
	
	/**
	 * 根据条件删除数据
	 * @param tableName
	 * @param params
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String deleteByConditions(String tableName, Map<String, Object> params) throws Exception{
		if (params == null || params.size() == 0) {
			throw new Exception("不允许全表删除，请手动删除！");
		}
		Set<String> fields = params.keySet();
		return new SQL(){
            {
            	DELETE_FROM(tableName);
                for (String field: fields) {
                	WHERE(field+" = #{"+field+"}");
                }
            }
        }.toString();
	}
	
	/**
	 * 根据对象的ID删除数据
	 * @param bean
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public String delete(Object bean) throws Exception{
		Class<?> beanClass = bean.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.key();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("该对象【"+tableName+"】没有设置主键，不能使用该删除方法！");
		}
		StringBuilder strSql = new StringBuilder();
		strSql.append("delete from ").append(tableName).append(" where 1=1");
		
		String keys[] = strKeys.split(",");
		for (String key: keys) {
			strSql.append(" and ").append(key).append(" = ").append("#{").append(key).append("}");
		}
		
		LOGGER.debug("delete sql: {}", strSql.toString());
		return strSql.toString();
	}
	
	public String findByConditions(Map<String, Object> map) throws Exception {
		String tableName = map.get("class").getClass().getSimpleName();
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>)map.get("params");
		String orderBy = (String)map.get("orderBy");
		StringBuilder strSql = new StringBuilder();
		strSql.append("select * from ")
		      .append(tableName)
		      .append(" where 1=1");
		if (params != null && params.size() > 0) {
			Set<String> fields = params.keySet();
			for (String field: fields) {
				strSql.append(field+" = #{"+field+"}");
            }
		} 
		
		if (StringUtils.isNotBlank(orderBy)) {
			strSql.append(orderBy);
		}
		
		return strSql.toString();
	}
	
	public String findBySql(Map<String, Object> map) throws Exception {
		String strSql = (String)map.get("sql");
		LOGGER.debug("findBySql sql: {}", strSql);
		return strSql;
	}
	
	public String findById(Object object) throws Exception {
		Class<?> beanClass = object.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.key();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("该对象【"+tableName+"】没有设置主键，不能使用该方法！");
		}
		
		String keys[] = strKeys.split(",");
		
		StringBuilder strSql = new StringBuilder();
		strSql.append("select * from ")
		.append(tableName)
		.append(" where 1=1");
		for (String key: keys) {
			strSql.append(" and ").append(key).append(" = ").append("#{"+key+"}");
		} 
		LOGGER.debug("findById sql: {}", strSql.toString());
		return strSql.toString();
	}
	
	public String update(Object bean) throws Exception{
		Class<?> beanClass = bean.getClass();
		String tableName = beanClass.getSimpleName();
		Table table = beanClass.getAnnotation(Table.class);
		String strKeys = table.key();
		if (StringUtils.isBlank(strKeys)) {
			throw new Exception("该对象【"+tableName+"】没有设置主键，不能使用该更新方法！");
		}
		StringBuilder strSql = new StringBuilder();
		Set<Field> fields = ReflectHandler.getAllFields(bean);
		strSql.append("update ").append(tableName).append(" set");
		
		String keys[] = strKeys.split(",");
		StringBuilder fieldSql = new StringBuilder();
		StringBuilder valueSql = new StringBuilder();
		for (Field field: fields) {
			String name = field.getName();
			fieldSql.append(name).append(",");
			valueSql.append("#{").append(name).append("},");
		}

		// remove last ','
		valueSql.deleteCharAt(valueSql.length() - 1);
		fieldSql.deleteCharAt(fieldSql.length() - 1);
		strSql.append(fieldSql).append(") values (").append(valueSql).append(")")
		      .append(" where 1=1");
		for (String key: keys) {
			strSql.append(" and ").append(key).append(" = ").append("#{").append(key).append("}");
		}
		
		LOGGER.debug("update sql: {}", strSql.toString());
		return strSql.toString();
	}
}
