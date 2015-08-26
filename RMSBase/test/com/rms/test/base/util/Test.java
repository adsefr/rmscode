package com.rms.test.base.util;


public class Test {

	public static void main(String[] args) {
		try {
			String[] array = new  String[0];
			Long.parseLong("-5");
			array[5]= "";
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}

}
