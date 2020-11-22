package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator implements Runnable{
private final List<BigInteger> list=new ArrayList<>();
private volatile boolean canceled;
	@Override
	public void run() {
		BigInteger p=BigInteger.ONE;
		while (!canceled) {
			BigInteger pnext=p.nextProbablePrime();
			synchronized (this) {
				list.add(pnext);
			}
			
		}
		
	}
	public void cancel() {
		this.canceled=true;

	}
	
	public synchronized List<BigInteger> get() {
		return new ArrayList<>(this.list);

	}
	
	public static void main(String[] args) {
		
	}
}


