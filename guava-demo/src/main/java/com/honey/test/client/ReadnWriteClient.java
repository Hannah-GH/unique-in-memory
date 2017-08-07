package com.honey.test.client;

public class ReadnWriteClient extends MsgClient implements Runnable {

	public ReadnWriteClient(String name) {
		super(name);
	}

	public void run() {
		while(true) {
			try {
				this.writeToGuava();
				Thread.sleep((long)(100+Math.random()*400));
				String read = this.readFromGuava();
				System.out.println(getName()+"\tread:\t"+read);
				Thread.sleep((long)(100+Math.random()*400));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
