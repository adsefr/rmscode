package com.rms.base.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.common.jdbc.JDBCCloseable;
import com.rms.common.jdbc.JDBCSavePoint;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
interface JDBCConnection extends JDBCCloseable {

	public void setAutoCommit(boolean autoCommit) throws SQLException;

	public boolean isAutoCommit() throws SQLException;

	public void setReadOnly(boolean readOnly) throws SQLException;

	public boolean isReadOnly() throws SQLException;

	public void setTransactionType(TransactionType transactionType) throws SQLException;

	public TransactionType getTransactionType() throws SQLException;

	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException;

	public HoldabilityType getHoldabilityType() throws SQLException;

	public JDBCSavePoint savePoint() throws SQLException;

	public JDBCSavePoint savePoint(String savePointName) throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;

	public void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException;
}
