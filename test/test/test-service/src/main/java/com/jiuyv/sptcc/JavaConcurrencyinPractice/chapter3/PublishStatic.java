package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * 最常见的发布对象的方式是将对象的引用存储到公共静态域中。
 * @author jiuyv
 *
 */
public class PublishStatic {
public static Set<Secret> knownSecrets;
public void initialize() {
	knownSecrets=new HashSet<Secret>();
}
}
