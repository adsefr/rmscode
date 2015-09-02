package com.rms.base.jdbc.implments;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.util.ArrayUtil;
import com.rms.base.util.NumberUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResultMetaData;

/**
 *
 * @author ri.meisei
 * @since 2015/08/31
 */
class DefaultJDBCQueryResultMetaData implements JDBCQueryResultMetaData {

	private final int columnCount;

	private final List<QueryResultColumnMeta> queryResultColumnMetaCollection = new ArrayList<>();

	private final Map<String, QueryResultColumnMeta> queryResultColumnMetaMap = new HashMap<>();

	private final Map<String, Integer> queryResultColumnOrderMap = new HashMap<>();

	DefaultJDBCQueryResultMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {

		Assertion.assertNotNull("resultSetMetaData", resultSetMetaData);

		this.columnCount = resultSetMetaData.getColumnCount();

		for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
			QueryResultColumnMeta queryResultColumnMeta = JDBCFactory.newQueryResultColumnMeta();
			queryResultColumnMeta.setColumnNumber(columnNumber);
			queryResultColumnMeta.setCatalogName(resultSetMetaData.getCatalogName(columnNumber));
			queryResultColumnMeta.setSchemaName(resultSetMetaData.getSchemaName(columnNumber));
			queryResultColumnMeta.setTableName(resultSetMetaData.getTableName(columnNumber));
			queryResultColumnMeta.setColumnName(resultSetMetaData.getColumnName(columnNumber));
			queryResultColumnMeta.setColumnClassName(resultSetMetaData.getColumnClassName(columnNumber));
			queryResultColumnMeta.setColumnType(resultSetMetaData.getColumnType(columnNumber));
			queryResultColumnMeta.setColumnTypeName(resultSetMetaData.getColumnTypeName(columnNumber));
			queryResultColumnMeta.setColumnLabel(resultSetMetaData.getColumnLabel(columnNumber));
			queryResultColumnMeta.setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(columnNumber));
			queryResultColumnMeta.setPrecision(resultSetMetaData.getPrecision(columnNumber));
			queryResultColumnMeta.setAutoIncrement(resultSetMetaData.isAutoIncrement(columnNumber));
			queryResultColumnMeta.setCaseSensitive(resultSetMetaData.isCaseSensitive(columnNumber));
			queryResultColumnMeta.setCurrency(resultSetMetaData.isCurrency(columnNumber));
			queryResultColumnMeta.setDefinitelyWritable(resultSetMetaData.isDefinitelyWritable(columnNumber));
			queryResultColumnMeta.setReadOnly(resultSetMetaData.isReadOnly(columnNumber));
			queryResultColumnMeta.setSearchable(resultSetMetaData.isSearchable(columnNumber));
			queryResultColumnMeta.setSigned(resultSetMetaData.isSigned(columnNumber));
			queryResultColumnMeta.setWritable(resultSetMetaData.isWritable(columnNumber));
			queryResultColumnMeta.setNullable(resultSetMetaData.isNullable(columnNumber));

			ArrayUtil.add(queryResultColumnMetaCollection, columnNumber, queryResultColumnMeta);
			queryResultColumnMetaMap.put(queryResultColumnMeta.getColumnName().toUpperCase(), queryResultColumnMeta);
			queryResultColumnOrderMap.put(queryResultColumnMeta.getColumnName().toUpperCase(), columnNumber);
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

		return queryResultColumnMetaMap.containsKey(columnName.toUpperCase());
	}

	@Override
	public final Integer getColumnNumber(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return queryResultColumnOrderMap.get(columnName.toUpperCase());
	}

	@Override
	public final String getColumnName(int columnNumber) {

		String columnName = null;

		if (hasColumn(columnNumber)) {
			columnName = queryResultColumnMetaCollection.get(columnNumber).getColumnName();
		}

		return columnName;
	}

	@Override
	public final QueryResultColumnMeta getColumnMeta(int columnNumber) {

		QueryResultColumnMeta queryResultColumnMeta = null;

		if (hasColumn(columnNumber)) {
			queryResultColumnMeta = queryResultColumnMetaCollection.get(columnNumber);
		}

		return queryResultColumnMeta;
	}

	@Override
	public final QueryResultColumnMeta getColumnMeta(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		QueryResultColumnMeta queryResultColumnMeta = queryResultColumnMetaMap.get(columnName.toUpperCase());

		return queryResultColumnMeta;
	}
}
