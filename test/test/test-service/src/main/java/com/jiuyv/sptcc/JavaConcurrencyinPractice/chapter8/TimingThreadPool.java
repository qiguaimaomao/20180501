package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TimingThreadPool extends ThreadPoolExecutor{
	
	private final ThreadLocal<Long> starttime=new ThreadLocal<>();
	private final AtomicLong numTasks=new AtomicLong();
	private final AtomicLong totaltime=new AtomicLong();
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		super.beforeExecute(t, r);
		starttime.set(System.nanoTime());
	}

	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			numTasks.getAndIncrement();
			long endtime=System.nanoTime();
			long executetime=endtime-starttime.get();
			totaltime.addAndGet(executetime);
		} finally {
			super.afterExecute(r, t);
		}
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void terminated() {
	try {
		System.out.println(totaltime.get()/numTasks.get());
	} finally {
		super.terminated();
	}
		
	}
}
