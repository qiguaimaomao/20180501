package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.Immutable;
/**
 * 只有满足以下状态，一个对象才是不可变的
 * 1.它的状态不能在创建后再修改
 * 2.所有域都是final类型
 * 3.它被正确创建（没有this引用逸出）
 * @author jiuyv
 *
 */
@Immutable
public final class ThreeStooges {
private final Set<String> stooges=new HashSet<>();
public ThreeStooges() {
	stooges.add("a");
	stooges.add("b");
	stooges.add("c");
}
 public boolean isStooge(String name){
	return stooges.contains(name);
	
}
}
