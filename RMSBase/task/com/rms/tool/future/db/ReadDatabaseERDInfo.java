package com.rms.tool.future.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.rms.base.constant.Encodes;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
import com.rms.tool.future.erd.bean.ColumnInfo;
import com.rms.tool.future.erd.bean.TableInfo;
import com.rms.tool.future.erd.check.Const;

/**
 *
 * @author ri-meisei
 *
 */
public class ReadDatabaseERDInfo {

	private final static Logger logger = Logger.getLogger(ReadDatabaseERDInfo.class);

	public final static String ERDINFO_FILEPATH = "C:/document/作業内容/【00000000_参考情報】/ERD_INFO.txt";

	public final static Charset ERDINFO_FILEPATH_CHARSET = Encodes.CHARSET_UTF8;

	public final static String SPLIT_CHAR = "`";

	private final Set<TableInfo> dbTableInfoCollection = new TreeSet<>();

	@Test
	public Set<TableInfo> readDBERDInfo() {

		BufferedReader reader = null;
		try {

			TableInfo tableInfo = new TableInfo();

			reader = Files.newBufferedReader(Paths.get(DatabaseERDInfo.ERDINFO_FILEPATH), DatabaseERDInfo.ERDINFO_FILEPATH_CHARSET);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(DatabaseERDInfo.SPLIT_CHAR, -1);
				if (TextUtil.isNotEquals(tableInfo.getTableName(), fields[3])) {
					tableInfo = new TableInfo();
					tableInfo.setDefinedScope(Const.TABLE_DEFINED_DB);
					tableInfo.setDomainNumber(new BigDecimal(fields[0]));
					tableInfo.setDomainName(fields[1]);
					tableInfo.setTableId(new BigDecimal(fields[2]));
					tableInfo.setTableName(fields[3]);
					tableInfo.setTableNameJP(fields[4]);
					tableInfo.setDbTableNameJP(fields[4]);
					tableInfo.setDbTableNameJPConverted(fields[4]);
					dbTableInfoCollection.add(tableInfo);
				}

				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setColumnDefinedScope(Const.COLUMN_DEFINED_DB);
				columnInfo.setColumnId(new BigDecimal(fields[5]));
				columnInfo.setColumnName(fields[6]);
				columnInfo.setColumnNameJP(fields[7]);
				columnInfo.setDbColumnNameJP(fields[7]);
				columnInfo.setDbColumnNameJPConverted(fields[7]);
				if (TextUtil.isNotBlank(fields[8])) {
					columnInfo.setPrimaryKeyDefinedScope(Const.PK_COLUMN_DEFINED_DB);
					columnInfo.setPrimaryKeyPosition(new BigDecimal(fields[8]));
				}

				tableInfo.getColumnInfos().add(columnInfo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dbTableInfoCollection;
	}
}
