package com.rms.base.constant;

import java.nio.charset.Charset;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/31
 */
public final class Encodes {

	public static final String ENCODE_UTF8 = "UTF-8";

	public static final String ENCODE_SJIS = "SJIS";

	public static final Charset CHARSET_UTF8 = Charset.forName(ENCODE_UTF8);

	public static final Charset CHARSET_SJIS = Charset.forName(ENCODE_SJIS);
}
