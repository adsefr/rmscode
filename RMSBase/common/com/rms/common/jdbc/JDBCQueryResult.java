package com.rms.common.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCQueryResult extends JDBCCloseable {

	public QueryParameter getQueryParameter();

	// public Integer getResultCount();

	public String getResultCode();

	public String getErrorMessage();

	public void beforeFirst() throws SQLException;

	public void absolute(int rowNumber) throws SQLException;

	public void afterLast() throws SQLException;

	public boolean hasNext() throws SQLException;

	public boolean hasColumn(int columnNumber) throws SQLException;

	public boolean hasColumn(String columnName) throws SQLException;

	public JDBCRow getRow() throws SQLException;

	public <T> T getValue(int columnNumber) throws SQLException;

	public <T> T getValue(String columnName) throws SQLException;

}
