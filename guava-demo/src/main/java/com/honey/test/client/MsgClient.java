package com.honey.test.client;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.honey.test.guava.GuavaCache;

public class MsgClient {
	private String name;
	private GuavaCache guava;
	private int wNum;
	private int rNum;
	
	public MsgClient(String name) {
		this.name = name;
		this.guava = new GuavaCache();
		this.wNum = 0;
		this.rNum = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public void writeToGuava() {
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString();
		int size = guava.write(key, new String("ABCDEFGH"));
		this.wNum++;
//		System.out.println(this.getName()+"\twrite:\t"+key+":ABCDEFGH");
//		System.out.println(this.getName()+"\tsize:\t"+size);
	}

	public String readFromGuava() {
		int i = (int) Math.floor(Math.random()*1000);
		String query = null;
		try {
			query = guava.read(String.valueOf(i));
			this.rNum++;
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return query;
	}
	
	public int getWNum() {
		return this.wNum;
	}
	
	public int getRNum() {
		return this.rNum;
	}

	
}
