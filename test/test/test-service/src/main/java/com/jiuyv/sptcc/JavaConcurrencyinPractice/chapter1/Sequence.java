package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 线程安全的类
 * @author jiuyv
 *
 */
@ThreadSafe
public class Sequence {
	@GuardedBy("this")
private int value;
public synchronized int getNext() {
	return ++value;
}
}
