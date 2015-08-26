package com.rms.base.jdbc.model;

import java.util.List;

public class UpdateParameter {

	private String sqlClause;

	private List<Object> parameterList;

	public UpdateParameter() {

	}

	public UpdateParameter(String sqlClause, List<Object> parameterList) {

		super();
		this.sqlClause = sqlClause;
		this.parameterList = parameterList;
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
}
