package com.rms.common.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.rms.base.jdbc.JDBCCloseable;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCQueryExecutor extends JDBCCloseable {

	public void execute() throws SQLException;

	public boolean success();

	public boolean failure();

	public String getStatusCode();

	public String getErrorMessage();

	public Throwable getThrowable();

	public QueryParameter getQueryParameter();

	public void beforeFirst() throws SQLException;

	public void absolute(int rowNumber) throws SQLException;

	public void afterLast() throws SQLException;

	public boolean hasNext() throws SQLException;

	public boolean hasColumn(int columnNumber) throws SQLException;

	public boolean hasColumn(String columnName) throws SQLException;

	public JDBCRow getRow() throws SQLException;

	public JDBCValue getJDBCValue(int columnNumber) throws SQLException;

	public JDBCValue getJDBCValue(String columnName) throws SQLException;

	public List<JDBCValue> getJDBCValues() throws SQLException;
}
