package test1;

import java.math.BigInteger;

public class OneValueCache {
private final BigInteger lastNumber;
private final BigInteger[] lastFactors;
public OneValueCache(BigInteger i,BigInteger[] lastFactors) {
	this.lastNumber=i;
	this.lastFactors=lastFactors.clone();
}
public BigInteger[] getLastFactors(BigInteger i) {
	if (lastNumber==null||!lastNumber.equals(i)) {
		return null;
	}
	return lastFactors.clone();
}

}
