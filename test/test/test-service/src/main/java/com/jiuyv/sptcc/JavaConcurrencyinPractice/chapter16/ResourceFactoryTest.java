package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter16;

public class ResourceFactoryTest {
public static void main(String[] args) {
	ResourceFactory rf=new ResourceFactory();
	System.out.println("rf init");
	ResourceFactory.getResource();
}
}
