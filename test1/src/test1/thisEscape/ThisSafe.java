package test1.thisEscape;

public class ThisSafe {
	 private final int id;
	 private final String name;
	 private final EventListener eventListener;
	 public String getName() {
		return name;
	}
     private ThisSafe() {
           id = 1;
           eventListener=new EventListener() {
               public ThisEscape onEvent(Object obj) {
                   System.out.println("id: "+ThisSafe.this.id);
                   System.out.println("name: "+ThisSafe.this.name);
				return null;
             }
       };
           
           try {
               Thread.sleep(1000); // 调用sleep模拟其他耗时的初始化操作
         } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
         }
           name = "flysqrlboy";
           
     }
     
     public static ThisSafe getInstance(EventSource<EventListener> source) {
    	 ThisSafe thisSafe=new ThisSafe();
    	 source.registerListener(thisSafe.eventListener);
return thisSafe;
	}
}
