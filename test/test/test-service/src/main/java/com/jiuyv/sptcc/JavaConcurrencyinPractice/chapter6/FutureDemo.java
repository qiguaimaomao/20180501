package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * future描述了任务的生命周期，
 * 任务的生命周期包括创建，提交，开始，和完成
 * 总可以取消已经提交但尚未开始的任务，但对于已经开始的任务，只有它们响应中断，才能取消，取消一个已经完成的任务没有任何影响。
 * @author qiguai
 *
 */
public class FutureDemo implements Future{

	@Override
	public boolean cancel(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(long arg0, TimeUnit arg1) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

}
