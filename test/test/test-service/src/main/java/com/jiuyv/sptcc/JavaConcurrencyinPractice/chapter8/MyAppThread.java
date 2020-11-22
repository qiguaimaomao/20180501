package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyAppThread extends Thread{
public static final String DEFAULT_NAME="MyAppThread";
private static volatile boolean debugLifecycle=false;
private static final AtomicInteger created=new AtomicInteger();
private static final AtomicInteger alive=new AtomicInteger();
private static final Logger log=Logger.getAnonymousLogger();

public MyAppThread(Runnable r) {
	this(r,DEFAULT_NAME);
}

public MyAppThread(Runnable r, String defaultName) {
	super(r, defaultName+"-"+created.incrementAndGet());
	setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			log.log(Level.SEVERE, "uncaught in thread "+t.getName(), e);
		}
	});
}


@Override
	public void run() {
	boolean debug=this.debugLifecycle;
		if (debug) {
			log.log(Level.FINE, "created "+ getName());
		}
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug) {
				log.log(Level.FINE, "exiting "+ getName());
			}
		}		
	}

public static int getCreated() {
	return created.get();
}

public static int getAlive() {
	return alive.get();
}

public static boolean getdebug() {
	return debugLifecycle;

}

public static void setDebugLifecycle(boolean debugLifecycle) {
	MyAppThread.debugLifecycle = debugLifecycle;
}
}
