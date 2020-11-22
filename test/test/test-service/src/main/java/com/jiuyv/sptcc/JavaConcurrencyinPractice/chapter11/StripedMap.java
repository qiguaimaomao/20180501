package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11;


public class StripedMap {
private static class Node{
	private final Node next;
	private final Object key;
	private final Object value;
	public Node(Node next,Object key,Object value) {
		this.next=next;
		this.value=value;
		this.key=key;
	}
	
}

private static final int N_LOCK=16;
private final Object[] locks;
private final Node[] buckets;
public StripedMap(int numbuckets) {
	locks=new Object[N_LOCK];
	for (int i = 0; i < N_LOCK; i++) {
		locks[i]=new Object();
	}
	buckets=new Node[numbuckets];
}

private int hash(Object key) {
	return Math.abs(key.hashCode()%buckets.length);
}

public Object get(Object key) {
	int hash=hash(key);
	synchronized (locks[hash%N_LOCK]) {
		for (Node m=buckets[hash]; m!=null; m=m.next) {
			if (m.key.equals(key)) {
				return m.value;
			}
		}
	}
	return null;

}

public void clear() {
	for (int i = 0; i < buckets.length; i++) {
		synchronized (locks[i%N_LOCK]) {
			buckets[i]=null;
		}
	}

}
}
