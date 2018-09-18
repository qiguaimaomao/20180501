package java8.chapter1;

import java.io.File;

public class MethodReference {
public static void main(String[] args) {
	//��������File::isHidden��Ϊֵ
	File[] hiddenfiles=new File("C:/").listFiles(File::isHidden);
	for (File file : hiddenfiles) {
		System.out.println(file.getName());
	}
	
	//Lambda������������
	m1((int x)-> x+1);
}

private static void m1(java.util.function.IntFunction<Integer> f) {
	System.out.println(f.apply(1));
}
}
