package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

public class ThisSafe {
	private final EventListener eventListener;
	private ThisSafe() {
this.eventListener=new EventListener() {
	
	public void onEvent(Object o) {
		// TODO Auto-generated method stub
		
	}
};
	}
	
	public static ThisSafe getInstance(EventSource source) {
		ThisSafe safe=new ThisSafe();
		source.registerListener(safe.eventListener);
		return safe;
	}
}
