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
public interface IMapper<T>{
	
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
	int deleteByConditions(@Param("class") Class<?> cla, @Param("params") Map<String, Object> params) throws Exception;
	
	@DeleteProvider(type = SqlProviderUtils.class, method = "delete")
	int delete(Object object) throws Exception;
	
	/**
	 * 根据主键ID查询
	 * @param object
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProviderUtils.class, method = "findById")
	T findById(T object) throws Exception;
	
	/**
	 * 根据条件查询数据
	 * @param cla
	 * @param params
	 * @param orderBy
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@SelectProvider(type = SqlProviderUtils.class, method = "findByConditions")
	List<T> findByConditions(@Param("class") Class<T> cla, @Param("params") Map<String, Object> params, @Param("orderBy") String orderBy) throws Exception;

	@SelectProvider(type = SqlProviderUtils.class, method = "findBySql")
	List<T> findBySql(@Param("class") Class<T> cla, @Param("strSql") String strSql, @Param("params") Map<String, Object> params) throws Exception;
	
	@UpdateProvider(type = SqlProviderUtils.class, method = "update")
	int update(Object object) throws Exception;
	
	@UpdateProvider(type = SqlProviderUtils.class, method = "execute")
	int execute(@Param("strSql") String strSql, @Param("params") Map<String, Object> params) throws Exception;
}
