package java8.chapter1;

import java.io.File;

public class MethodReference {
public static void main(String[] args) {
	//方法引用File::isHidden作为值
	File[] hiddenfiles=new File("C:/").listFiles(File::isHidden);
	for (File file : hiddenfiles) {
		System.out.println(file.getName());
	}
	
	//Lambda——匿名函数
	m1((int x)-> x+1);
}

private static void m1(java.util.function.IntFunction<Integer> f) {
	System.out.println(f.apply(1));
}
}
