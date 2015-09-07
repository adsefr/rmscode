package com.rms.common.generation.classfile;

import org.junit.Test;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.common.jdbc.JDBCObject;

public class TestGenerateTableBeanClass {

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
			SchemaMeta schemaMeta = jdbcObject.getJDBCDataBaseMetaData().getSchemaMeta("public");

			GenerateBeanClass generate = new GenerateBeanClass();
			generate.setSchemaMeta(schemaMeta);

			generate.generate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcObject.close();
		}
	}
}
