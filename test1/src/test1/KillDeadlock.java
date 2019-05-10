package test1;

import java.util.concurrent.locks.ReentrantLock;

public class KillDeadlock implements Runnable{

	private static ReentrantLock lock1=new ReentrantLock();
	private static ReentrantLock lock2=new ReentrantLock();
	int lock;
	public KillDeadlock(int i) {
		this.lock=i;
	}
	@Override
	public void run() {
		try {
		if (lock==1) {
			
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				
				lock2.lockInterruptibly();
				System.out.println(lock+":"+Thread.currentThread().getId()+":完成");
			} 
	else {
		lock2.lockInterruptibly();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		lock1.lockInterruptibly();
		System.out.println(lock+":"+Thread.currentThread().getId()+":完成");

		}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId()+":退出");
		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		KillDeadlock killDeadlock1=new KillDeadlock(1);
		KillDeadlock killDeadlock2=new KillDeadlock(2);
		Thread thread1=new Thread(killDeadlock1);
		Thread thread2=new Thread(killDeadlock2);
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
		thread2.interrupt();
	}
}
