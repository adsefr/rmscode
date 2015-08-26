package jp.co.rnai.task.talend;

import java.util.Arrays;
import java.util.List;

public class Utils {

	public static boolean isBlank(String content) {

		return content == null || content.trim().isEmpty();
	}

	public static String[] split(Object value, String split) {

		String content = (String) value;

		return content.split(split);
	}

	/**
	 *
	 * @param delimitedChar
	 * @param list
	 * @return
	 */
	public static String join(String delimitedChar, List<String> list) {

		if (list == null || list.isEmpty()) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size() - 1; i++) {
			builder.append(list.get(i)).append(delimitedChar);
		}
		builder.append(list.get(list.size() - 1));

		return builder.toString();
	}

	/**
	 *
	 * @param delimitedChar
	 * @param array
	 * @return
	 */
	public static String join(String delimitedChar, String... array) {

		if (array == null || array.length == 0) {
			return "";
		}

		return join(delimitedChar, Arrays.asList(array));
	}

	public static String trim(char c, String str) {

		return trimRight(c, trimLeft(c, str));
	}

	public static String trimLeft(char c, String str) {

		if (str == null || str.isEmpty()) {
			return str;
		}

		StringBuilder builder = new StringBuilder(str);
		while (builder.length() > 0 && builder.charAt(0) == c) {
			builder.delete(0, 1);
		}

		return builder.toString();
	}

	public static String trimRight(char c, String str) {

		if (str == null || str.isEmpty()) {
			return str;
		}

		StringBuilder builder = new StringBuilder(str);
		while (builder.length() > 0 && builder.charAt(builder.length() - 1) == c) {
			builder.delete(builder.length() - 1, builder.length());
		}

		return builder.toString();
	}

	/**
	 * 値がnullの場合のみ、書き換え文字列に変換する。
	 *
	 * @param content
	 *            チェック対象
	 * @param replace
	 *            書き換え文字列
	 * @return
	 */
	public static String replaceNull(String content, String replace) {

		return (content == null) ? replace : content;
	}

	/**
	 * 値がnullの場合や[""]空白の場合、書き換え文字列に変換する。
	 *
	 * @param content
	 *            チェック対象
	 * @param replace
	 *            書き換え文字列
	 * @return
	 */
	public static String replaceBlank(String content, String replace) {

		return (content == null || content.isEmpty()) ? replace : content;
	}

	public static String replaceAll(String content, String toString, String... replaced) {

		if (content == null || replaced == null) {
			return content;
		}

		String result = content;
		for (String replace : replaced) {
			result = result.replaceAll(replace, toString);
		}

		return result;
	}
}
