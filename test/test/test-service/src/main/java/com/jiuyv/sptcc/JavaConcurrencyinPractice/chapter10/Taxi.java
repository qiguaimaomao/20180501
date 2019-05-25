package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

import java.awt.Point;

public class Taxi {
private Point location,destination;
private final Dispatcher dispater;
public Taxi(Dispatcher dispater) {
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
public synchronized void setLocation(Point location) {
	this.location = location;
	if (destination.equals(location)) {
		dispater.notifyAvailable(this);
	}
}
}
