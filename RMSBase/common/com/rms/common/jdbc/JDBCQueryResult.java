package com.rms.common.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCQueryResult {

	public String getErrorCode();

	public String getErrorMessage();

	public Integer getResultCount();

	public QueryParameter getQueryParameter();

	public void beforeFirst() throws SQLException;

	public void before(int rowNumber) throws SQLException;

	public void after(int rowNumber) throws SQLException;

	public void afterLast() throws SQLException;

	public boolean hasNext() throws SQLException;

	public JDBCRow getRow() throws SQLException;

	public boolean hasColumn(int columnIndex) throws SQLException;

	public boolean hasColumn(String columnName) throws SQLException;

	public <T> T getValue(int columnIndex) throws SQLException;

	public <T> T getValue(String columnName) throws SQLException;
}
