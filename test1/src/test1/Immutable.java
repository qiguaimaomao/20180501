package test1;

public class Immutable {
private static void f1(String a) {
	a="1234";

}
public static void main(String[] args) {
	String a="123";
	f1(a);
	System.out.println(a);
}
}
