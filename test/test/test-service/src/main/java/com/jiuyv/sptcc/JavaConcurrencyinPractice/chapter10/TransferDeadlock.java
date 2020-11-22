package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

public class TransferDeadlock {
	/**
	 * 注意保证加锁的顺序,不然很容易造成死锁
	 * @param from
	 * @param to
	 */
public void transferMoney(Account from,Account to) {
	synchronized (from) {
		synchronized (to) {
			
		}
	}

}
}
