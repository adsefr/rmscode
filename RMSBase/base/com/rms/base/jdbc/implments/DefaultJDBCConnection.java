package com.rms.base.jdbc.implments;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCUtil;
import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.jdbc.JDBCUpdateExecutor;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class DefaultJDBCConnection implements JDBCConnection {

	protected final Logger logger = Logger.getLogger(JDBCConnection.class);

	private Connection connection;

	/**
	 *
	 * @param connection
	 * @throws SQLException
	 */
	protected DefaultJDBCConnection() {

		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Connection getConnection() throws SQLException {

		if (isClosed()) {
			logger.debug("the connection has been closed!!!" + connection.toString());
			throw new SQLException("the connection has been closed!!!");
		}

		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAutoCommit(boolean autoCommit) throws SQLException {

		getConnection().setAutoCommit(autoCommit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isAutoCommit() throws SQLException {

		return getConnection().getAutoCommit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setReadOnly(boolean readOnly) throws SQLException {

		getConnection().setReadOnly(readOnly);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isReadOnly() throws SQLException {

		return getConnection().isReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTransactionType(TransactionType transactionType) throws SQLException {

		getConnection().setTransactionIsolation(transactionType.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final TransactionType getTransactionType() throws SQLException {

		return TransactionType.valueOf(getConnection().getTransactionIsolation());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException {

		getConnection().setHoldability(holdabilityType.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final HoldabilityType getHoldabilityType() throws SQLException {

		return HoldabilityType.valueOf(getConnection().getTransactionIsolation());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean connection(DataBaseInfo dataBaseInfo) throws SQLException {

		if (!isClosed()) {
			return false;
		}

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		try {
			Class.forName(dataBaseInfo.getDriver());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			throw new SQLException(e);
		}

		String url = dataBaseInfo.getUrl();
		String userId = dataBaseInfo.getUserId();
		String password = dataBaseInfo.getPassword();

		Connection connection = null;

		if (TextUtil.isNotBlank(url)) {
			if (TextUtil.isBlank(userId) && TextUtil.isBlank(password)) {
				connection = DriverManager.getConnection(url);
			} else if (TextUtil.isNotBlank(userId) && TextUtil.isNotBlank(password)) {
				connection = DriverManager.getConnection(url, userId, password);
			}
		} else {
			String host = dataBaseInfo.getHost();
			int port = dataBaseInfo.getPort();
			String dataBaseName = dataBaseInfo.getDataBaseName();
			switch (dataBaseInfo.getDataBaseType()) {
			case ORACLE:
				url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dataBaseName;
				break;
			case MYSQL:
				url = "jdbc:mysql://" + host + ":" + port + ":" + dataBaseName;
				break;
			case POSTGRESQL:
				url = "jdbc:postgresql://" + host + ":" + port + "/" + dataBaseName;
				break;
			default:
				throw new UnexpectedTypeException("type:" + dataBaseInfo.getDataBaseType().getClass().getName());
			}
			connection = JDBCUtil.getConnection(url, userId, password);
		}

		connection.setReadOnly(dataBaseInfo.isReadOnly());
		connection.setAutoCommit(dataBaseInfo.isAutoCommit());

		if (dataBaseInfo.getTransactionType() != null) {
			connection.setTransactionIsolation(dataBaseInfo.getTransactionType().getType());
		}

		if (dataBaseInfo.getHoldabilityType() != null) {
			connection.setHoldability(dataBaseInfo.getHoldabilityType().getType());
		}

		if (!isClosed()) {
			close();
		}

		this.connection = connection;

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		DatabaseMetaData databaseMetaData = getConnection().getMetaData();

		JDBCDataBaseMetaData jdbcDataBaseMetaData = JDBCFactory.newJDBCDataBaseMetaData(databaseMetaData);

		return jdbcDataBaseMetaData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCQueryExecutor query(QueryParameter queryParameter) throws SQLException {

		JDBCQueryExecutor jdbcQueryExecutor = JDBCFactory.newJDBCQueryExecutor(this, queryParameter);

		return jdbcQueryExecutor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCUpdateExecutor update(UpdateParameter updateParameter) throws SQLException {

		JDBCUpdateExecutor jdbcQueryExecutor = JDBCFactory.newJDBCUpdateExecutor(this, updateParameter);

		return jdbcQueryExecutor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void commit() throws SQLException {

		getConnection().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void rollback() throws SQLException {

		getConnection().rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		JDBCUtil.close(connection);

		connection = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		return JDBCUtil.isClosed(connection);
	}
}
