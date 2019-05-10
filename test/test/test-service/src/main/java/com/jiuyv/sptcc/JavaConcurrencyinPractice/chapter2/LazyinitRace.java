package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyinitRace {
private ExpensiveObject instance=null;
 public ExpensiveObject getInstance() {
	 if (instance==null) {
		 
		 instance=new ExpensiveObject();
	}
	return instance;
}

}
