package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.math.BigInteger;
import java.util.List;

public class PrimeGeneratorTest {
public List<BigInteger> aSecondOfPrimes() throws InterruptedException {
	PrimeGenerator p=new PrimeGenerator();
	new Thread(p).start();
	try {
		Thread.sleep(1000L);
	} finally {
		p.cancel();
	}
	return p.get();

}
}
