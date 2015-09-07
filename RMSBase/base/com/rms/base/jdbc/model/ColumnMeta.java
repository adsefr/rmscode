package com.rms.base.jdbc.model;

import java.sql.JDBCType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public interface ColumnMeta {

	public String getCatalogName();

	public String getSchemaName();

	public String getTableName();

	public String getColumnName();

	public JDBCType getJdbcType();

	public String getTypeName();

	public Integer getColumnSize();

	public Integer getDecimalDits();

	public Integer getNumPrecRadix();

	public Integer getNullable();

	public String getColumnDefaultValue();

	public boolean isPrimaryKey();

	public Integer getKeySequence();
}
