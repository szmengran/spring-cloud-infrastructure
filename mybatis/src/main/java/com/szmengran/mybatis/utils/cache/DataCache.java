package com.szmengran.mybatis.utils.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * @ClassName: DataCache 
 * @Description: 数据缓存
 * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
 * @date 2016年10月26日 下午4:24:18 
 *
 */
public class DataCache {
	private static final Map<String,Object> map = new ConcurrentHashMap<String,Object>();
	private static final DataCache dataCache = new DataCache();
	private DataCache(){}
	public static DataCache getInstance(){
		return dataCache;
	}
	
	/**
	 * 
	 * @Description: 缓存一个值
	 * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
	 * @date 2016年10月26日 下午4:24:48 
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value){
		map.put(key, value);
	}
	
	/**
	 * 
	 * @Description: 获取一个值 
	 * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
	 * @date 2016年10月26日 下午4:25:07 
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		return map.get(key);
	}
}
