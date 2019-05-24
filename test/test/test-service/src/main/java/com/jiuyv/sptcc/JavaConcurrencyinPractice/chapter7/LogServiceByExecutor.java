package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class LogServiceByExecutor {
private final ExecutorService service=Executors.newSingleThreadExecutor();
private final PrintWriter writer;

public LogServiceByExecutor(PrintWriter writer) {
	this.writer=writer;
}
public void start() {
	Runtime.getRuntime().addShutdownHook(new Thread() {
		@Override
		public void run() {
			try {
				LogServiceByExecutor.this.stop();
			} catch (Exception e) {
				// TODO: handle exception
			};
		}
	});
}

public void stop() throws InterruptedException {
try {
	service.shutdown();
	service.awaitTermination(10, TimeUnit.SECONDS);
} finally {
	writer.close();
}
}

public void log(String msg) {
	try {
		service.execute(new WriterTask(msg));
	} catch (RejectedExecutionException e) {
		// TODO: handle exception
	}
	

}

private class WriterTask implements Runnable{
private String msg;
	public WriterTask(String msg) {
		this.msg=msg;
	}
	@Override
	public void run() {
		LogServiceByExecutor.this.writer.print(msg);
		
	}
	
}
}
