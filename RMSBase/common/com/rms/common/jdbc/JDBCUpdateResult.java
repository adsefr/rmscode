package com.rms.common.jdbc;

/**
 *
 * @author ri.meisei
 * @since 2015/08/24
 */
public interface JDBCUpdateResult {

	public void setUpdateCount(int updateCount);

	public int getUpdateCount();
}
