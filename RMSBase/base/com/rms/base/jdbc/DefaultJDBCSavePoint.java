package com.rms.base.jdbc;

import java.sql.Savepoint;

import com.rms.common.jdbc.JDBCSavePoint;

class DefaultJDBCSavePoint implements JDBCSavePoint {

	private Savepoint savepoint;

	public DefaultJDBCSavePoint(Savepoint savepoint) {

		this.savepoint = savepoint;
	}

	/**
	 * @return savepoint
	 */
	public Savepoint getSavepoint() {

		return savepoint;
	}

}
