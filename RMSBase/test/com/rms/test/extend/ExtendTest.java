package com.rms.test.extend;

public class ExtendTest {
public static void main(String[] args) {

	new C().print();
}
}

class A {

	private final String LOGGER_NAME = this.getClass().getName();
	public A() {
		print();
	}
	public void print() {

		System.out.println(LOGGER_NAME);
	}
}

class B extends A {

//	@Override
//	public void print() {
//
//		super.print();
//	}
}
class C extends B {

//	@Override
//	public void print() {
//
//		super.print();
//	}
}
