package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter1;

import net.jcip.annotations.NotThreadSafe;

/**
 * 线程不安全的类
 * @author jiuyv
 *
 */
@NotThreadSafe
public class UnsafeSequence {
private int value;

public int getNext(int i) {
	return ++value;
}
//public static void main(String[] args) {
//	UnsafeSequence us=new UnsafeSequence();
//	System.out.println(us.getNext());
//	System.out.println(us.getNext());
//}
}
