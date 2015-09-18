package jp.co.rnai.talend.mapping.validate;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jp.co.rnai.task.talend.JDBC;

import com.rms.base.constant.Encodes;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.JDBCTable;
import com.rms.common.poi.excel.model.FontModel;
import com.rms.common.poi.excel.object.ExcelFactory;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class ValidateMappingData {

	private static String VALIDATE_OUT_PATH = "D:/work/datamapping/validate/excel/";

	private static String VALIDATE_SQL_PATH = "D:/work/datamapping/validate/sql/";

	private JDBCOperator jdbcOperator = null;

	public static void main(String[] args) throws SQLException, IOException {

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("localhost");
		dataBase.setPort(5432);
		dataBase.setDataBaseName("Ishioka");
		dataBase.setUserId("Ishioka");
		dataBase.setPassword("admin");
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);

		ValidateMappingData validateMappingData = new ValidateMappingData();
		validateMappingData.jdbcOperator = JDBC.getOperator(dataBase);
		validateMappingData.printResultToExcel();
	}

	public void printResultToExcel() throws IOException, SQLException {

		List<File> list = FileHelper.getFileList(VALIDATE_SQL_PATH, ".+\\.sql", FileType.FILE, true);

		for (File sqlFile : list) {
			try {
				System.out.println(sqlFile.getAbsolutePath());
				String sql = FileHelper.readAll(sqlFile, Encodes.ENCODE_UTF8);
				JDBCTable table = jdbcOperator.select(sql);

				printResultToExcel(sqlFile, table);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void printResultToExcel(File sqlFile, JDBCTable table) throws IOException {

		ExcelOperator excelOperator = ExcelFactory.create();
		SheetOperator sheetOperator = excelOperator.createSheet(sqlFile.getName());
		sheetOperator.setActiveSheet();

		CellOperator cellOperator = sheetOperator.getCellOperator();

		FontModel sameFont = new FontModel();
		sameFont.setFontSize(Integer.valueOf(10).shortValue());

		FontModel diffFont = new FontModel();
		diffFont.setFontColor(Color.BLUE);
		diffFont.setFontSize(Integer.valueOf(10).shortValue());

		for (int rowIndex = 0; rowIndex < table.getRowList().size(); rowIndex++) {
			JDBCRow row = table.getRowList().get(rowIndex);
			List<JDBCColumn> columnList = row.getColumnList();

			if (rowIndex == 0) {
				for (int columnIndex = 0; columnIndex < columnList.size(); columnIndex++) {
					JDBCColumn column = columnList.get(columnIndex);
					cellOperator.rowIndex(rowIndex).columnIndex(columnIndex).value(column.getLogicalName()).flush();
				}
			}

			for (int columnIndex = 0; columnIndex < columnList.size() - 1; columnIndex++) {
				JDBCColumn dump = columnList.get(columnIndex);
				JDBCColumn imported = columnList.get(columnIndex + 1);

				String dumpValue = String.valueOf(dump.getValue());
				String importedValue = String.valueOf(imported.getValue());

				if (dump.getValue() == null) {
					dumpValue = "";
				}

				if (imported.getValue() == null) {
					importedValue = "";
				}

				if (dumpValue.equals(importedValue)) {
					cellOperator.rowIndex(rowIndex + 1).columnIndex(columnIndex).fontModel(sameFont).value(dumpValue)
							.flush();
					cellOperator.rowIndex(rowIndex + 1).columnIndex(columnIndex + 1).fontModel(sameFont)
							.value(importedValue).flush();
				} else {
					cellOperator.rowIndex(rowIndex + 1).columnIndex(columnIndex).fontModel(diffFont).value(dumpValue)
							.flush();
					cellOperator.rowIndex(rowIndex + 1).columnIndex(columnIndex + 1).fontModel(diffFont)
							.value(importedValue).flush();
				}

				columnIndex++;
			}

		}

		String outFile = VALIDATE_OUT_PATH + "/" + sqlFile.getName() + ".xlsx";
		excelOperator.write(outFile);
	}
}
