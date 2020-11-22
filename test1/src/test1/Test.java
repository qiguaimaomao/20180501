package test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
public static void main(String[] args)  {
	System.out.println("hello world");
	for (int i = 0; i < 100000000; i++) {
		System.out.println(i);
		
	}
	ExecutorService s=Executors.newSingleThreadExecutor();
	s.execute(null);
	try {
		Thread.currentThread().join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
