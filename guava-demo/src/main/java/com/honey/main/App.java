package com.honey.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

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
		GuavaCache guava = new GuavaCache();
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
	
	public static void main( String[] args )
	{
		testGuava();
//		testConMap();
	}
}
