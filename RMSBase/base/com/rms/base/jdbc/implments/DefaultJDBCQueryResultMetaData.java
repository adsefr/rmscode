package com.rms.base.jdbc.implments;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCQueryResultMetaData;
import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.util.ArrayUtil;
import com.rms.base.util.NumberUtil;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/08/31
 */
class DefaultJDBCQueryResultMetaData implements JDBCQueryResultMetaData {

	private final int columnCount;

	private final List<QueryResultColumnMeta> QueryResultColumnMetaCollection = new ArrayList<>();

	private final Map<String, QueryResultColumnMeta> QueryResultColumnMetaMap = new HashMap<>();

	private final Map<String, Integer> QueryExecutorColumnOrderMap = new HashMap<>();

	DefaultJDBCQueryResultMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {

		Assertion.assertNotNull("resultSetMetaData", resultSetMetaData);

		this.columnCount = resultSetMetaData.getColumnCount();

		for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
			QueryResultColumnMeta QueryResultColumnMeta = JDBCFactory.newQueryResultColumnMeta();
			QueryResultColumnMeta.setColumnNumber(columnNumber);
			QueryResultColumnMeta.setCatalogName(resultSetMetaData.getCatalogName(columnNumber));
			QueryResultColumnMeta.setSchemaName(resultSetMetaData.getSchemaName(columnNumber));
			QueryResultColumnMeta.setTableName(resultSetMetaData.getTableName(columnNumber));
			QueryResultColumnMeta.setColumnName(resultSetMetaData.getColumnName(columnNumber));
			QueryResultColumnMeta.setColumnClassName(resultSetMetaData.getColumnClassName(columnNumber));
			QueryResultColumnMeta.setColumnType(resultSetMetaData.getColumnType(columnNumber));
			QueryResultColumnMeta.setColumnTypeName(resultSetMetaData.getColumnTypeName(columnNumber));
			QueryResultColumnMeta.setColumnLabel(resultSetMetaData.getColumnLabel(columnNumber));
			QueryResultColumnMeta.setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(columnNumber));
			QueryResultColumnMeta.setPrecision(resultSetMetaData.getPrecision(columnNumber));
			QueryResultColumnMeta.setAutoIncrement(resultSetMetaData.isAutoIncrement(columnNumber));
			QueryResultColumnMeta.setCaseSensitive(resultSetMetaData.isCaseSensitive(columnNumber));
			QueryResultColumnMeta.setCurrency(resultSetMetaData.isCurrency(columnNumber));
			QueryResultColumnMeta.setDefinitelyWritable(resultSetMetaData.isDefinitelyWritable(columnNumber));
			QueryResultColumnMeta.setReadOnly(resultSetMetaData.isReadOnly(columnNumber));
			QueryResultColumnMeta.setSearchable(resultSetMetaData.isSearchable(columnNumber));
			QueryResultColumnMeta.setSigned(resultSetMetaData.isSigned(columnNumber));
			QueryResultColumnMeta.setWritable(resultSetMetaData.isWritable(columnNumber));
			QueryResultColumnMeta.setNullable(resultSetMetaData.isNullable(columnNumber));

			ArrayUtil.add(QueryResultColumnMetaCollection, columnNumber, QueryResultColumnMeta);
			QueryResultColumnMetaMap.put(QueryResultColumnMeta.getColumnName().toUpperCase(), QueryResultColumnMeta);
			QueryExecutorColumnOrderMap.put(QueryResultColumnMeta.getColumnName().toUpperCase(), columnNumber);
		}
	}

	@Override
	public final int getColumnCount() {

		return columnCount;
	}

	@Override
	public final boolean hasColumn(int columnNumber) {

		return NumberUtil.between(columnNumber, 1, columnCount);
	}

	@Override
	public final boolean hasColumn(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return QueryResultColumnMetaMap.containsKey(columnName.toUpperCase());
	}

	@Override
	public final Integer getColumnNumber(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return QueryExecutorColumnOrderMap.get(columnName.toUpperCase());
	}

	@Override
	public final String getColumnName(int columnNumber) {

		String columnName = null;

		if (hasColumn(columnNumber)) {
			columnName = QueryResultColumnMetaCollection.get(columnNumber).getColumnName();
		}

		return columnName;
	}

	@Override
	public final QueryResultColumnMeta getColumnMeta(int columnNumber) {

		QueryResultColumnMeta QueryResultColumnMeta = null;

		if (hasColumn(columnNumber)) {
			QueryResultColumnMeta = QueryResultColumnMetaCollection.get(columnNumber);
		}

		return QueryResultColumnMeta;
	}

	@Override
	public final QueryResultColumnMeta getColumnMeta(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		QueryResultColumnMeta QueryResultColumnMeta = QueryResultColumnMetaMap.get(columnName.toUpperCase());

		return QueryResultColumnMeta;
	}
}
