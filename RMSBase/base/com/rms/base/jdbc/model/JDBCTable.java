package com.rms.base.jdbc.model;

/**
 *
 * @author ri.meisei
 * @since 2015/08/20
 */
public interface JDBCTable {

	public String getSelectSqlClause();

	public String getInsertSqlClause();

	public String getUpdateSqlClause();

	public String getDeleteSqlClause();

	public String getTruncateSqlClause();

	public String getDropSqlClause();

}
