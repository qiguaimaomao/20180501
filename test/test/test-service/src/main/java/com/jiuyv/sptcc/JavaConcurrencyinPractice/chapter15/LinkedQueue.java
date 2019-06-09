package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {
private static class Node<E>{
	final E item;
	final AtomicReference<Node<E>> next;
	public Node(E item,Node<E> next) {
		this.item=item;
		this.next=new AtomicReference<Node<E>>(next);
	}
}

private final Node<E> dummy=new Node<E>(null, null);
private final AtomicReference<Node<E>> head=new AtomicReference<Node<E>>(dummy);
private final AtomicReference<Node<E>> tail=new AtomicReference<Node<E>>(dummy);

public boolean  put(E item) {
	Node<E> newNode=new Node<E>(item, null);
	while (true) {
		Node<E> curtail=tail.get();
		Node<E> tailnext=curtail.next.get();
		if (tailnext!=null) {
			tail.compareAndSet(curtail, tailnext);
		}else{
			if (curtail.next.compareAndSet(null, newNode)) {
				tail.compareAndSet(curtail, null);
				return true;
			}
			
		}
	}

}

}
