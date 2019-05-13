package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

import java.util.ArrayList;
import java.util.List;

public class EventSource {
	 
    private final List<EventListener> eventListeners ;
    
    public EventSource() {
          eventListeners = new ArrayList<EventListener>() ;
    }
    
    public synchronized void registerListener(EventListener eventListener) {
          this.eventListeners.add(eventListener);
          this.notifyAll();
    }
    
    public synchronized List<EventListener> retrieveListeners() throws InterruptedException {
          List<EventListener> dest = null;
          if(eventListeners.size() <= 0 ) {
                this.wait();
          }
          dest = new ArrayList<EventListener>(eventListeners.size());
          dest.addAll(eventListeners);
          return dest;
    }
}
