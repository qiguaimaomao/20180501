package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter5;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;
/**
 * 
 * @author jiuyv
 */
@ThreadSafe
public class Factorizer {
	private final Computable<BigInteger, BigInteger[]> c=new Computable<BigInteger, BigInteger[]>() {

		@Override
		public BigInteger[] compute(BigInteger arg) throws InterruptedException {
			return factor(arg);
		}
	};

	private Memoizer4<BigInteger, BigInteger[]> memoizer4=new Memoizer4<>(c);
	
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		public ServletConfig getServletConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			BigInteger i=exetractFromRequset(req);
			try {
				encodeIntoRespose(res,memoizer4.compute(i));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
