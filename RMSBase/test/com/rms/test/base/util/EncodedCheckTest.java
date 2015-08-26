package com.rms.test.base.util;

import com.rms.base.constant.Encodes;
import com.rms.base.util.Utils;

public class EncodedCheckTest {

	public static void main(String[] args) {

		System.out.println(Utils.isEncode("铭", Encodes.CHARSET_SJIS));
		System.out.println(Utils.isEncode("銘", Encodes.CHARSET_SJIS));
	}
}
