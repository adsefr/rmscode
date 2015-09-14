package com.rms.base.io.file;

/**
 * ファイルに関する定数を定義クラス
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public class FileConst {

	public enum FileType {
		FILE, DIRECTORY, BOTH;
	}

	public enum FileOperateType {
		BYTE_READ, CHAR_READ, LINE_READ,

		BYTE_WRITE, CHAR_WRTIE, LINE_WRTIE,
	}

}
