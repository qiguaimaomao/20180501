package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter8;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundedExecutor {
	private final Semaphore sem;
	private final Executor exe;
	
	public BoundedExecutor(Executor exe,int bound) {
		this.sem=new Semaphore(bound);
		this.exe=exe;
	}
	
	public void submitTask(final Runnable task) throws InterruptedException {
		sem.acquire();
		try {
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						task.run();
					} finally {
						sem.release();
					}
				
					
				}
			});
		} catch (RejectedExecutionException e) {
			sem.release();
		}

	}
}
