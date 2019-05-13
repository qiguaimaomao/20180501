package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

/**
 * 也可以通过如本地变量来实现线程封闭
 * @author jiuyv
 *
 */
public class ThisEscape {
	/**
	 * ThreadLocal用于防止单例变量或者全局变量出现不正确的共享
	 */
	 public static ThreadLocal<String> threadLocal=ThreadLocal.withInitial(()->String.valueOf(System.currentTimeMillis()));

	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {
			
			public void onEvent(Object o) {
				System.out.println(ThisEscape.this);
			}
		});
	}
}
