package test1.thisEscape;

public class ThisEscape {
	 private final int id;
	 private final String name;
	 private ThreadLocal<String> threadLocal=ThreadLocal.withInitial(()->"123");
	 public String getName() {
		return name;
	}
     public ThisEscape(EventSource<EventListener> source) {
           id = 1;
           source.registerListener(new EventListener() {
                 public ThisEscape onEvent(Object obj) {
//                	 ThisEscape.this.threadLocal.set("1234");
                       System.out.println("id: "+ThisEscape.this.id);
                       System.out.println("name: "+ThisEscape.this.name);
                       System.out.println("threadLocal: "+threadLocal.get());
                       return ThisEscape.this;
                 }
           });
           try {
               Thread.sleep(1000); // 调用sleep模拟其他耗时的初始化操作
         } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
         }
           name = "flysqrlboy";
      	 threadLocal.set(name);
     }
     
     public ThreadLocal getThreadLocal() {
		return threadLocal;
	}
}
