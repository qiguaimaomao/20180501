package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.jcip.annotations.ThreadSafe;
/**
 * 只有我们的类持有底层list的唯一外部引用，那么就能确保线程安全性。
 * @author jiuyv
 *
 * @param <E>
 */
@ThreadSafe
public class ImprovedList<E> implements List<E>{
private final List<E> list ;
public ImprovedList(List<E> list1) {
	this.list=list1;
}
/**
 * 需要把所有方法外面再套一层锁
 * @param e
 * @return
 */
public synchronized boolean putIfAbsent(E e) {
	boolean absent=list.contains(e);
	if (!absent) {
		list.add(e);
	}
	return absent;

}

@Override
public int size() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean contains(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Iterator<E> iterator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Object[] toArray() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> T[] toArray(T[] a) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean add(E e) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean remove(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean containsAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean addAll(Collection<? extends E> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean addAll(int index, Collection<? extends E> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean removeAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean retainAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	
}

@Override
public E get(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E set(int index, E element) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void add(int index, E element) {
	// TODO Auto-generated method stub
	
}

@Override
public E remove(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int indexOf(Object o) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int lastIndexOf(Object o) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public ListIterator<E> listIterator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ListIterator<E> listIterator(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<E> subList(int fromIndex, int toIndex) {
	// TODO Auto-generated method stub
	return null;
}
}
