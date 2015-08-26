package jp.co.rnai.task.talend;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class JDBC {

	private final static String CONNECTION = "CONNECTION";

	private final static String TYPE_TEXT = "text";

	private final static String COMMA = ",";

	/**
	 * テーブルの項目を更新する前、値を変換する処理<br>
	 * [1]改行コードやタブコードを取り除く<br>
	 * [2]値に含まれる複数スペース(全角と半角)を１桁半角スペースに置き換え、前後のスペース(全角と半角)を取り除くく<br>
	 * [3]引数が空文字[""]の場合や1桁以上のスペースの場合、nullに変換する<br>
	 *
	 * @param content
	 * @return
	 */
	public static String convertItem(String content) {

		// nullの場合、変換なし
		if (content == null) {
			return content;
		}

		String converted = content;

		// 改行コードを取り除く
		converted = converted.replaceAll("\r\n", "").replaceAll("\n", "");
		// タブコードを取り除く
		converted = converted.replaceAll("\t", "");

		// スペースを置き換える
		converted = converted.replaceAll("[ 　]{2,}", " ");

		// 頭スペースを取り除く
		converted = Utils.trimLeft(' ', converted);
		converted = Utils.trimLeft('　', converted);

		// 末尾のスペースを取り除く
		converted = Utils.trimRight(' ', converted);
		converted = Utils.trimRight('　', converted);

		// 結果を返す
		return (converted.isEmpty()) ? null : converted;
	}

	public static String convertItem(String content, boolean parseHexBinary) {

		String converted = content;
		if (parseHexBinary) {
			converted = XMLUtil.parseHexBinary(converted);
		}

		return convertItem(converted);
	}

	public static Array convert2TextArray(Map<String, Object> globalMap, Object content) throws SQLException {

		return createTextArray(globalMap, content, false);
	}

	public static Array convert2TextArray(Map<String, Object> globalMap, Object content, boolean parseHexBinary)
			throws SQLException {

		return createTextArray(globalMap, content, parseHexBinary);
	}

	/**
	 * String型の値を区切り文字[,]で分割して、テキスト配列の値に変換する。
	 *
	 * @param globalMap
	 *            コンテキスト
	 * @param content
	 *            変換対象
	 * @param parseHexBinary
	 *
	 * @return
	 * @throws SQLException
	 */
	private static Array createTextArray(Map<String, Object> globalMap, Object content, boolean parseHexBinary)
			throws SQLException {

		if (content == null || content.getClass() != String.class) {
			return null;
		}

		String text = (String) content;

		if (text.isEmpty()) {
			return null;
		}

		String[] array = ((String) content).split(COMMA, -1);

		if (parseHexBinary) {
			array = XMLUtil.parseHexBinary(array);
		}

		if (array.length == 0) {
			return null;
		}

		for (int i = 0; i < array.length; i++) {
			array[i] = convertItem(array[i]);
		}

		Connection connection = (Connection) globalMap.get(CONNECTION);
		return connection.createArrayOf(TYPE_TEXT, array);
	}
}
