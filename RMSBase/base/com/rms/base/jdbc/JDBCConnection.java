package com.rms.base.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.base.jdbc.model.ConnectionInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.common.jdbc.JDBCCloseable;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCSavePoint;
import com.rms.common.jdbc.JDBCUpdateResult;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCConnection extends JDBCCloseable {

	public void connection(ConnectionInfo connectionInfo) throws SQLException;

	public JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException;

	public void setAutoCommit(boolean autoCommit) throws SQLException;

	public boolean isAutoCommit() throws SQLException;

	public void setReadOnly(boolean readOnly) throws SQLException;

	public boolean isReadOnly() throws SQLException;

	public void setTransactionType(TransactionType transactionType) throws SQLException;

	public TransactionType getTransactionType() throws SQLException;

	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException;

	public HoldabilityType getHoldabilityType() throws SQLException;

	public JDBCQueryResult queryStatement(QueryParameter queryParameter) throws SQLException;

	public JDBCUpdateResult updateStatement(UpdateParameter updateParameter) throws SQLException;

	public JDBCSavePoint savePoint() throws SQLException;

	public JDBCSavePoint savePoint(String savePointName) throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;

	public void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException;
}
