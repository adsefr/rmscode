package com.rms.base.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.common.jdbc.JDBCCloseable;

public class JDBCUtil {

	static String generateID() {

		return UUID.randomUUID().toString();
	}

	public static Object getData(ResultSet resultSet, String fieldName, int dataType) throws SQLException {

		switch (dataType) {
		case Types.CHAR:
		case Types.VARCHAR:
			return resultSet.getString(fieldName);
		case Types.SMALLINT:
		case Types.BIGINT:
		case Types.INTEGER:
			return resultSet.getInt(fieldName);
		case Types.TIMESTAMP:
			return resultSet.getTimestamp(fieldName);
		default:
			throw new UnexpectedDataException("type:" + dataType);
		}
	}

	public static void close(AutoCloseable autoCloseable) {

		try {
			if (autoCloseable != null) {
				autoCloseable.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Collection<? extends JDBCCloseable> jdbcCloseableCollection) {

		for (Iterator<? extends JDBCCloseable> iterator = jdbcCloseableCollection.iterator(); iterator.hasNext();) {
			JDBCCloseable jdbcCloseable = iterator.next();
			close(jdbcCloseable);
		}
	}
}
