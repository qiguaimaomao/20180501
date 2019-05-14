package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2.Widget;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
@ThreadSafe
public class PrivateLock {
private final Object mylock=new Object();
@GuardedBy("mylock")
Widget widget;
public void someMethod() {
	synchronized (mylock) {
		//修改widget的状态
	}

}
}
