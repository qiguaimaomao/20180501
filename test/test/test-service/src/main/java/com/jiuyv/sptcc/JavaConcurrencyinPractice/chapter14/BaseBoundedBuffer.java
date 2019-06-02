package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

public abstract class BaseBoundedBuffer<V> {
private final V[] buf;
private int tail,head,count;
protected BaseBoundedBuffer(int capacity) {
	buf=(V[]) new Object[capacity];
	
}

protected synchronized void doPut(V value) {
	buf[tail]=value;
	if (++tail==buf.length) {
		tail=0;
	}
++count;
}

protected synchronized V doGet(){
	V value=buf[head];
	buf[head]=null;
	if (++head==buf.length) {
		head=0;
	}
	--count;
	return value;
}

public boolean isEmpty() {
	return count==0;

}

public boolean isFull() {
	return count==buf.length;

}
}
