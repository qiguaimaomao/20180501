package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter11;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {
private final Semaphore availableItems,availableSpaces;
private final E[] items;
private int putPosition=0,takePosition=0;
public BoundedBuffer(int bound) {
	availableItems=new Semaphore(0);
	availableSpaces=new Semaphore(bound);
	items=(E[]) new Object[bound];
}

public boolean isEmpty() {
	return availableItems.availablePermits()==0;

}

public boolean isFull(){
	return availableSpaces.availablePermits()==0;
} 

public void put(E e) throws InterruptedException {
	availableSpaces.acquire();
	doInsert(e);
	availableItems.release();

}

public E take() throws InterruptedException {
	availableItems.acquire();
	E e=doExtract();
	availableSpaces.release();
	return e;

}

private synchronized E doExtract() {
	int i=takePosition;
	E e=items[i];
	items[i]=null;
	takePosition=(++i==items.length)?0:i;
	return e;
}

private synchronized void doInsert(E e) {
	int i=putPosition;
	items[i]=e;
	putPosition=(++i==items.length)?0:i;
}


}
