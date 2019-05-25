package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

public class LeftRightDeadLock {
private final Object left=new Object();
private final Object right=new Object();
public void leftRight() {
	synchronized (left) {
		synchronized (right) {
			doSomething();
		}
	}

}
private void doSomething() {
	// TODO Auto-generated method stub
	
}

public void rightLeft(){
	synchronized (right) {
		synchronized (left) {
			doSomething();
		}
	}
}
}
