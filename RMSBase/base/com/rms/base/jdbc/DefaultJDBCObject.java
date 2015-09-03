package com.rms.base.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.implments.JDBCFactory;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.jdbc.JDBCUpdateExecutor;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCObject extends JDBCObject {

	private final Logger logger = Logger.getLogger(JDBCObject.class);

	private final JDBCConnection jdbcConnection = JDBCFactory.newJDBCConnection();

	private final List<JDBCQueryExecutor> jdbcQueryExecutorCollection = new ArrayList<>();

	private final List<JDBCUpdateExecutor> jdbcUpdateExecutorCollection = new ArrayList<>();

	private final DataBaseInfo dataBaseInfo;

	public DefaultJDBCObject(DataBaseInfo dataBaseInfo) {

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		this.dataBaseInfo = dataBaseInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final DataBaseInfo getDatabaseInfo() throws SQLException {

		return dataBaseInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean startTransaction() throws SQLException {

		boolean newConnection = false;

		if (isClosed()) {
			newConnection = jdbcConnection.connection(dataBaseInfo);
		} else {
			commitTransaction();
		}

		jdbcConnection.setAutoCommit(false);

		return newConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void commitTransaction() throws SQLException {

		for (JDBCUpdateExecutor jdbcUpdateExecutor : jdbcUpdateExecutorCollection) {
			if (jdbcUpdateExecutor.failure()) {
				jdbcUpdateExecutor.rollback();
			}
		}

		commit();

		jdbcQueryExecutorCollection.forEach(jdbcQueryExecutor -> jdbcQueryExecutor.close());
		jdbcUpdateExecutorCollection.forEach(jdbcUpdateExecutor -> jdbcUpdateExecutor.close());

		jdbcQueryExecutorCollection.clear();
		jdbcUpdateExecutorCollection.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		return jdbcConnection.getJDBCDataBaseMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCQueryExecutor query(QueryParameter queryParameter) throws SQLException {

		Assertion.assertNotNull("queryParameter", queryParameter);

		JDBCQueryExecutor jdbcQueryExecutor = jdbcConnection.query(queryParameter);

		jdbcQueryExecutorCollection.add(jdbcQueryExecutor);

		return jdbcQueryExecutor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final JDBCUpdateExecutor update(UpdateParameter updateParameter) throws SQLException {

		Assertion.assertNotNull("updateParameter", updateParameter);

		JDBCUpdateExecutor jdbcUpdateExecutor = jdbcConnection.update(updateParameter);

		jdbcUpdateExecutorCollection.add(jdbcUpdateExecutor);

		return jdbcUpdateExecutor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void commit() throws SQLException {

		jdbcConnection.commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void rollback() throws SQLException {

		jdbcConnection.rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		try {
			if (!isClosed()) {
				jdbcConnection.close();
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		return jdbcConnection.isClosed();
	}

}
