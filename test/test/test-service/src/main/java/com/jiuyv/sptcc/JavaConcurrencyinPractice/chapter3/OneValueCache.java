package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

import java.math.BigInteger;

import net.jcip.annotations.Immutable;

@Immutable
public class OneValueCache {
private final BigInteger lastNumber;
private final BigInteger[] lastFactors;
public OneValueCache(BigInteger lastNumber,BigInteger[] lastFactors) {
	this.lastNumber=lastNumber;
	this.lastFactors=lastFactors.clone();
}

public BigInteger[] getFactors(BigInteger i) {
	if (i==null||!i.equals(lastNumber)) {
		return null;
	}
	return lastFactors.clone();
}
}
