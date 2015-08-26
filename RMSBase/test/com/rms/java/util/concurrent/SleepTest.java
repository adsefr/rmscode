package com.rms.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepTest {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 1; i < 10; i++) {
			executorService.execute(new SimpleThread(i, i));
		}
	}
}

class SimpleThread implements Runnable {

	private int id;

	private int priority;

	public SimpleThread(int id, int priority) {

		this.id = id;
		this.priority = priority;
		Thread.currentThread().setPriority(priority);
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			try {
				System.out.println("id:" + Thread.currentThread().getId() + " priority:" + priority + " time:" + i);
				TimeUnit.SECONDS.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}