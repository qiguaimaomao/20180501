package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11;

import junit.framework.TestCase;

public class BoundedBufferTest extends TestCase{
public void testIsEmptyWhenConstructed() {
	BoundedBuffer<Integer> bb=new BoundedBuffer<>(10);
	assertTrue(bb.isEmpty());
	assertFalse(bb.isFull());
}

public void testIsFullAfterPuts() throws InterruptedException {
	BoundedBuffer<Integer> bb=new BoundedBuffer<>(10);
	for (int i = 0; i < 10; i++) {
		bb.put(i);
	}
	assertFalse(bb.isEmpty());
	assertTrue(bb.isFull());
}
}
