package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckEmailTest {
	public void checkEmail(Set<String> hosts,long timeout,TimeUnit unit) throws InterruptedException {
		ExecutorService es=Executors.newCachedThreadPool();
	   final AtomicBoolean hasnew=new AtomicBoolean(false);
		try {
			  for (String string : hosts) {
					es.submit(new Runnable() {
						
						@Override
						public void run() {
							if (checkMail(string)) {
								hasnew.set(true);
							}
							
						}
					});
					
				}
		} finally {
			es.shutdown();
			es.awaitTermination(timeout, unit);
		}
	 

	}
	
	private boolean checkMail(String host) {
		return false;
		// TODO Auto-generated method stub

	}
}
