package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3.OneValueCache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;
/**
 * @author jiuyv
 */
@ThreadSafe
public class VolatileCachedFactorizer {
private volatile OneValueCache cache=new OneValueCache(null, null); 

		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		public ServletConfig getServletConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public synchronized void  service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			BigInteger i=exetractFromRequset(req);
			BigInteger[] factors=cache.getFactors(i);
			if(factors==null) {
				factors=factor(i);
				cache=new OneValueCache(i, factors);
				
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
