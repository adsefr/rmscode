package com.rms.common.jdbc;

import java.sql.SQLException;
import java.util.Arrays;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCSelector;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.common.jdbc.model.DataBaseType;

public class Test {

	/**
	 * @param args
	 * @throws SQLException
	 */

	public static void main(String[] args) throws SQLException {

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setDataBaseName("MeSH");
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);
		dataBase.setHost("192.168.1.225");
		dataBase.setPort(5432);
		dataBase.setUserId("postgres");
		dataBase.setPassword("postgres00");
		JDBCObject jdbcObject = JDBCObject.newInstance(dataBase);

		JDBCConnection jdbcConnection = jdbcObject.getJDBCConnection();
		jdbcConnection.connection();
		JDBCSelector jdbcSelector = jdbcObject.createJDBCSelector(jdbcConnection);
		jdbcSelector.setSqlClause("SELECT * FROM \"Term\"");
		JDBCQueryResult jdbcResultSet = jdbcSelector.executeQuery();
		while (jdbcResultSet.hasNext()) {
			System.out.println(jdbcResultSet.getValue(1));
			System.out.println(jdbcResultSet.getValue(2));
			System.out.println(jdbcResultSet.getValue(3));
			System.out.println(jdbcResultSet.getValue(4));
			System.out.println(jdbcResultSet.getValue(5));
			System.out.println(jdbcResultSet.getValue(6));
			System.out.println(jdbcResultSet.getValue(7));
		}

	}
}
