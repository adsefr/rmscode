package com.rms.common.jdbc;

import java.sql.Array;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCUpdater;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.common.jdbc.model.DataBaseType;

public class InsertArrayTbl {

	public static void main(String[] args) throws SQLException {

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("localhost");
		dataBase.setPort(5432);
		dataBase.setDataBaseName("rimeisei");
		dataBase.setUserId("rimeisei");
		dataBase.setPassword("admin");
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);

		JDBCObject jdbcObject = JDBCObject.getInstance(dataBase);
		JDBCConnection connection = jdbcObject.getJDBCConnection();
		connection.connection();

//		String sql = "	INSERT INTO tmp.arraytbl (id,content) VALUES (?,?)";
		String sql = "	INSERT INTO tmp.arraytbl (id,content) VALUES (1,'{\"a\",\"b\"}')";
		JDBCUpdater updater = jdbcObject.createJDBCUpdater(connection);
		updater.addParameter(1);
		updater.addParameter("'{\"a\",\"b\"}'");

		Array array = updater.arrayOf("varchar", new Object[] { "a", "b" });
		updater.addParameter(array);
		updater.setSqlClause(sql);
		updater.executeUpdate();
		connection.commit();

	}
}
