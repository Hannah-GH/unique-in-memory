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
	 * 
	 * @param key
	 * @param value
	 * @return false 表示消息不更新
	 * 			true 表示执行消息更新
	 */
	public boolean put(K key, V value);
	
}
