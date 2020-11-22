package test1;

import java.util.Arrays;
import java.util.HashMap;

public class HashMapTable {
public static void main(String[] args) {
	HashMap<String, String> hashmap=new HashMap<>();
	hashmap.put(null, null);
	System.out.println(hashmap);
	
	
	int[] a= {7,2,3,4,5,6,1};
	int b=Arrays.stream(a).sorted().skip(a.length/2).findFirst().getAsInt();
	System.out.println(b);
}
}
