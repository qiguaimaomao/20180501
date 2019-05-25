package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

import java.awt.Point;

public class TaxiOpen {
private Point location,destination;
private final DispatcherOpen dispater;
public TaxiOpen(DispatcherOpen dispater) {
this.dispater=dispater;
}

public synchronized Point getLocation() {
	return location;
}

/**
 * 在持有锁的时候调用外部方法是在挑战活跃度问题，很难进行分析，因此是危险的
 * 当被调用的方法不需要持有锁时，这被称为开放调用
 * @param location
 */
public  void setLocation(Point location) {
	boolean b=false;
	synchronized (this) {
		this.location = location;
		b=destination.equals(location);
	}
	
	if (b) {
		dispater.notifyAvailable(this);
	}
}
}
