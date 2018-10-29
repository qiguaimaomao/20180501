package java8.chapter14;

import java.util.function.Predicate;

public interface MyList<T> {
T head();
MyList<T> tail();

default boolean isEmpty() {
return true;
}
MyList<T> filter(Predicate<T> p);
}
