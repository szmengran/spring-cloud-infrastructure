package com.szmengran.mybatis.utils.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.szmengran.mybatis.utils.SqlProviderUtils;

/**
 * @Package com.szmengran.mybatis.utils.mapper
 * @Description: TODO
 * @date Oct 30, 2018 2:54:17 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface IMapper {
	
	/**
	 * 插入数据
	 * @param object
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@InsertProvider(type = SqlProviderUtils.class, method = "insert")
	int insert(Object object) throws Exception;
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@InsertProvider(type = SqlProviderUtils.class, method = "insertBatch")
	int insertBatch(List<Object> list) throws Exception;
	
	/**
	 * 根据条件删除
	 * @param tableName
	 * @param params
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@DeleteProvider(type = SqlProviderUtils.class, method = "deleteByConditions")
	int deleteByConditions(String tableName, Map<String, Object> params) throws Exception;
	
	@DeleteProvider(type = SqlProviderUtils.class, method = "delete")
	int delete(Object object) throws Exception;
	
	@SelectProvider(type = SqlProviderUtils.class, method = "findById")
	<T> T findById(Object object) throws Exception;
	
	@SelectProvider(type = SqlProviderUtils.class, method = "findByConditions")
	<T> List<T> findByConditions(@Param("class") Class<T> cla, @Param("params") Map<String, Object> params, @Param("orderBy") String orderBy) throws Exception;
	
	@SelectProvider(type = SqlProviderUtils.class, method = "findBySql")
	<T> List<T> findBySql(Class<T> cla, @Param("sql") String sql, Map<String, Object> params) throws Exception;
	
	@UpdateProvider(type = SqlProviderUtils.class, method = "update")
	int update(Object bean) throws Exception;
}
