package com.honey.storage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MyCache<K,V> implements BasicCache<K,V>{
	/*
	 * 缓存
	 */
	private Cache<K,V> cache;
	/*
	 * 可并发写缓存的线程数
	 */
	private int concurrencyLevel = 20;
	/*
	 * 过期时间和单位
	 */
	private long expire = 60;
	private TimeUnit timeUnit = TimeUnit.SECONDS;
	/*
	 * 设置缓存大小上限，超过此值就开始LRU替换
	 */
	private int size = 1000;
	
	public MyCache() {
		init();
	}
	
	//@postConstruct
	public void init() {
		cache = CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterAccess(expire, timeUnit).maximumSize(size).build();
	}
	
	private V getValue(K key) throws ExecutionException {
		V result = this.cache.get(key, new Callable<V>() {

			public V call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		return result;
	}

	public V get(K key) {
		V result = null;
		try {
			result = getValue(key);
		} catch (ExecutionException e) {
			//guava不允许返回null，这里捕获异常，不作任何处理，返回null
		}
		
		return result;
	}

	public void put(K key, V value) {
		this.cache.put(key, value);
	}
	
	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

	public long getExpire() {
		return expire;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public int getSize() {
		return size;
	}

	public void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
