package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11;

import junit.framework.TestCase;
import junit.framework.TestResult;

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

public void testTakeBlocksWhenEmpty() {
	final BoundedBuffer<Integer> bb=new BoundedBuffer<>(10);
Thread taker=new Thread(){
	@Override
		public void run() {
		try {
			int used=bb.take();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		}
};


try {
	taker.start();
	Thread.sleep(1000L);
	taker.interrupt();
	taker.join(1000L);
	assertFalse(taker.isAlive());
} catch (Exception e) {
	fail();
}
}
}
