package test1.thisEscape;

import java.util.List;

public class ListenerRunnable implements Runnable {
	 
    private EventSource<EventListener> source;
    public ListenerRunnable(EventSource<EventListener> source) {
          this.source = source;
    }
    public void run() {
          List<EventListener> listeners = null;
          
          try {
                listeners = this.source.retrieveListeners();
          } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
          }
          for(EventListener listener : listeners) {
        	  ThisEscape th=    listener.onEvent(new Object());
          }
    }

}
