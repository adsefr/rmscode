package com.rms.base.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCSavePoint;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCConnection implements JDBCConnection {

	private final Connection connection;

	/**
	 *
	 * @param connection
	 */
	DefaultJDBCConnection(Connection connection) {

		this.connection = connection;
	}

	final Connection getConnection() {

		return connection;
	}

	JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		DatabaseMetaData databaseMetaData = connection.getMetaData();
		JDBCDataBaseMetaData jdbcDataBaseMetaData = new DefaultJDBCDatabaseMetaData(databaseMetaData);

		return jdbcDataBaseMetaData;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {

		connection.setAutoCommit(autoCommit);
	}

	@Override
	public boolean isAutoCommit() throws SQLException {

		return connection.getAutoCommit();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {

		connection.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {

		return connection.isReadOnly();
	}

	@Override
	public void setTransactionType(TransactionType transactionType) throws SQLException {

		connection.setTransactionIsolation(transactionType.getType());
	}

	@Override
	public TransactionType getTransactionType() throws SQLException {

		return TransactionType.valueOf(connection.getTransactionIsolation());
	}

	@Override
	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException {

		connection.setHoldability(holdabilityType.getType());
	}

	@Override
	public HoldabilityType getHoldabilityType() throws SQLException {

		return HoldabilityType.valueOf(connection.getTransactionIsolation());
	}

	@Override
	public JDBCSavePoint savePoint() throws SQLException {

		Savepoint savepoint = connection.setSavepoint();

		return JDBCUtil.getJDBCSavePoint(savepoint);
	}

	@Override
	public JDBCSavePoint savePoint(String savePointName) throws SQLException {

		Savepoint savepoint = connection.setSavepoint(savePointName);

		return JDBCUtil.getJDBCSavePoint(savepoint);
	}

	@Override
	public void commit() throws SQLException {

		connection.commit();
	}

	@Override
	public void rollback() throws SQLException {

		connection.rollback();
	}

	@Override
	public void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException {

		DefaultJDBCSavePoint defaultJDBCSavePoint = (DefaultJDBCSavePoint) jdbcSavePoint;

		connection.rollback(defaultJDBCSavePoint.getSavepoint());
	}

	@Override
	public void close() throws SQLException {

		connection.close();
	}

	@Override
	public boolean isClosed() throws SQLException {

		return connection.isClosed();
	}
}
