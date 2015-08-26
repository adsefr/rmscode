package com.rms.java.util.concurrent;

public class WaitTest {

	public static void main(String[] args) {

		Object object = new Object();
		synchronized (object) {

			try {
				object.wait();
				System.out.println("wait");
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		object.notify();
	}
}
