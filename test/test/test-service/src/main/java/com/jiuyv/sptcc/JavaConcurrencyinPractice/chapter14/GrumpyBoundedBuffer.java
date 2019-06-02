package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public synchronized void put(V v) throws BufferFullException {
		if (isFull()) {
			throw new BufferFullException();
		}
		this.doPut(v);

	}
	
	public synchronized V get() throws BufferEmptyException {
		if (isEmpty()) {
			throw new BufferEmptyException();
		}
		return this.doGet();

	}
	
	public static void main(String[] args) throws InterruptedException {
		GrumpyBoundedBuffer<String> gbb=new GrumpyBoundedBuffer<>(5);
		while (true) {
			try {
				String v=gbb.get();
			} catch (BufferEmptyException e) {
				Thread.sleep(100);
			}
		}
	}
}
