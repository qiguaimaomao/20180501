package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {
public int x,y;
public MutablePoint() {
	x=0;
	y=0;
}

public MutablePoint(MutablePoint m) {
	this.x=m.x;
	this.y=m.y;
}
}
