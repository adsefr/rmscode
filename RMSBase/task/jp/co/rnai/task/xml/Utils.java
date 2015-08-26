package jp.co.rnai.task.xml;

import java.util.Arrays;
import java.util.List;

public class Utils {

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
}
