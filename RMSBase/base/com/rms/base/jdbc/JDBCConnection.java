package com.rms.base.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.jdbc.JDBCUpdateExecutor;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCConnection extends JDBCCloseable {

	public void setAutoCommit(boolean autoCommit) throws SQLException;

	public boolean isAutoCommit() throws SQLException;

	public void setReadOnly(boolean readOnly) throws SQLException;

	public boolean isReadOnly() throws SQLException;

	public void setTransactionType(TransactionType transactionType) throws SQLException;

	public TransactionType getTransactionType() throws SQLException;

	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException;

	public HoldabilityType getHoldabilityType() throws SQLException;

	public Connection getConnection() throws SQLException;

	public boolean connection(DataBaseInfo dataBaseInfo) throws SQLException;

	public JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException;

	public JDBCQueryExecutor query(QueryParameter queryParameter) throws SQLException;

	public JDBCUpdateExecutor update(UpdateParameter updateParameter) throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;
}
