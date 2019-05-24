package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter8;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{
	private final String poolname;
	public MyThreadFactory(String poolname) {
		this.poolname=poolname;
	}

	@Override
	public Thread newThread(Runnable arg0) {
		return new MyAppThread(arg0,poolname);
	}

}
