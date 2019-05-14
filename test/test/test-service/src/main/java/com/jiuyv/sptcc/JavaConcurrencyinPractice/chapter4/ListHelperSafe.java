package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ListHelperSafe<E> {
	public List<E> list=Collections.synchronizedList(new ArrayList<E>()) ;

	/**
	 * 使用相同的锁
	 * @param e
	 * @return
	 */
	public  boolean putIfAbsent(E e) {
		synchronized (list) {
			boolean absent=list.contains(e);
			if (!absent) {
				list.add(e);
			}
			return absent;
		}
		

	}
}
