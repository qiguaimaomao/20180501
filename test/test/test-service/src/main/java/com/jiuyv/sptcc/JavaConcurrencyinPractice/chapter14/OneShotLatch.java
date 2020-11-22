package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import net.jcip.annotations.ThreadSafe;
/**
 * 二元闭锁使用AbstractQueuedSynchronizer
 * @author qiguai
 *
 */
@ThreadSafe
public class OneShotLatch {
private final Sync sync=new Sync();
public void await() throws InterruptedException {
	sync.acquireSharedInterruptibly(0);

}

public void signal() {
	sync.tryReleaseShared(0);

}
private class Sync extends AbstractQueuedSynchronizer{
	@Override
	protected int tryAcquireShared(int arg) {
		return (getState()==1)?1:-1;
	}
	
	@Override
	protected boolean tryReleaseShared(int arg) {
		setState(1);
		return true;
	}
}
}
