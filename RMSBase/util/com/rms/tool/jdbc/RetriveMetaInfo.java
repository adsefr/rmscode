package com.rms.tool.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.common.jdbc.JDBCDataBaseMetaData;

public class RetriveMetaInfo extends BaseJDBCTool {

	private JDBCDataBaseMetaData jdbcDataBaseMetaData;

	public RetriveMetaInfo(DataBaseInfo dataBaseInfo) {

		super(dataBaseInfo);
	}

	@Override
	public void execute() throws SQLException {

		jdbcDataBaseMetaData = jdbcObject.getJDBCDataBaseMetaData();
	}

	public List<String> getTableNameList(String schemaName) throws SQLException {

		List<TableMeta> tableMetas = jdbcDataBaseMetaData.getTableMetas(schemaName);

		List<String> tableNames = tableMetas.stream().map(element -> element.getTableName()).distinct().collect(Collectors.toList());

		return tableNames;
	}
}
