package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
private final Set<T> set;
private final Semaphore sem;
public BoundedHashSet(int bound) {
	set=Collections.synchronizedSet(new HashSet<T>());
	sem=new Semaphore(bound);
}

public boolean add(T e) {
	sem.tryAcquire();
	boolean wasAdded=false;
	try {
		wasAdded=set.add(e);
		return wasAdded;
	} finally {
		if (!wasAdded) {
			sem.release();
		}
	}

}

public boolean remove(T e) {
	boolean wasRemoved=set.remove(e);
	if (wasRemoved) {
		sem.release();
	}
	return wasRemoved;

}
}
