package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
	//不变约束 lower<upper
private final AtomicInteger lower=new AtomicInteger(0);
private final AtomicInteger upper=new AtomicInteger(0);
public void setLower(int i) {
	//未同步的检查再执行
	if (i>upper.get()) {
		throw new IllegalArgumentException("can't set lower to "+i+">upper");
	}
	lower.set(i);
}

public void setUpper(int i) {
	//未同步的检查再执行
	if (i<lower.get()) {
		throw new IllegalArgumentException("can't set upper to "+i+"<lower");
	}
	upper.set(i);
}

public boolean isRange(int t) {
	return (t>=lower.get())&&(t<=upper.get());

}
}
