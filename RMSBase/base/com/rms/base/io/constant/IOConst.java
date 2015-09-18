package com.rms.base.io.constant;

import java.nio.charset.Charset;

import com.rms.base.constant.Characters;
import com.rms.base.constant.Encodes;

/**
 * ファイルに関する定数を定義クラス
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public class IOConst {

	public final static int DEFAULT_BUFFER_SIZE = 8192;

	public final static String DEFAULT_LINE_SEPARATOR = Characters.LINE_SEPARATOR_SYSTEM;

	public final static Charset DEFAULT_CHARSET = Charset.forName(Encodes.ENCODE_UTF8);
}
