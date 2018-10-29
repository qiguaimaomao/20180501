package java8.chapter14;

import java.util.function.Supplier;

class LazyList<T> implements MyList<T>{
final T head;
final Supplier<MyList<T>> tail;
public LazyList(T head, Supplier<MyList<T>> tail) {
this.head = head;
this.tail = tail;
}
public T head() {
return head;
}
public MyList<T> tail() {
return tail.get();
}
public boolean isEmpty() {
return false;
}

public static LazyList<Integer> from(int n) {
return new LazyList<Integer>(n, () -> from(n+1));
}

public static void main(String[] args) {
	LazyList<Integer> numbers = from(2);
	int two = numbers.head();
	int three = numbers.tail().head();
	int four = numbers.tail().tail().head();
	System.out.println(two + " " + three + " " + four);
}

}