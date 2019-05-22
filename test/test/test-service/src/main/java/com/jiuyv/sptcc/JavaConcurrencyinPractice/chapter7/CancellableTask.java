package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public interface CancellableTask<T> extends Callable<T>{
void cancel();
RunnableFuture<T> newTask();
}
