package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {
private final Set<Taxi> taxis;
private final Set<Taxi> availableTaxis;
public Dispatcher() {
	taxis=new HashSet<>();
	availableTaxis=new HashSet<>();
}
	public synchronized void notifyAvailable(Taxi taxi) {
		availableTaxis.add(taxi);
		
	}

	public synchronized void getImage() {
		for (Taxi taxi : taxis) {
			taxi.getLocation();
		}

	}
}
