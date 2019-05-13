package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

/**
 * 一个正确创建的对象可通过下列条件安全的发布
 * 1.通过静态初始化器初始化对象的引用
 * 2.将它的引用存储到volatile或者AtomicReference内
 * 3.将它的引用存储到正确创建的对象的final域中
 * 4.将它的引用存储到由锁正确保护的域中（比如线程安全的容器）
 * @author jiuyv
 *
 */
public class UnSafePublish {
public Holder holder;
public void initialize() {
	holder=new Holder(42);

}
}
