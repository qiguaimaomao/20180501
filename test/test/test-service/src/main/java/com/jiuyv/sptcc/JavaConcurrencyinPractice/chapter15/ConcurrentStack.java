package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter15;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {
private static class Node<E>{
	public final E item;
	public Node<E> next;
	public Node(E value) {
		this.item=value;
	}
}

private final AtomicReference<Node<E>> top=new AtomicReference<ConcurrentStack.Node<E>>();
public void push(E e) {
	Node<E> newhead=new Node<E>(e);
	Node<E> oldhead;
	do {
		oldhead=top.get();
		newhead.next=oldhead;
	} while (!top.compareAndSet(oldhead, newhead));

}

public E pop() {
	Node<E> newhead;
	Node<E> oldhead;
	do {
		oldhead=top.get();
		if (oldhead==null) {
			return null;
		}
		newhead=oldhead.next;
	} while (!top.compareAndSet(oldhead, newhead));
	return oldhead.item;
}
}
