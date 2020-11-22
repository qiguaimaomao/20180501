package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TrackingExecutorTest {
	private final Set<Runnable> tasksToSave=Collections.synchronizedSet(new HashSet<>());
private volatile TrackingExecutor executor;
	public synchronized void stop() throws InterruptedException {
		try {
			tasksToSave.addAll(executor.shutdownNow());
			if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
				tasksToSave.addAll(executor.getCancelledTasks());
			}
		} finally {
			executor=null;
		}
		

	}
}
