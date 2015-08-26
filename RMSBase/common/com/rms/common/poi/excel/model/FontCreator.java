package com.rms.common.poi.excel.model;

import java.awt.Color;

import com.rms.common.poi.excel.enums.BoldType;
import com.rms.common.poi.excel.enums.UnderlineType;

/**
 * 文字情報作成インタフェース
 * 
 * @author ri.meisei
 * @since 2013/12/27
 */
public interface FontCreator {

	/**
	 * フォント名を取得する。
	 * 
	 * @return 文字名
	 */
	String getFontName();

	/**
	 * 文字サイズを取得する。
	 * 
	 * @return 文字サイズ
	 */
	double getFontSize();

	/**
	 * 文字色を取得する。
	 * 
	 * @return 文字色
	 */
	Color getFontColor();

	/**
	 * 太字を取得する。
	 * 
	 * @return 太字タイプ
	 */
	BoldType getBoldType();

	/**
	 * 斜体を取得する。
	 * 
	 * @return 斜体フラグ true:斜体 false:正常
	 */
	boolean isItalic();

	/**
	 * 取り消し線を取得する。
	 * 
	 * @return 取り消し線フラグ true:取り消し線あり false:取り消し線なし
	 */
	boolean isStrikeout();

	/**
	 * 下線を取得する。
	 * 
	 * @return 下線タイプ
	 */
	UnderlineType getUnderlineType();

}
