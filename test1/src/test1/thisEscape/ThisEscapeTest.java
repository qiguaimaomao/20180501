package test1.thisEscape;

public class ThisEscapeTest {
	 
    public static void main(String[] args) {
          EventSource<EventListener> source = new EventSource<EventListener>();
          ListenerRunnable listRun = new ListenerRunnable(source);
          Thread thread = new Thread(listRun);
          thread.start();
          ThisEscape escape1 = new ThisEscape(source);
          System.out.println(escape1.getName());
          System.out.println("ThreadLocal"+escape1.getThreadLocal().get());
          ThisSafe thisSafe= ThisSafe.getInstance(source);
          ListenerRunnable listRun2 = new ListenerRunnable(source);
          Thread thread2 = new Thread(listRun2);
          thread2.start();
          System.out.println(thisSafe.getName());
    }
}
