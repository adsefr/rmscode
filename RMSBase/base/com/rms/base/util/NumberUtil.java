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
}
