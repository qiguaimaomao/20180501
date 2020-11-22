package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader {
private final FutureTask<ProductInfo>  task=new FutureTask<>(new Callable<ProductInfo>() {

	@Override
	public ProductInfo call() throws Exception {
		return new ProductInfo();
	}
	
});
private final Thread thread=new Thread(task);
public void start() {
	thread.start();

}

public ProductInfo get() throws InterruptedException{
	try {
		return task.get();
	} catch (ExecutionException e) {
		Throwable t=e.getCause();
		throw launderThrowable(t);
	}

}

private RuntimeException launderThrowable(Throwable t) {
	if (t instanceof RuntimeException) {
		return (RuntimeException)t;
	}else if (t instanceof Error)
        throw (Error)t;
	else {
		return new IllegalStateException("not uncheckde",t);
	}
}
}
