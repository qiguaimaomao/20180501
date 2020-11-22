package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class SocketUsingTask<T> implements CancellableTask<T>{
private Socket socket;
public synchronized void setSocket(Socket socket) {
	this.socket = socket;
}
	@Override
	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void cancel() {
		try {
		if (socket!=null) {
			
				socket.close();
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		
		return new FutureTask<T>(this){
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
				
			}
		};
	}

}
