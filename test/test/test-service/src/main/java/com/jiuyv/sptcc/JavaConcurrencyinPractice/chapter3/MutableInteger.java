package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
private int value;

public int getValue() {
	return value;
}

public void setValue(int value) {
	this.value = value;
}
}
