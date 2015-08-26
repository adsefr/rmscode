package com.rms.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VolatileTest {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 10; i++) {
			TimeUnit.SECONDS.sleep(2);
			executorService.execute(new VolatileTestClass(i, i));
		}
	}
}

class VolatileTestClass implements Runnable {

	private static volatile boolean sleep = false;

	private int id;

	private int priority;

	public VolatileTestClass(int id, int priority) {

		this.id = id;
		this.priority = priority;
		Thread.currentThread().setPriority(priority);
	}

	@Override
	public void run() {

		if (id == 10) {
			sleep = true;

		}
		while (!sleep || id == 10) {
			System.out.println("Thread Id:" + id);
		}
	}
}