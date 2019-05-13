package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;
/**
 * 内部锁，扮演互斥锁的角色
 * synchronized块有两个部分，锁对象的引用，以及这个锁保护的代码块
 * 性能不好
 * @author jiuyv
 */
@ThreadSafe
public class SynchronizedFactorizer {
	/**
	 * 存在不变约束：lastFactors中的各个因子的乘积应该等于lastNumber
	 */
	@GuardedBy("this")
	private final AtomicReference<BigInteger> lastNumber=new AtomicReference<BigInteger>();
	@GuardedBy("this")
	private final AtomicReference<BigInteger[]> lastFactors=new AtomicReference<BigInteger[]>();

		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		public ServletConfig getServletConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public synchronized void  service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
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
