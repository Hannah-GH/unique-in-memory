package com.honey.test.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCache {
	private static  Cache<String,String> cache;
	private static int inNum = 0;
	private static int outNum = 0;
	
	static {
		cache = CacheBuilder.newBuilder().recordStats().maximumSize(5000).build();
		long start = System.currentTimeMillis();
		//generate 10000 data
		for(int i=0;i<4000;i++) {
			String key = String.valueOf(i);
			cache.put(key, "AAAAAAAAAB");
		}
		long end = System.currentTimeMillis();
		System.out.println("cost "+(end-start)/1000.0+"s");
	}

	public GuavaCache() {
//		System.out.println("guava up!");
//		System.out.println("write in "+cache.asMap().size());
	}

	public synchronized int write(String key, String word) {
			cache.put(key, word);
//			inNum++;
			return cache.asMap().size();
	}

	public synchronized String read(String key) throws ExecutionException {
//		outNum++;
		return cache.get(key, new Callable<String>(){
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	public int getInNum() {
		return inNum;
	}
	
	public int getOutNum() {
		return outNum;
	}
}
