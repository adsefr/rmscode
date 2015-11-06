package jp.co.rnai.talend.tablecompare;

import java.io.File;
import java.io.IOException;

import com.rms.base.constant.Characters;
import com.rms.base.constant.Encodes;
import com.rms.base.io.FileWriter;
import com.rms.base.io.IOFactory;
import com.rms.base.jdbc.model.JDBCTable;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.usermodel.ExcelFactory;
import com.rms.base.poi.excel.usermodel.ExcelOperator;
import com.rms.base.poi.excel.usermodel.SheetOperator;
import com.rms.base.util.NumberUtil;
import com.rms.base.util.TextUtil;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/28
 */
public class CompareSqlGenerator {

	private static String TABLE_DD = "D:/GoogleDrive/調査資料/テーブル定義書/新テーブル定義書.xls";

	private static String PATH_SQL = "D:/work/datamapping/validatesql/";

	private static String schemaSrcAlias = "dump";

	private static String schemaDestAlias = "imported";

	public static void main(String[] args) {

		try {
			loopSheets();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loopSheets() throws IOException {

		ExcelOperator excelOperator = ExcelFactory.load(new File(TABLE_DD));
		int number = excelOperator.getNumberOfSheets();
		for (int index = 5; index < number; index++) {
			String sheetName = excelOperator.getSheetName(index);
			System.out.println("sheetName:" + sheetName);
			SheetOperator sheetOperator = excelOperator.getSheetObject(sheetName);

			Schema schema = createSchemaTableInfo(sheetOperator);

			createCompareSql(schema, sheetName);
		}
	}

	private static Schema createSchemaTableInfo(SheetOperator sheetOperator) {

		String schemaId = String.valueOf(sheetOperator.getCellModel(1, 1).getValue());

		Schema schema = new Schema();
		schema.setSchemaId(schemaId);

		JDBCTable table = null;

		for (int rowIndex = 4; rowIndex <= sheetOperator.getLastRowIndex(); rowIndex++) {

			CellModel cellModel = sheetOperator.getCellModel(rowIndex, 0);

			if ("テーブル名".equals(String.valueOf(cellModel.getValue()))) {
				String tableName = String.valueOf(sheetOperator.getCellModel(rowIndex, 1).getValue()).trim();
				System.out.println(tableName);
				table = new JDBCTable();
				table.setTableName("tableName:" + tableName);
				schema.getTables().put(tableName, table);
				rowIndex++;
				continue;
			}

			CellModel fieldNameCellModel = sheetOperator.getCellModel(rowIndex, 0);
			if (fieldNameCellModel.getValue() == null || "".equals(fieldNameCellModel.getValue().toString())) {
				continue;
			}
			CellModel fieldKanjiCellModel = sheetOperator.getCellModel(rowIndex, 1);
			CellModel lengthCellModel = sheetOperator.getCellModel(rowIndex, 3);
			CellModel pkCellModel = sheetOperator.getCellModel(rowIndex, 4);

			String fieldName = String.valueOf(fieldNameCellModel.getValue()).trim();
			if ("JGLOBAL作成日時".equals(fieldName) || "JGLOBAL更新日時".equals(fieldName)
					|| "JGLOBAL削除日時".equals(fieldName)) {
				continue;
			}
			String fieldKanji = String.valueOf(fieldKanjiCellModel.getValue()).trim();
			int length = NumberUtil.convertInt(String.valueOf(lengthCellModel.getValue()).trim());
			boolean isPk = !TextUtil.isBlank(String.valueOf(pkCellModel.getValue()).trim());

			Field field = new Field();
			field.setPhysicalName(fieldName);
			field.setLogicalName(fieldKanji);
			field.setLength(length);
			field.setPrimaryKey(isPk);
			if (table != null) {
				table.getFields().put(field.getPhysicalName(), field);
			}
		}

		return schema;
	}

	/**
	 *
	 * @param schema
	 * @throws IOException
	 */
	private static void createCompareSql(Schema schema, String sheetName) throws IOException {

		for (String tableName : schema.getTables().keySet()) {
			JDBCTable dumpTable = schema.getTables().get(tableName);

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT").append(Characters.LINE_SEPARATOR_SYSTEM);

			for (Field field : dumpTable.getFields().values()) {
				builder.append("\"");
				builder.append(schemaSrcAlias);
				builder.append("\"");
				builder.append(Characters.DOT);
				builder.append("\"");
				builder.append(field.getPhysicalName());
				builder.append("\"");
				builder.append(Characters.SPACE_HALF);
				builder.append("\"");
				builder.append(field.getPhysicalName() + Characters.UNDERSCORE + schemaSrcAlias);
				builder.append("\"");
				builder.append(",");
				builder.append(Characters.LINE_SEPARATOR_SYSTEM);
				builder.append("\"");
				builder.append(schemaDestAlias);
				builder.append("\"");
				builder.append(Characters.DOT);
				builder.append("\"");
				builder.append(field.getPhysicalName());
				builder.append("\"");
				builder.append(Characters.SPACE_HALF);
				builder.append("\"");
				builder.append(field.getPhysicalName() + Characters.UNDERSCORE + schemaDestAlias);
				builder.append("\"");
				builder.append(",");
				builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			}

			if (!dumpTable.getFields().values().isEmpty()) {
				builder.deleteCharAt(builder.toString().lastIndexOf(","));
			}

			builder.append("FROM");
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			builder.append("\"");
			builder.append(schema.getSchemaId());
			builder.append("\"");
			builder.append(Characters.DOT);
			builder.append("\"");
			builder.append(tableName);
			builder.append("\"");
			builder.append(Characters.SPACE_HALF);
			builder.append("\"");
			builder.append(schemaSrcAlias);
			builder.append("\"");
			builder.append(Characters.SPACE_HALF);
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			builder.append("LEFT JOIN");
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			builder.append("\"");
			builder.append(schema.getSchemaId() + "_" + schemaDestAlias);
			builder.append("\"");
			builder.append(Characters.DOT);
			builder.append("\"");
			builder.append(tableName);
			builder.append("\"");
			builder.append(Characters.SPACE_HALF);
			builder.append("\"");
			builder.append(schemaDestAlias);
			builder.append("\"");
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			builder.append("ON");
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);

			int pknumber = 0;

			for (Field field : dumpTable.getFields().values()) {
				if (field.isPrimaryKey()) {
					if (pknumber > 0) {
						builder.append("AND");
						builder.append(Characters.SPACE_HALF);
					}
					builder.append("\"");
					builder.append(schemaSrcAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" = ");
					builder.append("\"");
					builder.append(schemaDestAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(Characters.LINE_SEPARATOR_SYSTEM);
					pknumber++;
				}
			}
			builder.append("WHERE").append(Characters.LINE_SEPARATOR_SYSTEM);
			int fieldnumber = 0;
			for (Field field : dumpTable.getFields().values()) {
				if (!field.isPrimaryKey()) {
					if (fieldnumber > 0) {
						builder.append("OR");
						builder.append(Characters.SPACE_HALF);
					}

					builder.append("(\"");
					builder.append(schemaSrcAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" IS NULL AND ");
					builder.append("\"");
					builder.append(schemaDestAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" IS NOT NULL) OR ");
					builder.append("(\"");
					builder.append(schemaSrcAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" IS NOT NULL AND ");
					builder.append("\"");
					builder.append(schemaDestAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" IS NULL) OR ");

					builder.append("\"");
					builder.append(schemaSrcAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(" <> ");
					builder.append("\"");
					builder.append(schemaDestAlias);
					builder.append("\"");
					builder.append(Characters.DOT);
					builder.append("\"");
					builder.append(field.getPhysicalName());
					builder.append("\"");
					builder.append(Characters.LINE_SEPARATOR_SYSTEM);
					fieldnumber++;
				}
			}

			builder.append(Characters.LINE_SEPARATOR_SYSTEM);

			File dir = new File(PATH_SQL + sheetName);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File sqlFile = new File(dir.getAbsolutePath() + "/" + tableName + ".sql");
			FileWriter writer = IOFactory.newFileWriter(sqlFile, Encodes.ENCODE_UTF8);
			writer.write(builder.toString());
			writer.close();
		}
	}
}
