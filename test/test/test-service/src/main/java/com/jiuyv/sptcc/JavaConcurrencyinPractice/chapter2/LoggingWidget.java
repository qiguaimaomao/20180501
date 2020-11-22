package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;
/**
 * 同一个锁是可重入的，不然可能会引起死锁
 * @author jiuyv
 *
 */
public class LoggingWidget extends Widget{
@Override
public synchronized void doSomething() {
	System.out.println("LoggingWidget ");
	super.doSomething();
}
}
