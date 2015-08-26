package com.rms.base.jdbc.model;

import com.rms.base.jdbc.constant.JDBCConst;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public enum DataBaseType {

	ORACLE(JDBCConst.DEFAULT_ORACLE_JDBC_DRIVER), //

	MYSQL(JDBCConst.DEFAULT_MYSQL_JDBC_DRIVER), //

	POSTGRESQL(JDBCConst.DEFAULT_POSTGRESQL_JDBC_DRIVER);

	private String driver = null;

	private DataBaseType(String driver) {

		this.driver = driver;
	}

	/**
	 * @return driver
	 */
	public String getDriver() {

		return driver;
	}

}
