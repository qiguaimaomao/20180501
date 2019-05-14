package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.Vector;

import net.jcip.annotations.ThreadSafe;
@ThreadSafe
public class BetterVector<E> extends Vector<E>{
public synchronized boolean putIfAbsent(E e) {
	boolean absent=contains(e);
	if (!absent) {
		add(e);
	}
	return absent;
}
}
