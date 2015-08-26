package com.rms.base.util;

public class Encode {

	private static byte[] convert(String str) {

		byte[] array = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			String a = str.substring(i, i + 1);
			if (i / 2 == 0) {
				array[i] = (byte) (0xF & Integer.parseInt(a, 16));
			} else {
				array[i] = (byte) (0xF & Integer.parseInt(a, 16));
			}
		}

		byte[] results = new byte[array.length / 2];
		for (int i = 0; i < results.length; i++) {
			results[i] = (byte) ((array[i * 2] << 4) | (array[i * 2 + 1]));
		}

		return results;
	}
}
