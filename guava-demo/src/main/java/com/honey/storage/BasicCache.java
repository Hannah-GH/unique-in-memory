package com.honey.storage;

/**
 * 
 * @author H249365
 * 本地缓存接口
 * @param <K>
 * @param <V>
 */
public interface BasicCache<K,V> {
	/**
	 * 获取key对应的记录
	 * @param key
	 * @return V value
	 */
	public V get(K key);
	
	/**
	 * 向缓存中添加新记录
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
}
