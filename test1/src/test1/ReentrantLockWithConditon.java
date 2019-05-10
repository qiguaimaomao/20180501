package test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditon implements Runnable{

	private static ReentrantLock lock=new ReentrantLock();
	private static Condition c=lock.newCondition(); 
	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName()+"begin wait");
			c.await();
			System.out.println(Thread.currentThread().getName()+"end wait");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockWithConditon task=new ReentrantLockWithConditon();
		Thread t=new Thread(task);
		t.start();
		Thread.sleep(1000);
        System.err.println("过了1秒后...");
        lock.lock();
		c.signal();
		lock.unlock();
	}
}
