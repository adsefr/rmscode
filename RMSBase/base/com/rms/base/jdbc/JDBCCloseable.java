package com.rms.base.jdbc;

import java.sql.SQLException;

public interface JDBCCloseable extends AutoCloseable {

	@Override
	public void close();

	public boolean isClosed() throws SQLException;
}
