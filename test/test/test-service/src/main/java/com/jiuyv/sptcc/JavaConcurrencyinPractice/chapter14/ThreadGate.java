package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter14;
/**
 * 可重复关闭的闸门
 * @author qiguai
 *
 */
public class ThreadGate {
private boolean isOpen;
private int generation;

public void close() {
	this.isOpen=false;
}

public void open() {
	++generation;
	isOpen=true;
	notifyAll();

}

public void await() throws InterruptedException {
	int arrivegeneration=this.generation;
	//打开之后，即使再次关闭，之前等待的线程也能安然退出
	while (!isOpen&&arrivegeneration==this.generation) {
		wait();
	}

}


}
