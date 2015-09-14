package jp.co.rnai.task.xml;

import java.io.File;
import java.util.List;

import com.rms.base.io.file.FileHelper;

public class XMLOperatorTest {

	private static final String CHARSET = "UTF-8";

	public static void main(String[] args) throws Exception {

		String xmlFile = "D:/work/mapping/usr/local/ckmbase/data/data_import/11001_MEDLINE/MEDLINE/medline14n0033.xml";
		String content = FileHelper.readAll(new File(xmlFile), "UTF-8");
		System.out.println(XMLOperator.addAttribute(content, "meline_tag_id", "Article", "Author").length());
	}
}
