package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter8;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
private static ThreadPoolExecutor executor=new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
public static void main(String[] args) {
	executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	
}
}
