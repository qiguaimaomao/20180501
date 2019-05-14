package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DelegatingVehicleTracker {
private final ConcurrentMap<String, Point> locations;
private final Map<String, Point>  unmodifiableMap;
public DelegatingVehicleTracker(Map<String, Point> locations) {
	this.locations=new ConcurrentHashMap<>(locations);
	unmodifiableMap=Collections.unmodifiableMap(this.locations);
}
/**
 * 返回一个不可修改但是实时的map
 * @return
 */
public Map<String, Point> getLocations() {
	return unmodifiableMap;
}
/**
 * 返回一个不可修改的快照
 * @return
 */
public Map<String, Point> getSnapShotLocations() {
	return Collections.unmodifiableMap(new HashMap<>(locations));
}

public Point getLocation(String id) {
	return locations.get(id);
}

public  void setLocation(String id,int x,int y) {
	if (locations.replace(id, new Point(x, y)) != null) {
		throw new IllegalArgumentException("no such id " + id);
	}
}
}
