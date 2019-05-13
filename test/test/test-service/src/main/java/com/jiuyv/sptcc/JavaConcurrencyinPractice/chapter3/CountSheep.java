package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

/**
 * 只有满足下列所有标准之后，才能使用volatile变量
 * 1.变量的修改不依赖当前值；或者只有一个线程修改变量的值
 * 2.变量不需要与其他状态一起维持不变约束
 * 3.访问变量时，没有别的原因需要加锁
 * @author jiuyv
 *
 */
public class CountSheep {
private static volatile boolean asleep=false;


public static void main(String[] args) {
	while (!asleep) {
		countSomeSheep();
	}
}

private static void countSomeSheep() {
	// TODO Auto-generated method stub
	
}
}
