package com.szmengran.mybatis.utils.mapper;

import java.sql.SQLException;
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
 * @Description: mybatis common method
 * @date Oct 30, 2018 2:54:17 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface IMapper<T> {
    
    /**
     * insert data
     * @param object
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @InsertProvider(type = SqlProviderUtils.class, method = "insert")
    int insert(Object object) throws SQLException;
    
    /**
     * batch insert data
     * @param list
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @InsertProvider(type = SqlProviderUtils.class, method = "insertBatch")
    int insertBatch(List<T> list) throws SQLException;
    
    /**
     * delete data by conditions
     * @param tableName
     * @param params
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @DeleteProvider(type = SqlProviderUtils.class, method = "deleteByConditions")
    int deleteByConditions(@Param("class") Class<?> cla, @Param("params") Map<String, Object> params);
    
    /**
     * delete one row by object id
     * @param object
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @DeleteProvider(type = SqlProviderUtils.class, method = "delete")
    int delete(Object object);
    
    /**
     * find data by primary key
     * @param object
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProviderUtils.class, method = "findById")
    T findById(T object);
    
    /**
     * find data by conditions
     * @param cla
     * @param params
     * @param orderBy
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProviderUtils.class, method = "findByConditions")
    List<T> findByConditions(@Param("class") Class<T> cla, @Param("params") Map<String, Object> params, @Param("orderBy") String orderBy);

    /**
     * find data by sql and conditions
     * @param cla
     * @param strSql
     * @param params
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @SelectProvider(type = SqlProviderUtils.class, method = "findBySql")
    List<T> findBySql(@Param("class") Class<T> cla, @Param("strSql") String strSql, @Param("params") Map<String, Object> params);
    
    /**
     * update data by primary key
     * @param object
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @UpdateProvider(type = SqlProviderUtils.class, method = "update")
    int update(Object object);
    
    /**
     * execute sql
     * @param strSql
     * @param params
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @UpdateProvider(type = SqlProviderUtils.class, method = "execute")
    int execute(@Param("strSql") String strSql, @Param("params") Map<String, Object> params) throws SQLException;
}
