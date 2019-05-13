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
 * 缩小synchronized块，去掉原子变量
 * 使用两种不同的同步机制会引起混淆，而性能与安全也不会从中得到额外的好处
 * @author jiuyv
 */
@ThreadSafe
public class CachedFactorizer {
	/**
	 * 存在不变约束：lastFactors中的各个因子的乘积应该等于lastNumber
	 */
	@GuardedBy("this")
	private BigInteger lastNumber;
	@GuardedBy("this")
	private BigInteger[] lastFactors;
	@GuardedBy("this")
	private long hits;
	@GuardedBy("this")
	private long cacheHits;
	
	public synchronized long getHits() {
		return hits;
	}
	
	public double getCacheHitsRatio() {
		return (double)cacheHits/(double)hits;
	}
	
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		public ServletConfig getServletConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public  void  service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			BigInteger i=exetractFromRequset(req);
			BigInteger[] factors=null;
			synchronized (this) {
				hits++;
				if (i.equals(lastNumber)) {
					cacheHits++;
					factors=lastFactors.clone();
				}
			}
			
			if (factors==null) {
				factors=factor(i);
				synchronized (this) {
					lastNumber=i;
					lastFactors=factors.clone();
				}
				
			}
			encodeIntoRespose(res,factors);	
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
