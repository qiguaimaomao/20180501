package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeRunTest {
private static final ScheduledExecutorService ses=Executors.newScheduledThreadPool(1);
public static void timeRun(Runnable r,long timeout,TimeUnit unit) {
	final Thread currentThread=Thread.currentThread();
	ses.schedule(new Runnable() {
		
		@Override
		public void run() {
			//违反了一个规则，当中断一个线程之前，必须了解这个线程的中断策略
			currentThread.interrupt();
			
		}
	}, timeout, unit);
	r.run();
}

public void timeRunNewTask(Runnable r,long timeout,TimeUnit unit) throws InterruptedException {
	class RethrowableTask implements Runnable{
private volatile Throwable t;
		@Override
		public void run() {
			try {
				r.run();
			} catch (Throwable e) {
				t=e;
			}
			
		}
		
		public void rethrow() {
			if (t!=null) {
				throw new RuntimeException(t);
			}
			

		}
	}
	RethrowableTask target=new RethrowableTask();
	final Thread taskThread=new Thread(target);
	taskThread.start();
	
ses.schedule(new Runnable() {
		
		@Override
		public void run() {
			//这个线程是属于自己的，可以放心的中断
			taskThread.interrupt();
			
		}
	}, timeout, unit);
//不管任务是否响应中断，方法的退出由taskThread的join来决定，这里有个问题就是不知道taskThread是超时结束还是提前结束
taskThread.join(unit.toMillis(timeout));
target.rethrow();
}

public void timeRunFuture(Runnable r,long timeout,TimeUnit unit) throws InterruptedException {
	Future<?> future=ses.submit(r);
	try {
		future.get(timeout, unit);
	}  catch (ExecutionException e) {
		throw new RuntimeException(e.getCause());
	} catch (TimeoutException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//在任务超时或者被中断时，如果你知道不再需要结果时，可以取消任务
		future.cancel(true);
	}

}
}
