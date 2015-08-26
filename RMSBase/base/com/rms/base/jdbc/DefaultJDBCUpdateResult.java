package com.rms.base.jdbc;

import com.rms.common.jdbc.JDBCUpdateResult;

/**
 *
 * @author ri.meisei
 * @since 2015/08/24
 */
class DefaultJDBCUpdateResult implements JDBCUpdateResult {

	private int updateCount;

	DefaultJDBCUpdateResult() {

	}

	/**
	 * @return updateCount
	 */
	@Override
	public int getUpdateCount() {

		return updateCount;
	}

	/**
	 * @param updateCount
	 *            セットする updateCount
	 */
	public void setUpdateCount(int updateCount) {

		this.updateCount = updateCount;
	}

}
