package com.rms.test.encoding;

import java.io.UnsupportedEncodingException;

public class DefaultCharSet {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String a = "⑤";
		byte[] array = convert("E291A4");
		String b = new String(array, "UTF-8");
		System.out.println((int)b.charAt(0));
		System.out.println(a + " " + b);
	}

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
