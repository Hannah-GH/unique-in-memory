package com.honey.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.honey.storage.BasicCache;
import com.honey.storage.ConcreteCache;
import com.honey.storage.MsgObj;
import com.honey.storage.MyCache;
import com.honey.test.client.ReadOnlyClient;
import com.honey.test.client.WriteOnlyClient;
import com.honey.test.guava.GuavaCache;


/**
 * Hello world!
 *
 */
public class App 
{

	public static void testGuava() {
		long start = System.currentTimeMillis();
		//		try {
		//			FileOutputStream fo1 = new FileOutputStream("C:\\Users\\h249365\\Desktop\\guava.txt", true);
		//			PrintStream ps = new PrintStream(fo1);
		//			System.setOut(ps);
		int THREAD_MAX = 50;
		ExecutorService exec = Executors.newFixedThreadPool(THREAD_MAX);
		for(int i=0;i<THREAD_MAX;i++) {
			int NO = i;
			//				Runnable rw = new ReadnWriteClient("RW Task "+NO);
			//				exec.execute(rw);
			Runnable ro = new ReadOnlyClient("RO\t"+NO);
			exec.execute(ro);
			Runnable wo = new WriteOnlyClient("WO\t"+NO);
			exec.execute(wo);
		}
		exec.shutdown();
		//			ps.close();
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
	}

	public static void testConMap() {
		int THREAD_MAX = 50;
		ExecutorService exec = Executors.newFixedThreadPool(THREAD_MAX);
		for(int i=0;i<THREAD_MAX;i++) {
			int NO = i;
			Runnable ro = new ReadOnlyClient("RO\t"+NO);
			exec.execute(ro);
			Runnable wo = new WriteOnlyClient("WO\t"+NO);
			exec.execute(wo);
		}
		exec.shutdown();
	}

	public static void singleThreadGuava(){
		GuavaCache guava = new GuavaCache();
		String[] keys = new String[100];
		for(int i=0;i<100;i++){
			int key = (int) Math.floor(Math.random()*10000);
			keys[i] = String.valueOf(key);
		}
		long start = System.currentTimeMillis();

		try {
			for(int i=0;i<100;i++){
				System.out.println(guava.read(keys[i]));
			}
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("finished in "+(end-start)+"ms");
	}
	
	public static void test() {
		BasicCache<String,MsgObj> bc = new ConcreteCache();
		bc.put("a", new MsgObj("aaa"));
		bc.put("b", new MsgObj("bbb"));
		bc.put("a", new MsgObj("aaa"));
		bc.put("b", new MsgObj("bbc"));
		try {
			Thread.sleep(5900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bc.put("a", new MsgObj("aaa"));
	}

	public static void main( String[] args )
	{
		//		testGuava();
//		testConMap();
//		singleThreadGuava();
		test();
	}
}
