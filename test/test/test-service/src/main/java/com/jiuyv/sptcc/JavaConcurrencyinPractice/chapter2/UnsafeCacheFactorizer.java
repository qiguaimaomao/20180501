package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.NotThreadSafe;
/**
 * 
 * @author jiuyv
 *当一个不变约束涉及到多个变量时，变量之间不是彼此独立的，某个变量的值会制约其他几个变量的值。
 *因此更新一个变量的时候，要在同一原子操作中更新其他几个。
 */
@NotThreadSafe
public class UnsafeCacheFactorizer {
	/**
	 * 存在不变约束：lastFactors中的各个因子的乘积应该等于lastNumber
	 */
	private final AtomicReference<BigInteger> lastNumber=new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors=new AtomicReference<BigInteger[]>();

		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		public ServletConfig getServletConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			BigInteger i=exetractFromRequset(req);
			if (i.equals(lastNumber.get())) {
				encodeIntoRespose(res,lastFactors.get());
			}else {
				BigInteger[] factors=factor(i);
				lastNumber.set(i);
				lastFactors.set(factors);
				encodeIntoRespose(res,factors);
			}
			
		}

		private void encodeIntoRespose(ServletResponse res, BigInteger[] factors) {
			// TODO Auto-generated method stub
			
		}

		private BigInteger[] factor(BigInteger i) {
			// TODO Auto-generated method stub
			return null;
		}

		private BigInteger exetractFromRequset(ServletRequest req) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getServletInfo() {
			// TODO Auto-generated method stub
			return null;
		}

		public void destroy() {
			// TODO Auto-generated method stub
			
		}

}
