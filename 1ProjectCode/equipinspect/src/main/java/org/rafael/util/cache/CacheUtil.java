package org.rafael.util.cache;

import java.util.Iterator;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.rafael.util.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 缓存工具-使用的是shiro的cache接口，具体实现是由ehcache ，可以通过修改shiro的cache配置换为其他缓存实现。
 * @author Rafael
 *
 */
public class CacheUtil {
	private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);
	private static CacheManager cacheManager = SpringContextUtil.getBean(CacheManager.class);
	
	private static final String SYS_CACHE = "sysCache";

	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	/**
	 * 写入SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	/**
	 * 从SYS_CACHE缓存中移除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}
	
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		return getCache(cacheName).get(key);
	}
	
	/**
	 * 写入缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		getCache(cacheName).put(key, value);
	}
	
	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 从缓存中移除所有
	 * @param cacheName
	 */
	public static void removeAll(String cacheName) {
		Cache<String, Object> cache = getCache(cacheName);
		Set<String> keys = cache.keys();
		for (Iterator<String> it = keys.iterator(); it.hasNext();){
			cache.remove(it.next());
		}
		logger.info("清理缓存： {} => {}", cacheName, keys);
	}
	
	private static Cache<String, Object> getCache(String cacheName){
		Cache<String, Object> cache = cacheManager.getCache(cacheName);
		if (cache == null){
			throw new RuntimeException("当前系统中没有定义“"+cacheName+"”这个缓存。");
		}
		return cache;
	}
}
