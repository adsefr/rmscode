package com.rms.base.util;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class NumberUtil {

	public static int convertInt(String value) {

		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean between(long number, long number1, long number2) {

		return number >= number1 && number <= number2;
	}
}
