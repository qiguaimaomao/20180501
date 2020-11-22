package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter16;

public class ResourceFactory {
private static class ResourceHolder{
	static{
		System.out.println("ResourceHolder init");
	
	}
	public static Resource re=new Resource();
}

static{
	System.out.println("ResourceFactory init");
}

public static Resource getResource() {
	return ResourceHolder.re;

}


}
