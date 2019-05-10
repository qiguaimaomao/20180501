package test1;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 同步容器类包括Vector和Hashtable，或者Collections.synchronizedXXX()等工厂方法创建。
 * 同步容器类是线程安全的，但在某些情况下可能需要额外的客户端加锁来保护复合操作。
 * @author jiuyv
 *
 */
public class Combination {
public static Object getLast(Vector vector) {
	//在调用size和get之间，容器的大小可能会发生改变
	int last=vector.size()-1;
	return vector.get(last);

}

public static Object removeLast(Vector vector) {
	//在调用size和get之间，容器的大小可能会发生改变
	int last=vector.size()-1;
	return vector.remove(last);

}

public   static void iteration(Vector vector) {
for (int i = 0; i < vector.size(); i++) {
	if (i==1) {
		removeLast(vector);
	}
	System.out.println(vector.get(i));
}
}

public static void iterationMap(ConcurrentHashMap<Integer, Integer> map) {

}

public static void main(String[] args) {
	Vector v=new Vector<>();
	for (int i = 0; i < 5; i++) {
		v.add(i);
	}
	iteration(v);
	for (int i = 0; i < 5; i++) {
		v.add(i);
	}
	//在单线程中也可能会抛出ConcurrentModificationException，当使用Iterator迭代时，没有使用Iterator删除而是直接删除时
	for (Object object : v) {
//		removeLast(v);
	}
	System.out.println(v);
	
	Hashtable table=new Hashtable<>();
	HashMap map=new HashMap<>();
	ConcurrentHashMap<Integer, Integer> map1=new ConcurrentHashMap<>();
	
	CopyOnWriteArrayList list=new CopyOnWriteArrayList<>();
}
}
