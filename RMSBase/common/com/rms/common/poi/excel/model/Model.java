package com.rms.common.poi.excel.model;

/**
 * 
 * @author ri.meisei
 * @since 2014/01/07
 */
public interface Model extends Cloneable {

	/**
	 * インスタンス自体をコピーする。
	 * 
	 * @return
	 */
	public Model copy();

	/**
	 * 項目にデフォルト値を設定する。
	 */
	public void reSet();
}
