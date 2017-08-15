package com.honey.storage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ConcreteCache extends MyCache<String,MsgObj> implements BasicCache<String,MsgObj> {
	
	public ConcreteCache() {
		this.init(); 								//初始化默认参数
	}
	
	public ConcreteCache(int concurrencyLevel,long expire,TimeUnit timeUnit, int size) {
		this.setConcurrencyLevel(concurrencyLevel);
		this.setExpire(expire);
		this.setTimeUnit(timeUnit);
		this.setSize(size);
	}


	@Override
	protected MsgObj fetchData(String key) throws ExecutionException {
		MsgObj obj = this.getCache().get(key, new Callable<MsgObj>() {

			public MsgObj call() throws Exception {
				return new MsgObj("");															//这里构造一个null obj返回出去，避免抛异常
			}
			
		});
		return obj;
	}

	@Override
	protected boolean isNull(MsgObj value) {
		if(value==null || value.getText()==null || value.getText().length()==0)
			return true;
		return false;
	}
	
	public boolean put(String key, MsgObj value) {
		MsgObj oldValue = this.getValue(key);
		if(oldValue==null || !value.equals(oldValue)) {
			if(!isNull(value)) {
				this.putValue(key, value);
				System.out.println("insert or update");
				return true;
			}else {
				System.out.println("empty message cannot be stored");
			}
		}
		System.out.println("not updated");
		return false;
	}
	
	

}
