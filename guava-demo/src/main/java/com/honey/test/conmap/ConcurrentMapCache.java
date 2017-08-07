package com.honey.test.conmap;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.common.cache.CacheBuilder;

public class ConcurrentMapCache {
	private static ConcurrentMap<String,String> cache;
	
	static {
		cache = new ConcurrentHashMap<String,String>();
		long start = System.currentTimeMillis();
		//generate 10000 data
		for(int i=0;i<10000;i++) {
			String key = String.valueOf(i);
			cache.put(key, "AAAAAAAAAB");
		}
		long end = System.currentTimeMillis();
		System.out.println("cost "+(end-start)/1000.0+"s");
	}

	
	public ConcurrentMapCache() {
		System.out.println("ConcurrentMap up!!!");
		System.out.println("size: "+cache.size());
	}
	
	public int write(String key, String word) {
		cache.put(key, word);
		return cache.size();
	}
	
	public String read(String key) {
		return cache.get(key);
	}
	
	
}
