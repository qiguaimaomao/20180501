package test1;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {
public static void main(String[] args) {
	AtomicLong num=new AtomicLong();
	num.getAndIncrement();
	num.incrementAndGet();
}
}
