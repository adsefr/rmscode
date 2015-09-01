package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.util.ArrayUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResultMetaData;

/**
 *
 * @author ri.meisei
 * @since 2015/08/31
 */
class DefaultJDBCQueryResultMetaData implements JDBCQueryResultMetaData {

	private List<QueryResultColumnMeta> queryResultColumnMetaCollection;

	private Map<String, QueryResultColumnMeta> columnNameMap = new HashMap<>();

	public DefaultJDBCQueryResultMetaData() {

		queryResultColumnMetaCollection = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {

		return columnNameMap.size();
	}

	@Override
	public boolean hasColumn(int columnNumber) {

		return columnNumber >= 1 && columnNumber <= queryResultColumnMetaCollection.size();
	}

	@Override
	public boolean hasColumn(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return columnNameMap.containsKey(columnName);
	}

	@Override
	public Integer getColumnNumber(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		QueryResultColumnMeta queryResultColumnMeta = columnNameMap.get(columnName);

		if (queryResultColumnMeta != null) {
			return columnNameMap.get(columnName).getColumnNumber();
		}

		return null;
	}

	@Override
	public String getColumnName(int columnNumber) {

		String columnName = null;

		if (hasColumn(columnNumber)) {
			columnName = queryResultColumnMetaCollection.get(columnNumber).getColumnName();
		}

		return columnName;
	}

	@Override
	public QueryResultColumnMeta getColumnMeta(int columnNumber) {

		QueryResultColumnMeta queryResultColumnMeta = null;

		if (hasColumn(columnNumber)) {
			queryResultColumnMeta = queryResultColumnMetaCollection.get(columnNumber - 1);
		}

		return queryResultColumnMeta;
	}

	@Override
	public QueryResultColumnMeta getColumnMeta(String columnName) {

		QueryResultColumnMeta queryResultColumnMeta = null;

		if (hasColumn(columnName)) {
			Integer columnNumber = getColumnNumber(columnName);
			queryResultColumnMeta = queryResultColumnMetaCollection.get(columnNumber - 1);
		}

		return queryResultColumnMeta;
	}

	@Override
	public void addColumnMeta(QueryResultColumnMeta queryResultColumnMeta) {

		Assertion.assertNotNull("queryResultColumnMeta", queryResultColumnMeta);

		String columnName = queryResultColumnMeta.getColumnName();
		Assertion.assertNotBlank("queryResultColumnMeta.getColumnName()", columnName);

		if (!columnNameMap.containsKey(columnName)) {
			columnNameMap.put(columnName, queryResultColumnMeta);
		}

		ArrayUtil.add(queryResultColumnMetaCollection, queryResultColumnMeta.getColumnNumber() - 1, queryResultColumnMeta);
	}
}
