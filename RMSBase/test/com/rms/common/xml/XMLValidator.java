package com.rms.common.xml;

import java.io.File;

import jp.co.rnai.task.talend.XMLUtil;

public class XMLValidator {

	public static void main(String[] args) throws Exception {

		System.setProperty("user.dir", "D:/work/mapping/usr/local/ckmbase/conf/talend/dtd/1101_JSTAGE/");
		File file = new File(
				"D:/work/mapping/usr/local/ckmbase/data/data_import/1101_JSTAGE/abas9i0(J-STAGE20130313).xml");

		XMLUtil.validate(file.getAbsolutePath());
	}
}
