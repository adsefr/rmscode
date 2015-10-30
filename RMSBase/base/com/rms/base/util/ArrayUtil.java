package com.rms.base.util;

import java.lang.reflect.Array;
import java.util.List;

import com.rms.base.validate.Assertion;

/**
 * 配列に関する操作共通クラス
 *
 * @author ri.meisei
 * @since 2014/01/07
 */
public class ArrayUtil {

	/**
	 * 配列が空かチェックする。<br>
	 * パラメータが配列出ない場合、falseを返す。<br>
	 *
	 * @param array
	 *            チェック対象配列
	 * @return 配列がnullか長さが0の場合はtrueを返す。それ以外の場合はfalseを返す。
	 */
	public static boolean isEmpty(Object array) {

		if (array == null) {
			return true;
		}

		if (!array.getClass().isArray()) {
			return false;
		}

		int length = Array.getLength(array);

		return (length == 0);
	}

	/**
	 * 配列の元素がすべてnullかチェックする。
	 *
	 * @param array
	 *            チェック対象配列
	 * @return 配列がnullか長さが０の場合または配列の元素がすべてnullの場合はtrueを返す。それ以外の場合はfalseを返す。
	 */
	public static <T> boolean allNull(T[] array) {

		if (array == null || array.length == 0) {
			return true;
		}

		for (T t : array) {
			if (t == null) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 配列にはnullの元素があるかチェックする。
	 *
	 * @param array
	 *            チェック対象配列 * @return 配列がnullか長さが０の場合またはnullの元素がある場合はtrueを返す。それ以外の場合はfalseを返す。
	 */
	public static <T> boolean hasEmpty(T[] array) {

		if (array == null || array.length == 0) {
			return true;
		}

		for (T t : array) {
			if (t == null) {
				return true;
			}
		}

		return false;
	}

	public static <T> void add(List<T> collection, int index, T element) {

		Assertion.assertNotNull("collection", collection);
		Assertion.assertPositiveNumber("index", index);

		while (collection.size() < index) {
			collection.add(null);
		}

		collection.add(index, element);
	}

	public static String join(List<?> list, String seperator) {

		return join(list, seperator, false, null);
	}

	public static String join(List<?> list, String seperator, final Boolean isSkipNullElement) {

		return join(list, seperator, isSkipNullElement, null);
	}

	public static String join(List<?> list, String seperator, String replaceNullValue) {

		return join(list, seperator, false, replaceNullValue);
	}

	private static String join(List<?> list, String seperator, final Boolean isSkipNullElement, String replaceNullValue) {

		Assertion.assertNotNull("list", list);
		Assertion.assertNotNull("seperator", seperator);

		if (list.isEmpty()) {
			return "";
		}

		boolean isSkipNull = (isSkipNullElement == null) ? false : isSkipNullElement;

		StringBuilder sBuilder = new StringBuilder();

		for (Object element : list) {

			if (element == null) {
				if (isSkipNull) {
					continue;
				} else if (replaceNullValue != null) {
					element = replaceNullValue;
				}
			}

			sBuilder.append(String.valueOf(element)).append(seperator);
		}

		sBuilder.delete(sBuilder.length() - seperator.length(), sBuilder.length());

		return sBuilder.toString();
	}
}
