package com.rms.base.util;

import com.rms.base.validate.Assertion;

public class TextUtil {

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isBlank(String input) {

		return (input == null || input.isEmpty());
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isNotBlank(String input) {

		return (input != null && !input.isEmpty());
	}

	public static String trim(String input) {

		if (!isBlank(input)) {
			return input.trim();
		}

		return input;
	}

	public static boolean equals(String str1, String str2) {

		if ((str1 == null) ^ (str2 == null)) {
			return false;
		}

		if (str1 != null) {
			return str1.equals(str2);
		}

		return true;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEquals(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equals(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equals(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	public static boolean isNotEquals(String... array) {

		return !isEquals(array);
	}

	public static String deleteLast(String content, int length) {

		return null;
	}

	public static String deleteFirst(String content, int length) {

		return null;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equalsIgnoreCase(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equalsIgnoreCase(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	/**
	 * nullの場合、空文字に返す。
	 *
	 * @return
	 */
	public static String repaceNull(String input) {

		String result = input;
		if (input == null) {
			result = "";
		}

		return result;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = " " + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length, String padSequence) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = padSequence + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String rPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult += " ";
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param times
	 * @return
	 */
	public static String repeat(String input, int times) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			builder.append(input);
		}
		return builder.toString();
	}

	/**
	 *
	 * @param content
	 * @return
	 */
	public static String capital(String content) {

		Assertion.assertNotNull("content", content);

		if (content.isEmpty()) {
			return "";
		}

		StringBuilder sBuilder = new StringBuilder(content);

		String firstChar = sBuilder.deleteCharAt(0).toString().toUpperCase();

		sBuilder.insert(0, firstChar);

		return sBuilder.toString();
	}

	public static String convertHalfToFull(String content) {

		if (isBlank(content)) {
			return content;
		}

		content = NumberUtil.convertNumberHalfToFull(content);
		content = HanToZenForKKana(content);
		content = hankakuAlphabetToZenkakuAlphabet(content);

		content = content.replace(" ", "　");
		content = content.replace("(", "（");
		content = content.replace(")", "）");

		return content;
	}

	public static String hankakuAlphabetToZenkakuAlphabet(String s) {

		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				sb.setCharAt(i, (char) (c - 'a' + 'ａ'));
			} else if (c >= 'A' && c <= 'Z') {
				sb.setCharAt(i, (char) (c - 'A' + 'Ａ'));
			}
		}
		return sb.toString();
	}

	public static String zenkakuAlphabetToHankaku(String s) {

		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ａ' && c <= 'ｚ') {
				sb.setCharAt(i, (char) (c - 'ａ' + 'a'));
			} else if (c >= 'Ａ' && c <= 'Ｚ') {
				sb.setCharAt(i, (char) (c - 'Ａ' + 'A'));
			}
		}
		return sb.toString();
	}

	private static final String kanaHanZenTbl[][] = {
			// 2文字構成の濁点付き半角カナ
			// 必ずテーブルに先頭に置いてサーチ順を優先すること
			{ "ｶﾞ", "ガ" }, { "ｷﾞ", "ギ" }, { "ｸﾞ", "グ" }, { "ｹﾞ", "ゲ" }, { "ｺﾞ", "ゴ" },
			{ "ｻﾞ", "ザ" }, { "ｼﾞ", "ジ" }, { "ｽﾞ", "ズ" }, { "ｾﾞ", "ゼ" }, { "ｿﾞ", "ゾ" },
			{ "ﾀﾞ", "ダ" }, { "ﾁﾞ", "ヂ" }, { "ﾂﾞ", "ヅ" }, { "ﾃﾞ", "デ" }, { "ﾄﾞ", "ド" },
			{ "ﾊﾞ", "バ" }, { "ﾋﾞ", "ビ" }, { "ﾌﾞ", "ブ" }, { "ﾍﾞ", "ベ" }, { "ﾎﾞ", "ボ" },
			{ "ﾊﾟ", "パ" }, { "ﾋﾟ", "ピ" }, { "ﾌﾟ", "プ" }, { "ﾍﾟ", "ペ" }, { "ﾎﾟ", "ポ" },
			{ "ｳﾞ", "ヴ" },
			// 1文字構成の半角カナ
			{ "ｱ", "ア" }, { "ｲ", "イ" }, { "ｳ", "ウ" }, { "ｴ", "エ" }, { "ｵ", "オ" },
			{ "ｶ", "カ" }, { "ｷ", "キ" }, { "ｸ", "ク" }, { "ｹ", "ケ" }, { "ｺ", "コ" },
			{ "ｻ", "サ" }, { "ｼ", "シ" }, { "ｽ", "ス" }, { "ｾ", "セ" }, { "ｿ", "ソ" },
			{ "ﾀ", "タ" }, { "ﾁ", "チ" }, { "ﾂ", "ツ" }, { "ﾃ", "テ" }, { "ﾄ", "ト" },
			{ "ﾅ", "ナ" }, { "ﾆ", "ニ" }, { "ﾇ", "ヌ" }, { "ﾈ", "ネ" }, { "ﾉ", "ノ" },
			{ "ﾊ", "ハ" }, { "ﾋ", "ヒ" }, { "ﾌ", "フ" }, { "ﾍ", "ヘ" }, { "ﾎ", "ホ" },
			{ "ﾏ", "マ" }, { "ﾐ", "ミ" }, { "ﾑ", "ム" }, { "ﾒ", "メ" }, { "ﾓ", "モ" },
			{ "ﾔ", "ヤ" }, { "ﾕ", "ユ" }, { "ﾖ", "ヨ" },
			{ "ﾗ", "ラ" }, { "ﾘ", "リ" }, { "ﾙ", "ル" }, { "ﾚ", "レ" }, { "ﾛ", "ロ" },
			{ "ﾜ", "ワ" }, { "ｦ", "ヲ" }, { "ﾝ", "ン" },
			{ "ｧ", "ァ" }, { "ｨ", "ィ" }, { "ｩ", "ゥ" }, { "ｪ", "ェ" }, { "ｫ", "ォ" },
			{ "ｬ", "ャ" }, { "ｭ", "ュ" }, { "ｮ", "ョ" }, { "ｯ", "ッ" },
			{ "｡", "。" }, { "｢", "「" }, { "｣", "」" }, { "､", "、" }, { "･", "・" },
			{ "ｰ", "ー" }, { "", "" }
	};

	public static String HanToZenForKKana(String p) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0, j = 0; i < p.length(); i++) {
			Character c = new Character(p.charAt(i));
			// Unicode半角カタカナのコード範囲か？
			if (c.compareTo(new Character((char) 0xff61)) >= 0
					&& c.compareTo(new Character((char) 0xff9f)) <= 0) {
				// 半角全角変換テーブルを検索する
				for (j = 0; j < kanaHanZenTbl.length; j++) {
					if (p.substring(i).startsWith(kanaHanZenTbl[j][0])) {
						sb.append(kanaHanZenTbl[j][1]);
						i += kanaHanZenTbl[j][0].length() - 1;
						break;
					}
				}

				// 検索できなければ、変換しない
				if (j >= kanaHanZenTbl.length) {
					sb.append(c);
				}
			} else { // Unicode半角カタカナ以外なら変換しない
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String ZenToHanForKKana(String p) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = 0; i < p.length(); i++) {
			Character c = new Character(p.charAt(i));
			// Unicode全角カタカナのコード範囲か?
			if (c.compareTo(new Character((char) 0x30a1)) >= 0
					&& c.compareTo(new Character((char) 0x30fc)) <= 0) {
				// 半角全角変換テーブルを検索する
				for (j = 0; j < kanaHanZenTbl.length; j++) {
					if (p.substring(i).startsWith(kanaHanZenTbl[j][1])) {
						sb.append(kanaHanZenTbl[j][0]);
						break;
					}
				}
				// 検索できなければ、変換しない
				if (j >= kanaHanZenTbl.length) {
					sb.append(c);
				}
			} else { // 全角カタカナ以外なら変換しない
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
