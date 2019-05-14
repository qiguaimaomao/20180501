package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
@ThreadSafe
public class MonitorVehicleTracker {
	/**
	 * 不是一个线程安全的map
	 * 先复制可变的数据，再返回给用户，这种实现方式部分的维护着线程安全。
	 * 注意复制的时候要使用深度复制，不能仅仅返回一个unmodifiableMap容器就行，还必须复制到容器中的每个对象。
	 */
@GuardedBy("this")	
private final Map<String,MutablePoint> locations;
public MonitorVehicleTracker(Map<String,MutablePoint> locations) {
	this.locations=deepCopy(locations);
}



private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
	Map<String, MutablePoint> result=new HashMap<>();
	for (String iterable_element : locations.keySet()) {
		result.put(iterable_element, locations.get(iterable_element));
	}
	return Collections.unmodifiableMap(result);
}

public synchronized MutablePoint getLocation(String id) {
	MutablePoint p=locations.get(id);
return p==null?null:new MutablePoint(p);
}

public synchronized void setLocation(String id,int x,int y) {
	MutablePoint p=locations.get(id);
	if (p==null) {
		throw new IllegalArgumentException("no such id " + id);
	}

	p.x=x;
	p.y=y;
}

public synchronized Map<String,MutablePoint> getLocations() {
	return deepCopy(locations);
}
}
