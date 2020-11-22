package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter3;

public class UnSafeStates {
private String[] states= {"wk","wl"};

public String[] getStates() {
	return states;
}

public void setStates(String[] states) {
	this.states = states;
}

public static void main(String[] args) {
	UnSafeStates u=new UnSafeStates();
	u.getStates()[0]="123";
}
}
