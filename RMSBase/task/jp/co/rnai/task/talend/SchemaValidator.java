package jp.co.rnai.task.talend;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SchemaValidator {

	public static void main(String[] args) throws SAXException, IOException {

		System.setProperty("user.dir", "D:/work/usr/local/ckmbase/data/data_import/1C01_TAYLORFRANCIS/TalyorFrancis/archive-interchange-oasis-dtd-3.0-tf/");
		String file = "D:/work/usr/local/ckmbase/data/data_import/1C01_TAYLORFRANCIS/TAYLORFRANCIS/gapa20.v092.i12/00036811.2012.742185.xml";
		String dtd = "";

		try {
			XMLUtil.validateDTD(file, dtd);
		} catch (ParserConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
