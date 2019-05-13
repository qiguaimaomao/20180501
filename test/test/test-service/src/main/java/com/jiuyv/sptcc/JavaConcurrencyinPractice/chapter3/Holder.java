package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

public class Holder {
private final int n;
public Holder(int n) {
	this.n=n;
}
public void assertSanity() {
	if (n!=n) {
		throw new AssertionError("this statement is false");
	}

}
}
