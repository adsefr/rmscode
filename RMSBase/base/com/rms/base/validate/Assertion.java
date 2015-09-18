package com.rms.base.validate;

import java.io.File;

import com.rms.base.exception.InvalidException;
import com.rms.base.exception.NullParameterException;
import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.io.util.IOUtil;
import com.rms.base.validate.exception.UnexpectedFileTypeException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/07
 */
public class Assertion {

	/**
	 *
	 * @param name
	 *            項目名
	 * @param object
	 *            項目値
	 */
	public static void assertNotNull(String name, Object object) {

		if (object == null) {
			throw new NullParameterException(name + " is null.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param object
	 *            項目値
	 */
	public static void assertNotBlank(String name, String value) {

		if (value == null || value.isEmpty()) {
			throw new NullParameterException(name + " is null or empty.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param number
	 *            項目値
	 */
	public static void assertPositiveNumber(String name, long number) {

		if (number < 0) {
			throw new InvalidException(name + " is negative number.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param number
	 *            項目値
	 */
	public static void assertNegativeNumber(String name, long number) {

		if (number > 0) {
			throw new InvalidException(name + " is positive number.");
		}
	}

	/**
	 *
	 * @param expected
	 * @param actual
	 */
	public static void assertType(Class<?> expected, Class<?> actual) {

		if (expected == null && actual == null) {
			return;
		}

		if (expected == null && actual != null) {

			throw new UnexpectedTypeException("null", actual.getName());
		}

		if (expected != null && actual == null) {

			throw new UnexpectedTypeException(expected.getName(), "null");
		}

		if (expected != actual) {
			throw new UnexpectedTypeException(expected.getName(), actual.getName());
		}
	}

	public static void assertFile(String name, File file) {

		if (!IOUtil.isFile(file)) {
			throw new UnexpectedFileTypeException("expect that " + name + "is a file!!![" + file.toString() + "]");
		}
	}

	public static void assertDirectory(String name, File file) {

		if (!IOUtil.isDirectory(file)) {
			throw new UnexpectedFileTypeException("expect that " + name + "is a directory!!![" + file.toString() + "]");
		}
	}

	/**
	 * indexが範囲内かチェックする。<br>
	 * {@code minIndex}<={@code index}<={@code maxIndex}以外の場合、
	 * IndexOutOfBoundsExceptionが発生する。<br>
	 *
	 * @param index
	 *            チェック対象
	 * @param minIndex
	 *            最小Index
	 * @param maxIndex
	 *            最大Index
	 *
	 * @exception IndexOutOfBoundsException
	 */
	public static void isIndexOutOfBounds(int index, int minIndex, int maxIndex) {

		if (index < minIndex || index > maxIndex) {
			String message = "index:" + index + " minIndex:" + minIndex + " maxIndex:" + maxIndex;

			throw new IndexOutOfBoundsException(message);
		}
	}
}
