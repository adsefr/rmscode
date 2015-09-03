package com.rms.common.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.JDBCCloseable;

/**
 *
 * @author ri.meisei
 * @since 2015/08/24
 */
public interface JDBCUpdateExecutor extends JDBCCloseable {

	public void execute() throws SQLException;

	public boolean success();

	public boolean failure();

	public String getStatusCode();

	public String getErrorMessage();

	public Throwable getThrowable();

	public int getUpdateCount();

	public void rollback() throws SQLException;
}
