package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;
/**
 * 改成原子变量解决问题
 * @author jiuyv
 *
 */
@ThreadSafe
public class CountingFactorizer implements Servlet{
private final AtomicLong count=new AtomicLong(0);
public long getCount() {
	return count.get();
}
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		BigInteger i=exetractFromRequset(req);
		BigInteger[] factors=factor(i);
		//读-改-写
		count.incrementAndGet();
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
