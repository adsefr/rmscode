package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResultMetaData;

/**
 *
 * @author ri.meisei
 * @since 2015/08/31
 */
class DefaultJDBCQueryResultMetaData implements JDBCQueryResultMetaData {

	private int columnCount;

	private List<QueryResultColumnMeta> queryResultColumnMetaCollection;

	public DefaultJDBCQueryResultMetaData(int columnCount) {

		this.columnCount = columnCount;

		queryResultColumnMetaCollection = new ArrayList<>(columnCount + 1);

		queryResultColumnMetaCollection.add(null);
	}

	@Override
	public void addColumnMeta(QueryResultColumnMeta queryResultColumnMeta) {

		queryResultColumnMetaCollection.add(queryResultColumnMeta);
	}

	@Override
	public boolean hasColumn(int columnNumber) {

		return columnNumber >= 1 && columnNumber <= queryResultColumnMetaCollection.size();
	}

	@Override
	public boolean hasColumn(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		for (QueryResultColumnMeta queryResultColumnMeta : queryResultColumnMetaCollection) {
			if (TextUtil.isEqualsIgnoreCase(queryResultColumnMeta.getColumnName(), columnName)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int getColumnCount() {

		return columnCount;
	}

	@Override
	public QueryResultColumnMeta getColumn(int columnNumber) {

		if (hasColumn(columnNumber)) {
			return queryResultColumnMetaCollection.get(columnNumber);
		}

		return null;
	}

	@Override
	public QueryResultColumnMeta getColumn(String columnName) {

		for (QueryResultColumnMeta queryResultColumnMeta : queryResultColumnMetaCollection) {
			if (TextUtil.isEqualsIgnoreCase(queryResultColumnMeta.getColumnName(), columnName)) {
				return queryResultColumnMeta;
			}
		}

		return null;
	}
}
