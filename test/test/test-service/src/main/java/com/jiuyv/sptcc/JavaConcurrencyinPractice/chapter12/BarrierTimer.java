package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter12;

public class BarrierTimer implements Runnable{

	private boolean start=false;
	private long startTime,endTime;
	
	@Override
	public synchronized void run() {
		long t=System.nanoTime();
		if (!start) {
			start=true;
			startTime=t;
		}else{
			endTime=t;
		}
		
	}

	
	public synchronized void clear() {
		this.start=false;
	}
	
	public synchronized long getTime() {
		return endTime-startTime;
	}
}
