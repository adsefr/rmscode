package com.rms.common.generation.classfile;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCObject;

public class TestGenerateTableBeanClass {

	private final Logger logger = Logger.getLogger(TestGenerateTableBeanClass.class);

	@Test
	public void testgenerate() {

		JDBCObject jdbcObject = null;
		try {
			DataBaseInfo dataBaseInfo = new DataBaseInfo();
			dataBaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
			dataBaseInfo.setDriver("org.postgresql.Driver");
			dataBaseInfo.setHost("localhost");
			dataBaseInfo.setPort(5432);
			dataBaseInfo.setDataBaseName("V420_DEV01");
			dataBaseInfo.setUserId("V420_DEV01usr");
			dataBaseInfo.setPassword("V420_DEV01pwd");
			jdbcObject = JDBCObject.getInstance(dataBaseInfo);
			jdbcObject.startTransaction();

			SchemaMeta schemaMeta = jdbcObject.getJDBCDataBaseMetaData().getSchemaMeta(null, "public");

			Path path = Paths.get("D:/documents/workspace/GitHub/rmscode/RMSBase/gen/com/rms/gen/bean");
			BeanClassFileGenerator generate = new BeanClassFileGenerator();
			for (TableMeta tableMeta : schemaMeta.getTableMetas()) {
				if (!tableMeta.getTableType().equals("TABLE")) {
					continue;
				}
				if (tableMeta.getTableName().equals("tausmplpaydtl")) {
					for (ColumnMeta columnMeta : tableMeta.getColumnMetas()) {
						logger.debug(columnMeta.getColumnName() + " " + columnMeta.getJdbcType());
					}
				}
				generate.setTableMeta(tableMeta);
				generate.generate();
				generate.write(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcObject.close();
		}
	}
}
