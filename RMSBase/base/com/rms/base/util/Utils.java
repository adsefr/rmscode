package com.rms.base.util;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/07
 */
public final class Utils {

	public static int binarySearch(String[] array, String search, int start, int end) {

		int resultIndex = -1;

		if (array == null || array.length == 0 || start > end) {
			return resultIndex;
		}

		int startIndex = start;
		int endIndex = end;
		int middleIndex = (startIndex + endIndex) / 2;
		int compare = array[middleIndex].compareTo(search);

		if (compare == 0) {
			resultIndex = middleIndex;

		} else {
			if (compare < 0) {
				startIndex = middleIndex + 1;
			} else if (compare > 0) {
				endIndex = middleIndex - 1;
			}

			if (startIndex >= start && endIndex <= end) {
				resultIndex = binarySearch(array, search, startIndex, endIndex);
			}
		}

		return resultIndex;
	}

}
