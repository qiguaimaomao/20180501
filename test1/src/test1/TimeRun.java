package test1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeRun {
	
	private static final ScheduledExecutorService cancelExec=Executors.newScheduledThreadPool(1); 
public static void timedRun(final Runnable r,long timeout,TimeUnit unit) throws InterruptedException {
	class RethrowableTask implements Runnable{
        private volatile Throwable t;
		@Override
		public void run() {
			try {
				r.run();
			} catch (Throwable t) {
				this.t=t;
			}
			
			
		}
		
		private void rethrow() {
			if (t!=null) {
				launderThrowable(t); 
			}

		}
	}

	RethrowableTask task=new RethrowableTask();
	final Thread taskThread=new Thread(task);
	taskThread.start();
	cancelExec.schedule(new Runnable() {
		
		@Override
		public void run() {
			taskThread.interrupt();
			
		}
	}, timeout, unit);
	//等待任务执行到超时时间之后则返回主线程，join的问题在于无法知道任务是完成了之后退出还是因为超时而退出
	taskThread.join(unit.toMillis(timeout));
	task.rethrow();
}

public static void  launderThrowable(Throwable t){
    if(t instanceof RuntimeException)
        throw  (RuntimeException)t;
    else if(t instanceof Error)
        throw (Error)t;
    else throw new RuntimeException(t);
}

public static void timedRunFuture(final Runnable r,long timeout,TimeUnit unit) throws InterruptedException {
	Future<?> task= cancelExec.submit(r);
	try {
		task.get(timeout, unit);
	} catch (ExecutionException e) {
		launderThrowable(e.getCause());
	} catch (TimeoutException e) {
		
	}finally {
		task.cancel(true);
	}
}
}
