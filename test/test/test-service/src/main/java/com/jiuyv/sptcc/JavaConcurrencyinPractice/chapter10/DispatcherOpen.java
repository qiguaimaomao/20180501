package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter10;

import java.util.HashSet;
import java.util.Set;

public class DispatcherOpen {
private final Set<TaxiOpen> taxis;
private final Set<TaxiOpen> availableTaxis;
public DispatcherOpen() {
	taxis=new HashSet<>();
	availableTaxis=new HashSet<>();
}
	public synchronized void notifyAvailable(TaxiOpen taxi) {
		availableTaxis.add(taxi);
		
	}

	public  void getImage() {
		Set<TaxiOpen>  copy;
		synchronized (this) {
			copy=new HashSet<>(taxis);	
		}
		for (TaxiOpen taxi : copy) {
			taxi.getLocation();
		}

	}
}
