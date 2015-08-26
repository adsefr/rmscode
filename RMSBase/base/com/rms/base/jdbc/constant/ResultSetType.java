package com.rms.base.jdbc.constant;

import java.sql.ResultSet;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/24
 */
public enum ResultSetType {
	TYPE_FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY), //

	TYPE_SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE), //

	TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE), //
	;

	private int type = -1;

	private ResultSetType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public final int getType() {

		return type;
	}

}
