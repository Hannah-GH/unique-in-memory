package com.honey.test.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WriteOnlyClient extends MsgClient implements Runnable {

	public WriteOnlyClient(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		for(int i=0;i<50;i++) {
			try {
				this.writeToGuava();
				Thread.sleep((long)(Math.random()*400));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		
//		try {
//			FileOutputStream fo1 = new FileOutputStream("C:\\Users\\h249365\\Desktop\\guava_200.txt", true);
//			PrintStream ps = new PrintStream(fo1);
//			System.setOut(ps);
			System.out.println(this.getName()+"\t"+(end-start));
//			ps.close();
//			fo1.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
