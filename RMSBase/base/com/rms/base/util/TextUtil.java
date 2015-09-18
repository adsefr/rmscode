package com.rms.base.util;

import com.rms.base.validate.Assertion;

public class TextUtil {

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isBlank(String input) {

		return (input == null || input.isEmpty());
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isNotBlank(String input) {

		return (input != null && !input.isEmpty());
	}

	public static String trim(String input) {

		if (!isBlank(input)) {
			return input.trim();
		}

		return input;
	}

	public static boolean equals(String str1, String str2) {

		if ((str1 == null) ^ (str2 == null)) {
			return false;
		}

		if (str1 != null) {
			return str1.equals(str2);
		}

		return true;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEquals(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equals(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equals(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equalsIgnoreCase(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equalsIgnoreCase(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	/**
	 * nullの場合、空文字に返す。
	 *
	 * @return
	 */
	public static String repaceNull(String input) {

		String result = input;
		if (input == null) {
			result = "";
		}

		return result;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = " " + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length, String padSequence) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = padSequence + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String rPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult += " ";
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param times
	 * @return
	 */
	public static String repeat(String input, int times) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			builder.append(input);
		}
		return builder.toString();
	}

	/**
	 *
	 * @param content
	 * @return
	 */
	public static String capital(String content) {

		Assertion.assertNotNull("content", content);

		if (content.isEmpty()) {
			return "";
		}

		StringBuilder sBuilder = new StringBuilder(content);

		String firstChar = sBuilder.deleteCharAt(0).toString().toUpperCase();

		sBuilder.insert(0, firstChar);

		return sBuilder.toString();
	}
}
