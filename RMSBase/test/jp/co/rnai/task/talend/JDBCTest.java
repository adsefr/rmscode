package jp.co.rnai.task.talend;

public class JDBCTest {

	public static void main(String[] args) {

		testConvertItem();
	}

	private static void testConvertItem() {

		String content = "	 ad　 	　g   f　	 　	 ";
		String converted = JDBC.convertItem(content);
		System.out.println(converted);
	}
}
