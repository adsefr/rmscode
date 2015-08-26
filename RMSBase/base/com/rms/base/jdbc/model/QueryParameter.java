package com.rms.base.jdbc.model;

import java.util.List;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.JDBCConst;
import com.rms.base.jdbc.constant.ResultSetConcurrency;
import com.rms.base.jdbc.constant.ResultSetType;

public class QueryParameter {

	private int fetchSize = JDBCConst.DEFAULT_FETCH_SIZE;

	private int queryTimeout = JDBCConst.DEFAULT_QUERY_TIME_OUT;

	private ResultSetType resultSetType = ResultSetType.TYPE_FORWARD_ONLY;

	private ResultSetConcurrency resultSetConcurrency = ResultSetConcurrency.CONCUR_READ_ONLY;

	private HoldabilityType holdabilityType = HoldabilityType.HOLD_CURSORS_OVER_COMMIT;

	private String sqlClause;

	private List<Object> parameterList;

	public QueryParameter() {

	}

	public QueryParameter(String sqlClause, List<Object> parameterList) {

		this.sqlClause = sqlClause;
		this.parameterList = parameterList;
	}

	/**
	 * @return fetchSize
	 */
	public int getFetchSize() {

		return fetchSize;
	}

	/**
	 * @param fetchSize
	 *            セットする fetchSize
	 */
	public void setFetchSize(int fetchSize) {

		this.fetchSize = fetchSize;
	}

	/**
	 * @return queryTimeout
	 */
	public int getQueryTimeout() {

		return queryTimeout;
	}

	/**
	 * @param queryTimeout
	 *            セットする queryTimeout
	 */
	public void setQueryTimeout(int queryTimeout) {

		this.queryTimeout = queryTimeout;
	}

	/**
	 * @return resultSetType
	 */
	public ResultSetType getResultSetType() {

		return resultSetType;
	}

	/**
	 * @param resultSetType
	 *            セットする resultSetType
	 */
	public void setResultSetType(ResultSetType resultSetType) {

		this.resultSetType = resultSetType;
	}

	/**
	 * @return resultSetConcurrency
	 */
	public ResultSetConcurrency getResultSetConcurrency() {

		return resultSetConcurrency;
	}

	/**
	 * @param resultSetConcurrency
	 *            セットする resultSetConcurrency
	 */
	public void setResultSetConcurrency(ResultSetConcurrency resultSetConcurrency) {

		this.resultSetConcurrency = resultSetConcurrency;
	}

	/**
	 * @return sqlClause
	 */
	public String getSqlClause() {

		return sqlClause;
	}

	/**
	 * @param sqlClause
	 *            セットする sqlClause
	 */
	public void setSqlClause(String sqlClause) {

		this.sqlClause = sqlClause;
	}

	/**
	 * @return parameterList
	 */
	public List<Object> getParameterList() {

		return parameterList;
	}

	/**
	 * @param parameterList
	 *            セットする parameterList
	 */
	public void setParameterList(List<Object> parameterList) {

		this.parameterList = parameterList;
	}

	/**
	 * @return holdabilityType
	 */
	public HoldabilityType getHoldabilityType() {

		return holdabilityType;
	}

	/**
	 * @param holdabilityType
	 *            セットする holdabilityType
	 */
	public void setHoldabilityType(HoldabilityType holdabilityType) {

		this.holdabilityType = holdabilityType;
	}
}
