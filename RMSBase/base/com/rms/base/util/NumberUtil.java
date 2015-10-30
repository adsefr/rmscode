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

	public static String convertNumberFullToHalf(String number) {

		if (number == null) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer(number);
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if ('０' <= c && c <= '９') {
				sb.setCharAt(i, (char) (c - '０' + '0'));
			}
		}
		return sb.toString();
	}

	public static String convertNumberHalfToFull(String number) {

		if (number == null) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer(number);
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if ('0' <= c && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}

	public static String convertNumberHalfToFull(int number) {

		return convertNumberHalfToFull(String.valueOf(number));
	}
}
