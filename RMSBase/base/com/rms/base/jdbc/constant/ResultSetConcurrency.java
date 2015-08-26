package com.rms.base.jdbc.constant;

import java.sql.ResultSet;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/24
 */
public enum ResultSetConcurrency {
	CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY), //

	CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE), //
	;

	private int type;

	private ResultSetConcurrency(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public final int getType() {

		return type;
	}

}
