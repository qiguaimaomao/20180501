package java8.chapter1;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.function.IntFunction;

public class MethodReference {
public static void main(String[] args) throws Exception {
	//方法引用File::isHidden作为值
	File[] hiddenfiles=new File("C:/").listFiles(File::isHidden);
	for (File file : hiddenfiles) {
		System.out.println(file.getName());
	}
	
	//Lambda——匿名函数
	m1((int x)-> x+1);
	Callable<String> r=()-> "abc";
	process(r);
}

private static void m1(java.util.function.IntFunction<Integer> f) {
	System.out.println(f.apply(1));
}

private IntFunction<Integer>  m2() {
	return (int x)->{
		return x+1;};

}

public static void process(Callable<String> r) throws Exception{
System.out.println(r.call());
}


}
