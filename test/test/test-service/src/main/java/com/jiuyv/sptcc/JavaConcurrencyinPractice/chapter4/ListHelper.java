package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.jcip.annotations.NotThreadSafe;
@NotThreadSafe
public class ListHelper<E> {
public List<E> list=Collections.synchronizedList(new ArrayList<E>()) ;

/**
 * 没有使用相同的锁
 * @param e
 * @return
 */
public synchronized boolean putIfAbsent(E e) {
	boolean absent=list.contains(e);
	if (!absent) {
		list.add(e);
	}
	return absent;

}
}
