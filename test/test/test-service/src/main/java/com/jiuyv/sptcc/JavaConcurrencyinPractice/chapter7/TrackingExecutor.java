package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService{
	public TrackingExecutor(ExecutorService es) {
		this.es=es;
	}
private final ExecutorService es;
private final Set<Runnable> tasksCancelledAtShutdown=Collections.synchronizedSet(new HashSet<>());
	@Override
	public void shutdown() {
		es.shutdown();
		
	}

	@Override
	public List<Runnable> shutdownNow() {
		return	es.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return es.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return es.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return es.awaitTermination(timeout, unit);
	}

	@Override
	public void execute(final Runnable command) {
			es.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						command.run();
					} finally {
						if (isShutdown()&&Thread.currentThread().isInterrupted()) {
							tasksCancelledAtShutdown.add(command);
						}
					}
					
				}
			});	
		
		
	}

	public List<Runnable> getCancelledTasks() {
		if (isTerminated()) {
			throw new IllegalStateException("");
		}
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);

	}
}
