package com.rms.test.base.util;

import com.rms.base.util.Utils;

public class BinarySearchTest {

	public static void main(String[] args) {

		System.out.println(Integer.parseInt("C287", 16));
		String[] array = new String[] { "0", "1", "2", "3", "4", "5" };
		int result = Utils.binarySearch(array, "0", 0, array.length - 1);
		System.out.println(result);
		result = Utils.binarySearch(array, "1", 0, array.length - 1);
		System.out.println(result);
		result = Utils.binarySearch(array, "2", 0, array.length - 1);
		System.out.println(result);
		result = Utils.binarySearch(array, "3", 0, array.length - 1);
		System.out.println(result);
		result = Utils.binarySearch(array, "4", 0, array.length - 1);
		System.out.println(result);
		result = Utils.binarySearch(array, "5", 0, array.length - 1);
		System.out.println(result);
	}
}
