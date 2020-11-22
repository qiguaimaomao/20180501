package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
@ThreadSafe
public class PersionSet {
	@GuardedBy("this")
private final Set<Person> myset=new HashSet<>();
public synchronized void addPerson(Person e) {
	myset.add(e);
}

public synchronized boolean contains(Person e) {
	return myset.contains(e);

}
}
