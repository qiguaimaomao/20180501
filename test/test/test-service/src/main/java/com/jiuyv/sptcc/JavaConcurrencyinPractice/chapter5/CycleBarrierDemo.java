package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.concurrent.CyclicBarrier;

public class CycleBarrierDemo {
private static final CyclicBarrier cb=new CyclicBarrier(100, new Runnable() {
	
	@Override
	public void run() {
		System.out.println("next step ");
		
	}
});


}
