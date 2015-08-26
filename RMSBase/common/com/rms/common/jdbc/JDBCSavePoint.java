package com.rms.common.jdbc;

import java.sql.SQLException;

public interface JDBCSavePoint {

	public int getSavepointId() throws SQLException;

	public void setSavepointId(int savepointId) throws SQLException;

	public String getSavepointName() throws SQLException;

	public void setSavepointName(String savepointName) throws SQLException;
}
