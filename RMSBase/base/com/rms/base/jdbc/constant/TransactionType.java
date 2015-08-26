package com.rms.base.jdbc.constant;

import java.sql.Connection;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public enum TransactionType {

	TRANSACTION_NONE(Connection.TRANSACTION_NONE), //

	TRANSACTION_READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED), //

	TRANSACTION_READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED), //

	TRANSACTION_REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ), //

	TRANSACTION_SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE), //

	;

	private int type;

	private TransactionType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public final int getType() {

		return type;
	}

	public static TransactionType valueOf(int type) {

		switch (type) {
		case Connection.TRANSACTION_NONE:
			return TRANSACTION_NONE;
		case Connection.TRANSACTION_READ_COMMITTED:
			return TRANSACTION_READ_COMMITTED;
		case Connection.TRANSACTION_READ_UNCOMMITTED:
			return TRANSACTION_READ_UNCOMMITTED;
		case Connection.TRANSACTION_REPEATABLE_READ:
			return TRANSACTION_REPEATABLE_READ;
		case Connection.TRANSACTION_SERIALIZABLE:
			return TRANSACTION_SERIALIZABLE;
		default:
			return null;
		}
	}

}
