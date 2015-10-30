package com.rms.tool.future.erd.check;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.rms.base.constant.Characters;
import com.rms.base.constant.Encodes;
import com.rms.base.io.IOFactory;
import com.rms.base.logging.Logger;
import com.rms.base.util.NumberUtil;
import com.rms.base.util.TextUtil;
import com.rms.tool.future.db.DatabaseERDInfo;
import com.rms.tool.future.erd.bean.ColumnInfo;
import com.rms.tool.future.erd.bean.FileERDInfo;
import com.rms.tool.future.erd.bean.TableInfo;

public class ValidateERDInfo {

	private final static Logger logger = Logger.getLogger(ValidateERDInfo.class);

	private static final String COLUMN_SPLIT = "[　 ]*`[　 ]*";

	final List<String> visiofileList = new ArrayList<>();

	private List<FileERDInfo> visioFileERDInfo = new ArrayList<>();

	private final Set<TableInfo> dbTableInfoCollection = new TreeSet<>();

	private final Set<TableInfo> fileTableInfoCollection = new TreeSet<>();

	private final Set<String> omitedDBTableNameJPCollection = new HashSet<>();

	private final Set<String> omitedDBColumnNameJPCollection = new HashSet<>();

	private final Map<String, String> convertedColumnNameJPMap = new HashMap<>();

	private final Map<String, String> tableNameJPConvertInfoMap = new HashMap<>();

	private final Map<String, Map<String, String>> columnNameJPConvertInfoMap = new HashMap<>();

	private final static String VALIDATE_OUTPUT_FILEPATH = "C:/document/作業内容/【20151013_標準データ及びERD確認】/ERD整合性確認結果.txt";

	private final static String CONVERTED_FILE_TABLEINFO_LIST = "C:/document/作業内容/【20151013_標準データ及びERD確認】/カラム名称変更対象一覧.txt";

	public ValidateERDInfo() {

		initOmitedDBTableNameJPCollection();
		initOmitedDBColumnNameJPCollection();
		initTableNameJPConvertInfoMap();
		initColumnConvertInfoMap();
		initConvertedColumnNameJPMap();
	}

	@Test
	public void validate() throws IOException {

		readFileERDInfo();

		readDBERDInfo();

		Set<TableInfo> allTableInfoSet = new TreeSet<>(dbTableInfoCollection);

		for (TableInfo fileTableInfo : fileTableInfoCollection) {
			String fileTableNameJP = fileTableInfo.getFileTableNameJP();
			String fileTableNameJPConverted = fileTableInfo.getFileTableNameJPConverted();

			for (TableInfo dbTableInfo : allTableInfoSet) {
				String dbTableNameJPConverted = dbTableInfo.getDbTableNameJPConverted();

				if (TextUtil.isEquals(fileTableNameJPConverted, dbTableNameJPConverted)) {

					fileTableInfo.setProcessed(true);
					dbTableInfo.setProcessed(true);

					dbTableInfo.setDefinedScope(Const.TABLE_DEFINED_BOTH);
					dbTableInfo.setTableNameJP(dbTableNameJPConverted);
					dbTableInfo.setFileTableNameJP(fileTableNameJP);
					dbTableInfo.setFileTableNameJPConverted(fileTableNameJPConverted);
					dbTableInfo.setVisioFileName(fileTableInfo.getVisioFileName());
					dbTableInfo.setVisioPageName(fileTableInfo.getVisioPageName());

					for (ColumnInfo fileColumnInfo : fileTableInfo.getColumnInfos()) {
						String fileColumnNameJP = fileColumnInfo.getFileColumnNameJP();
						String fileColumnNameJPConverted = fileColumnInfo.getFileColumnNameJPConverted();

						for (ColumnInfo dbColumnInfo : dbTableInfo.getColumnInfos()) {
							String dbColumnNameJPConverted = dbColumnInfo.getDbColumnNameJPConverted();

							if (TextUtil.isEquals(fileColumnNameJPConverted, dbColumnNameJPConverted)) {
								fileColumnInfo.setProcessed(true);
								dbColumnInfo.setProcessed(true);

								dbColumnInfo.setColumnDefinedScope(Const.COLUMN_DEFINED_BOTH);
								dbColumnInfo.setFileColumnNameJP(fileColumnNameJP);
								dbColumnInfo.setFileColumnNameJPConverted(fileColumnNameJPConverted);
								dbColumnInfo.setVisioFileName(fileColumnInfo.getVisioFileName());
								dbColumnInfo.setVisioPageName(fileColumnInfo.getVisioPageName());
								dbColumnInfo.setFileColumnNameJP(fileColumnInfo.getFileColumnNameJP());
								dbColumnInfo.setSplited(fileColumnInfo.isSplited());

								if (fileColumnInfo.getPrimaryKeyPosition() != null && dbColumnInfo.getPrimaryKeyPosition() != null) {
									dbColumnInfo.setPrimaryKeyDefinedScope(Const.PK_COLUMN_DEFINED_BOTH);
								} else if (fileColumnInfo.getPrimaryKeyPosition() == null && dbColumnInfo.getPrimaryKeyPosition() != null) {
									dbColumnInfo.setPrimaryKeyDefinedScope(Const.PK_COLUMN_DEFINED_DB);
								} else if (fileColumnInfo.getPrimaryKeyPosition() != null && dbColumnInfo.getPrimaryKeyPosition() == null) {
									dbColumnInfo.setPrimaryKeyDefinedScope(Const.PK_COLUMN_DEFINED_FILE);
								} else if (fileColumnInfo.getPrimaryKeyPosition() == null && dbColumnInfo.getPrimaryKeyPosition() == null) {
									dbColumnInfo.setPrimaryKeyDefinedScope(null);
								}

								break;
							}
						}

						if (!fileColumnInfo.isProcessed()) {
							dbTableInfo.getColumnInfos().add(fileColumnInfo);
						}
					}
				}
			}
			if (!fileTableInfo.isProcessed()) {
				allTableInfoSet.add(fileTableInfo);
			}
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(VALIDATE_OUTPUT_FILEPATH));
		StringBuilder hederInfo = new StringBuilder();
		hederInfo.append("DOMAIN_ID");
		hederInfo.append(Characters.TAB);
		hederInfo.append("DOMAIN_NAME");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_ID");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_NAME");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_NAME_JP");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_SCOPE");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_ID");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_NAME");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_NAME_JP");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_SCOPE");
		hederInfo.append(Characters.TAB);
		hederInfo.append("PK_COLUMN_SCOPE");
		hederInfo.append(Characters.TAB);
		hederInfo.append("VISIO_FILE");
		hederInfo.append(Characters.TAB);
		hederInfo.append("VISIO_PAGE");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_NAME_JP(DB)");
		hederInfo.append(Characters.TAB);
		hederInfo.append("TABLE_NAME_JP(FILE)");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_NAME_JP(DB)");
		hederInfo.append(Characters.TAB);
		hederInfo.append("COLUMN_NAME_JP(FILE)");
		writer.write(hederInfo.toString());
		writer.newLine();

		for (TableInfo tableInfo : allTableInfoSet) {
			for (ColumnInfo columnInfo : tableInfo.getColumnInfos()) {

				if (omitedDBColumnNameJPCollection.contains(columnInfo.getColumnNameJP())) {
					continue;
				}

				StringBuilder builder = new StringBuilder();
				builder.append(replaceNull(tableInfo.getDomainNumber()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getDomainName()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getTableId()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getTableName()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getTableNameJP()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getDefinedScope()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getColumnId()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getColumnName()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getColumnNameJP()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getColumnDefinedScope()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getPrimaryKeyDefinedScope()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getVisioFileName()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getVisioPageName()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getDbTableNameJP()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(tableInfo.getFileTableNameJP()));
				builder.append(Characters.TAB);
				builder.append(replaceNull(columnInfo.getDbColumnNameJP()));
				builder.append(Characters.TAB);

				if (columnNameJPConvertInfoMap.containsKey(tableInfo.getFileTableNameJP()) &&
						columnNameJPConvertInfoMap.get(tableInfo.getFileTableNameJP()).values().contains(columnInfo.getFileColumnNameJP())) {
					for (Entry<String, String> entry : columnNameJPConvertInfoMap.get(tableInfo.getFileTableNameJP()).entrySet()) {
						if (TextUtil.isEquals(entry.getValue(), columnInfo.getFileColumnNameJP())) {
							builder.append(entry.getKey());
							break;
						}
					}
				} else {
					builder.append(replaceNull(columnInfo.getFileColumnNameJP()));
				}

				builder.append(Characters.TAB);
				builder.append(validateTableResult(tableInfo, columnInfo));
				builder.append(Characters.TAB);
				builder.append(validateColumnResult(tableInfo, columnInfo));

				writer.write(builder.toString());
				writer.newLine();
			}
		}

		writer.close();
	}

	public String validateTableResult(TableInfo tableInfo, ColumnInfo columnInfo) {

		if (TextUtil.isNotEquals(tableInfo.getDefinedScope(), Const.TABLE_DEFINED_BOTH)) {
			return "要確認";
		}

		if (TextUtil.isEquals(tableInfo.getFileTableNameJP(), tableInfo.getDbTableNameJP())) {
			return "完全一致";
		}
		return "要確認";
	}

	public String validateColumnResult(TableInfo tableInfo, ColumnInfo columnInfo) {

		if (TextUtil.isNotEquals(columnInfo.getColumnDefinedScope(), Const.COLUMN_DEFINED_BOTH)) {
			return "要確認";
		}

		// if(TextUtil.isEquals("出荷グループ（出荷指示）",tableInfo.getFileTableNameJP())){
		// logger.debug(columnInfo.getFileColumnNameJP());
		// if (columnNameJPConvertInfoMap.containsKey(tableInfo.getFileTableNameJP())) {
		// if
		// (columnNameJPConvertInfoMap.get(tableInfo.getFileTableNameJP()).values().contains(columnInfo.getFileColumnNameJP()))
		// {
		// return "一致（項目名変更あり）";
		// }
		// }
		// }

		if (columnNameJPConvertInfoMap.containsKey(tableInfo.getFileTableNameJP())) {
			if (columnNameJPConvertInfoMap.get(tableInfo.getFileTableNameJP()).values().contains(columnInfo.getFileColumnNameJP())) {
				return "一致（項目名変更あり）";
			}
		}

		if (TextUtil.isEquals(columnInfo.getFileColumnNameJP(), columnInfo.getDbColumnNameJP())) {
			return "完全一致";
		}

		if (columnInfo.isSplited()) {
			return "一致（項目展開あり）";
		}

		if (convertedColumnNameJPMap.containsKey(columnInfo.getFileColumnNameJP())) {
			return "一致（項目説明あり）";
		}

		if (TextUtil.isEquals(TextUtil.convertHalfToFull(columnInfo.getFileColumnNameJP().replaceAll("　", "")),
				TextUtil.convertHalfToFull(columnInfo.getDbColumnNameJP()).replaceAll("　", ""))) {
			return "一致（全半角スペース変換あり）";
		}

		return "要確認";
	}

	public void readFileERDInfo() {

		BufferedReader reader = null;
		try {
			String fileERD = "C:/document/作業内容/【20151013_標準データ及びERD確認】/vba/【WMS】【SD】概念ERD.txt";
			String fileERDCom = "C:/document/作業内容/【20151013_標準データ及びERD確認】/vba/【WMS】【SD】概念ERD_共通.txt";
			visiofileList.add(fileERD);
			visiofileList.add(fileERDCom);

			for (String visioFile : visiofileList) {
				reader = IOFactory.newBufferedReader(visioFile, Encodes.CHARSET_SJIS);
				String line = null;
				while ((line = reader.readLine()) != null) {
					String[] fields = line.split("\t");
					FileERDInfo fileERDInfo = new FileERDInfo();
					fileERDInfo.setFileName(fields[1]);
					fileERDInfo.setPageId(fields[3]);
					fileERDInfo.setPageName(fields[4]);
					fileERDInfo.setWidth(fields[6]);
					fileERDInfo.setHeight(fields[7]);
					fileERDInfo.setPositionX(fields[8]);
					fileERDInfo.setPositionY(fields[9]);
					fileERDInfo.setPrimaryKey(fields[11]);
					fileERDInfo.setItems(fields[13]);
					visioFileERDInfo.add(fileERDInfo);
				}
				if (reader != null) {
					reader.close();
				}
			}
			processFileERDInfos();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readDBERDInfo() {

		BufferedReader reader = null;
		try {

			TableInfo tableInfo = new TableInfo();

			reader = Files.newBufferedReader(Paths.get(DatabaseERDInfo.ERDINFO_FILEPATH), DatabaseERDInfo.ERDINFO_FILEPATH_CHARSET);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(DatabaseERDInfo.SPLIT_CHAR, -1);
				if (omitedDBTableNameJPCollection.contains(fields[4])) {
					continue;
				}
				if (omitedDBColumnNameJPCollection.contains(fields[7])) {
					continue;
				}
				if (TextUtil.isNotEquals(tableInfo.getTableName(), fields[3])) {
					tableInfo = new TableInfo();
					tableInfo.setDefinedScope(Const.TABLE_DEFINED_DB);
					tableInfo.setDomainNumber(new BigDecimal(fields[0]));
					tableInfo.setDomainName(fields[1]);
					tableInfo.setTableId(new BigDecimal(fields[2]));
					tableInfo.setTableName(fields[3]);
					tableInfo.setTableNameJP(convertVal(fields[4]));
					tableInfo.setDbTableNameJP(fields[4]);
					tableInfo.setDbTableNameJPConverted(convertVal(fields[4]));
					dbTableInfoCollection.add(tableInfo);
				}

				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setColumnDefinedScope(Const.COLUMN_DEFINED_DB);
				columnInfo.setColumnId(new BigDecimal(fields[5]));
				columnInfo.setColumnName(fields[6]);
				columnInfo.setColumnNameJP(convertVal(fields[7]));
				columnInfo.setDbColumnNameJP(fields[7]);
				columnInfo.setDbColumnNameJPConverted(convertVal(fields[7]));
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
	}

	private void processFileERDInfos() {

		for (FileERDInfo tableNameInfo : visioFileERDInfo) {
			if (tableNameInfo.isProcessed()) {
				continue;
			}
			if (TextUtil.isNotBlank(tableNameInfo.getPrimaryKey())) {
				continue;
			}
			if (TextUtil.isBlank(tableNameInfo.getItems())) {
				continue;
			}
			if (tableNameInfo.getItems().split(COLUMN_SPLIT).length > 1) {
				continue;
			}

			long minTotalDistance = Long.MAX_VALUE;
			int srcWidth = Integer.parseInt(tableNameInfo.getWidth());
			int srcHeight = Integer.parseInt(tableNameInfo.getHeight());
			int srcPositionX = Integer.parseInt(tableNameInfo.getPositionX()) - srcWidth / 2;
			int srcPositionY = Integer.parseInt(tableNameInfo.getPositionY()) - srcHeight / 2;

			FileERDInfo itemsInfo = null;
			for (FileERDInfo tableItemsInfo : visioFileERDInfo) {
				if (tableNameInfo.isProcessed()) {
					continue;
				}
				if (tableNameInfo == tableItemsInfo) {
					continue;
				}
				if (TextUtil.isNotEquals(tableNameInfo.getFileName(), tableItemsInfo.getFileName())) {
					continue;
				}
				if (TextUtil.isNotEquals(tableNameInfo.getPageName(), tableItemsInfo.getPageName())) {
					continue;
				}

				int destWidth = Integer.parseInt(tableItemsInfo.getWidth());
				int destHeight = Integer.parseInt(tableItemsInfo.getHeight());
				int destPositionX = Integer.parseInt(tableItemsInfo.getPositionX());
				int destPositionY = Integer.parseInt(tableItemsInfo.getPositionY());

				if (srcPositionX > destPositionX + destWidth) {
					continue;
				}

				if (srcPositionY < destPositionY - destHeight) {
					continue;
				}
				long distanceX = Math.abs(srcPositionX - destPositionX);
				long distanceY = Math.abs(srcPositionY - destPositionY);
				long totalDistance = distanceX + distanceY;
				if (totalDistance <= minTotalDistance) {
					itemsInfo = tableItemsInfo;
					minTotalDistance = totalDistance;
				}
			}

			if (itemsInfo == null) {
				throw new RuntimeException("テーブル[" + tableNameInfo.getItems() + "]の情報が見つかりません。");
			}

			tableNameInfo.setProcessed(true);
			itemsInfo.setProcessed(true);

			if (tableNameJPConvertInfoMap.containsKey(tableNameInfo.getItems())) {
				tableNameInfo.setItems(tableNameJPConvertInfoMap.get(tableNameInfo.getItems()));
			}

			TableInfo tableInfo = new TableInfo();
			tableInfo.setDefinedScope(Const.TABLE_DEFINED_FILE);
			tableInfo.setTableNameJP(convertVal(tableNameInfo.getItems()));
			tableInfo.setFileTableNameJP(tableNameInfo.getItems());
			tableInfo.setFileTableNameJPConverted(convertVal(tableNameInfo.getItems()));
			tableInfo.setVisioFileName(tableNameInfo.getFileName());
			tableInfo.setVisioPageName(tableNameInfo.getPageName());
			if (TextUtil.isBlank(tableInfo.getFileTableNameJP())) {
				continue;
			}
			List<String> primaryKeys = new ArrayList<>(Arrays.asList(itemsInfo.getPrimaryKey().split(COLUMN_SPLIT)));
			primaryKeys.removeIf(element -> TextUtil.isBlank(element));
			List<String> columnNameJPs = new ArrayList<>(Arrays.asList(itemsInfo.getItems().split(COLUMN_SPLIT)));
			columnNameJPs.removeIf(element -> TextUtil.isBlank(element));

			for (String tableNameJP : columnNameJPConvertInfoMap.keySet()) {
				if (TextUtil.isEquals(tableNameJP, tableInfo.getFileTableNameJP())) {
					for (String columnNameJP : columnNameJPConvertInfoMap.get(tableNameJP).keySet()) {
						if (primaryKeys.contains(columnNameJP)) {
							primaryKeys.remove(columnNameJP);
							primaryKeys.add(columnNameJPConvertInfoMap.get(tableNameJP).get(columnNameJP));
						}
						if (columnNameJPs.contains(columnNameJP)) {
							columnNameJPs.remove(columnNameJP);
							columnNameJPs.add(columnNameJPConvertInfoMap.get(tableNameJP).get(columnNameJP));
						}
					}
				}
			}

			List<String> splitedColumnList = new ArrayList<>();
			List<String> columnNameJPList = new ArrayList<>();
			Map<String, String> convertedColumnNameMap = new HashMap<>();

			for (String columnNameJP : columnNameJPs) {
				String columnNameJPConverted = convertVal(convertColumnNameJP(columnNameJP));

				Pattern pattern = Pattern.compile("(.+)([０-９]+)([-〜])([０-９]+)(.*)");
				Matcher matcher = pattern.matcher(columnNameJPConverted);
				if (matcher.matches()) {
					int count = matcher.groupCount();
					// String grp0 = matcher.group(0);
					String grp1 = matcher.group(1);
					String grp2 = matcher.group(2);
					// String grp3 = matcher.group(3);
					String grp4 = matcher.group(4);
					String grp5 = "";
					if (count == 5) {
						grp5 = matcher.group(5);
					}

					grp2 = NumberUtil.convertNumberFullToHalf(grp2);
					grp4 = NumberUtil.convertNumberFullToHalf(grp4);
					int from = Integer.parseInt(grp2);
					int to = Integer.parseInt(grp4);
					for (int i = from; i <= to; i++) {
						String newColumnNameJP = grp1 + NumberUtil.convertNumberHalfToFull(i) + grp5;
						columnNameJPList.add(newColumnNameJP);
						convertedColumnNameMap.put(newColumnNameJP, columnNameJP);
						splitedColumnList.add(newColumnNameJP);
					}
				} else {
					columnNameJPList.add(columnNameJPConverted);
					convertedColumnNameMap.put(columnNameJPConverted, columnNameJP);
				}
			}

			for (String columnNameJP : columnNameJPList) {
				if (TextUtil.isBlank(columnNameJP)) {
					continue;
				}
				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setColumnDefinedScope(Const.COLUMN_DEFINED_FILE);
				columnInfo.setColumnNameJP(columnNameJP);
				columnInfo.setFileColumnNameJP(convertedColumnNameMap.get(columnNameJP));
				columnInfo.setFileColumnNameJPConverted(columnNameJP);
				if (primaryKeys.contains(columnNameJP)) {
					columnInfo.setPrimaryKeyDefinedScope(Const.PK_COLUMN_DEFINED_FILE);
					columnInfo.setPrimaryKeyPosition(new BigDecimal(1));
				}
				columnInfo.setVisioFileName(itemsInfo.getFileName());
				columnInfo.setVisioPageName(itemsInfo.getPageName());
				columnInfo.setSplited(splitedColumnList.contains(columnNameJP));

				tableInfo.getColumnInfos().add(columnInfo);
			}

			fileTableInfoCollection.add(tableInfo);
		}
	}

	private Object replaceNull(Object obj) {

		if (obj == null) {
			return "";
		}

		return obj;
	}

	private String convertVal(String value) {

		String convertVal = TextUtil.convertHalfToFull(value);
		convertVal = convertVal.toUpperCase();
		convertVal = convertVal.replaceAll("　", "");

		return convertVal;
	}

	private String convertColumnNameJP(String columnNameJP) {

		if (convertedColumnNameJPMap.containsKey(columnNameJP)) {
			return convertedColumnNameJPMap.get(columnNameJP);
		}

		return columnNameJP;
	}

	private void initColumnConvertInfoMap() {

		BufferedReader reader = null;
		try {

			reader = Files.newBufferedReader(Paths.get(CONVERTED_FILE_TABLEINFO_LIST), Encodes.CHARSET_UTF8);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(Characters.TAB, -1);

				String tableNameJP = fields[14];
				String columnNameJP = fields[16];
				String columnNameJPConverted = fields[19];

				if (!columnNameJPConvertInfoMap.containsKey(tableNameJP)) {
					columnNameJPConvertInfoMap.put(tableNameJP, new HashMap<>());
				}

				columnNameJPConvertInfoMap.get(tableNameJP).put(columnNameJP, columnNameJPConverted);
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
	}

	private void initOmitedDBTableNameJPCollection() {

		omitedDBTableNameJPCollection.add("rBatchキャッシュ世代管理");
		omitedDBTableNameJPCollection.add("rBatchジョブ名称マスタ");
		omitedDBTableNameJPCollection.add("rBatchジョブ定義履歴");
		omitedDBTableNameJPCollection.add("rBatchスケジュール定義履歴");
		omitedDBTableNameJPCollection.add("rBatchスケジュール説明マスタ");
		omitedDBTableNameJPCollection.add("rPartsBL名称マスタ");
		omitedDBTableNameJPCollection.add("rParts処理名称マスタ");
		omitedDBTableNameJPCollection.add("アップロードファイル管理");
		omitedDBTableNameJPCollection.add("キャッシュ世代データ管理");
		omitedDBTableNameJPCollection.add("クライアントストレージ");
		omitedDBTableNameJPCollection.add("サンプル_検索結果一覧ワーク");
		omitedDBTableNameJPCollection.add("テーブルバックアップログ");
		omitedDBTableNameJPCollection.add("データリフレッシュサービス管理");
		omitedDBTableNameJPCollection.add("データリフレッシュ実行管理");
		omitedDBTableNameJPCollection.add("データリフレッシュ要求管理");
		omitedDBTableNameJPCollection.add("ファイル種別マスタ");
		omitedDBTableNameJPCollection.add("ファイル管理");
		omitedDBTableNameJPCollection.add("プロセス定義差替マスタ");
		omitedDBTableNameJPCollection.add("リフレッシュデータグループIDXマスタ");
		omitedDBTableNameJPCollection.add("リフレッシュデータグループマスタ");
		omitedDBTableNameJPCollection.add("リフレッシュデータマスタ");
		omitedDBTableNameJPCollection.add("ロケーション情報一覧更新一時表");
		omitedDBTableNameJPCollection.add("倉庫荷主インデックス反映一時表");
		omitedDBTableNameJPCollection.add("出力ファイル振分項目定義設定ワーク");
		omitedDBTableNameJPCollection.add("出荷反映ワーク（出荷グループ）");
		omitedDBTableNameJPCollection.add("機能利用可能設定MVIEW");
		omitedDBTableNameJPCollection.add("汎用項目具象項目インデックスマスタ");
		omitedDBTableNameJPCollection.add("論理項目分類定義マスタ");
		omitedDBTableNameJPCollection.add("開閉局グループマスタ");
		omitedDBTableNameJPCollection.add("開閉局グループ機能IDXマスタ");
		omitedDBTableNameJPCollection.add("開閉局条件管理");
		omitedDBTableNameJPCollection.add("開閉局管理");
		omitedDBTableNameJPCollection.add("項目エイリアスマスタ");
		omitedDBTableNameJPCollection.add("項目マスタ");
		omitedDBTableNameJPCollection.add("項目マスタ（画面のみ）");
		omitedDBTableNameJPCollection.add("項目除外マスタ（画面利用無）");
	}

	private void initOmitedDBColumnNameJPCollection() {

		omitedDBColumnNameJPCollection.add("排他制御カウンタ");
		omitedDBColumnNameJPCollection.add("登録日時");
		omitedDBColumnNameJPCollection.add("登録ユーザ");
		omitedDBColumnNameJPCollection.add("登録端末");
		omitedDBColumnNameJPCollection.add("登録プログラム");
		omitedDBColumnNameJPCollection.add("更新日時");
		omitedDBColumnNameJPCollection.add("更新ユーザ");
		omitedDBColumnNameJPCollection.add("更新端末");
		omitedDBColumnNameJPCollection.add("更新プログラム");
	}

	private void initTableNameJPConvertInfoMap() {

		tableNameJPConvertInfoMap.put("rBatchジョブマスタ", "rBatchジョブ定義マスタ");
		tableNameJPConvertInfoMap.put("rBatchスケジュールマスタ", "rBatchスケジュール定義マスタ");
		tableNameJPConvertInfoMap.put("rBatch実行キュー", "rBatch実行キュー管理");
		tableNameJPConvertInfoMap.put("rBatch実行スケジュール", "rBatch実行スケジュール管理");
		tableNameJPConvertInfoMap.put("rBatch実行パラメータ値管理", "rBatch実行リクエストパラメータ管理");
		tableNameJPConvertInfoMap.put("rBatch実行結果", "rBatch実行結果管理");
		tableNameJPConvertInfoMap.put("グリッド構成管理", "rDesignグリッド構成管理");
		tableNameJPConvertInfoMap.put("グリッド管理", "rDesignグリッド管理");
		tableNameJPConvertInfoMap.put("バリデーショングループマスタ", "rDesignバリデーショングループマスタ");
		tableNameJPConvertInfoMap.put("バリデーションマスタ", "rDesignバリデーションマスタ");
		tableNameJPConvertInfoMap.put("ベースグリッド管理", "rDesignベースグリッド管理");
		tableNameJPConvertInfoMap.put("具象項目テンプレートマスタ", "rDesign具象項目テンプレートマスタ");
		tableNameJPConvertInfoMap.put("具象項目マスタ", "rDesign具象項目マスタ");
		tableNameJPConvertInfoMap.put("具象項目別サイズマスタ", "rDesign具象項目別サイズマスタ");
		tableNameJPConvertInfoMap.put("サイズ変更マスタ", "rDesign具象項目別サイズ変更マスタ");
		tableNameJPConvertInfoMap.put("具象項目別バリデーションマスタ", "rDesign具象項目別バリデーションマスタ");
		tableNameJPConvertInfoMap.put("バリデーション変更マスタ", "rDesign具象項目別バリデーション変更マスタ");
		tableNameJPConvertInfoMap.put("具象項目別値マスタ", "rDesign具象項目別値マスタ");
		tableNameJPConvertInfoMap.put("値変更マスタ", "rDesign具象項目別値変更マスタ");
		tableNameJPConvertInfoMap.put("具象項目別属性マスタ", "rDesign具象項目別属性マスタ");
		tableNameJPConvertInfoMap.put("属性変更マスタ", "rDesign具象項目別属性変更マスタ");
		tableNameJPConvertInfoMap.put("機能項目IDXマスタ", "rDesign機能項目IDXマスタ");
		tableNameJPConvertInfoMap.put("機能項目IDX変更マスタ", "rDesign機能項目IDX変更マスタ");
		tableNameJPConvertInfoMap.put("編集可能パネルマスタ", "rDesign編集可能パネルマスタ");
		tableNameJPConvertInfoMap.put("要素マスタ", "rDesign要素マスタ");
		tableNameJPConvertInfoMap.put("要素別属性マスタ", "rDesign要素別属性マスタ");
		tableNameJPConvertInfoMap.put("論理項目マスタ", "rDesign論理項目マスタ");
		tableNameJPConvertInfoMap.put("項目IDマスタ", "rDesign項目IDマスタ");
		tableNameJPConvertInfoMap.put("項目ID振替マスタ", "rDesign項目ID振替マスタ");
		tableNameJPConvertInfoMap.put("項目テンプレートマスタ", "rDesign項目テンプレートマスタ");
		tableNameJPConvertInfoMap.put("項目テンプレート別属性マスタ", "rDesign項目テンプレート別属性マスタ");
		tableNameJPConvertInfoMap.put("項目テンプレート構成マスタ", "rDesign項目テンプレート構成マスタ");
		tableNameJPConvertInfoMap.put("項目名称マスタ", "rDesign項目名称マスタ");
		tableNameJPConvertInfoMap.put("項目名称変更マスタ", "rDesign項目名称変更マスタ");
		tableNameJPConvertInfoMap.put("要素構成管理", "rDesign項目構成管理");
		tableNameJPConvertInfoMap.put("挿入グループマスタ", "rInjection挿入グループマスタ");
		tableNameJPConvertInfoMap.put("挿入グループ変更マスタ", "rInjection挿入グループ変更マスタ");
		tableNameJPConvertInfoMap.put("機能挿入グループIDXマスタ", "rInjection機能挿入グループIDXマスタ");
		tableNameJPConvertInfoMap.put("機能表示文言IDXマスタ", "rInjection機能表示文言IDXマスタ");
		tableNameJPConvertInfoMap.put("表示文言グループマスタ", "rInjection表示文言グループマスタ");
		tableNameJPConvertInfoMap.put("表示文言グループ変更マスタ", "rInjection表示文言グループ変更マスタ");
		tableNameJPConvertInfoMap.put("BLグループ差替マスタ", "rPartsBLグループ差替マスタ");
		tableNameJPConvertInfoMap.put("BL差替マスタ", "rPartsBL差替マスタ");
		tableNameJPConvertInfoMap.put("rBatchジョブ処理マスタ", "rPartsジョブ処理マスタ");
		tableNameJPConvertInfoMap.put("rBatch実行定義管理", "rParts実行定義管理");
		tableNameJPConvertInfoMap.put("画面処理定義差替マスタ", "rParts画面処理差替マスタ");
		tableNameJPConvertInfoMap.put("一覧表示ヘッダーグループマスタ", "rTable一覧表示ヘッダーグループマスタ");
		tableNameJPConvertInfoMap.put("一覧表示マスタ", "rTable一覧表示マスタ");
		tableNameJPConvertInfoMap.put("一覧表示項目グループマスタ", "rTable一覧表示項目グループマスタ");
		tableNameJPConvertInfoMap.put("一覧表示項目グループ変更マスタ", "rTable一覧表示項目グループ変更マスタ");
		tableNameJPConvertInfoMap.put("ハンディロック解除ワーク", "ハンディロック解除ワーク一時表");
		tableNameJPConvertInfoMap.put("区分編集制御マスタ（WMS）", "区分編集制御マスタ");
		tableNameJPConvertInfoMap.put("転送識別子マスタ", "転送識別子マスタ（WMS）");
		tableNameJPConvertInfoMap.put("BLグループ分類インデックス", "");
		tableNameJPConvertInfoMap.put("BLグループ分類マスタ", "");
		tableNameJPConvertInfoMap.put("rBatch実行パラメータ定義管理", "");
	}

	private void initConvertedColumnNameJPMap() {

		// convertedColumnNameJPMap.put("(作成中、作成完了)", "");
		// convertedColumnNameJPMap.put("※CSV", "");
		// convertedColumnNameJPMap.put("※CSVの場合", "");
		// convertedColumnNameJPMap.put("※以下商品マスタと同一項目", "");
		// convertedColumnNameJPMap.put("※保管用汎用項目除く", "");
		// convertedColumnNameJPMap.put("※処理実行管理（集配信単位）と同項目を保持", "");
		// convertedColumnNameJPMap.put("※固定長", "");
		// convertedColumnNameJPMap.put("※固定長の場合", "");
		// convertedColumnNameJPMap.put("※集信ワークと同じ項目", "");
		// convertedColumnNameJPMap.put("★入力", "");
		// convertedColumnNameJPMap.put("★出力", "");
		// convertedColumnNameJPMap.put("〜", "");
		convertedColumnNameJPMap.put("アクションID（カンマ区切りで複数指定可）", "アクションID");
		convertedColumnNameJPMap.put("グリッド幅（カンマ区切り）", "グリッド幅");
		convertedColumnNameJPMap.put("グリッド高さ（カンマ区切り）", "グリッド高さ");
		convertedColumnNameJPMap.put("コンフィグレーション単位区分（システム、企業、荷主、倉庫、倉庫/荷主）", "コンフィグレーション単位区分");
		convertedColumnNameJPMap.put("サイクル実行区分（有、無）", "サイクル実行区分");
		convertedColumnNameJPMap.put("サイクル期間単位（日、週、月、年）", "サイクル期間単位");
		convertedColumnNameJPMap.put("サービス種別区分(入荷、出荷、在庫)", "サービス種別区分");
		convertedColumnNameJPMap.put("サービス種別区分（入荷、出荷）", "サービス種別区分");
		convertedColumnNameJPMap.put("サービス種別区分（入荷・出荷・在庫…）", "サービス種別区分");
		convertedColumnNameJPMap.put("サービス種別区分（出荷、入荷、在庫、マスタメンテナンス、共通、システム設定）", "サービス種別区分");
		convertedColumnNameJPMap.put("データ登録元区分　（データ連携、CSV取込、手入力）", "データ登録元区分");
		convertedColumnNameJPMap.put("データ登録元区分（データ連携、CSV取込、手入力）", "データ登録元区分");
		convertedColumnNameJPMap.put("ハンディ作業状況区分（作業可、作業中）", "ハンディ作業状況区分");
		convertedColumnNameJPMap.put("ハンディ作業状況区分（未作業、作業中）", "ハンディ作業状況区分");
		convertedColumnNameJPMap.put("ハンディ入荷作業内容区分　　（検品、入荷受付…）", "ハンディ入荷作業内容区分");
		convertedColumnNameJPMap.put("バリデーションID（Alternate Key)", "バリデーションID");
		convertedColumnNameJPMap.put("バリデーショングループID（Alternate Key)", "バリデーショングループID");
		convertedColumnNameJPMap.put("バリデーショングループID（入出力機能用）", "バリデーショングループID");
		convertedColumnNameJPMap.put("バリデーションタイプ（class、属性）", "バリデーションタイプ");
		convertedColumnNameJPMap.put("パラメータフィルタキー ・・・パラメータ拡張設定IDが格納される", "パラメータフィルタキー");
		convertedColumnNameJPMap.put("パラメータ分類区分　　・・・　コンフィグ設定のタブ単位", "パラメータ分類区分");
		convertedColumnNameJPMap.put("パラメータ設定単位区分　（企業、荷主、倉庫、荷主・倉庫、?　　機能、JOB、入出力、プロセス）", "パラメータ設定単位区分");
		convertedColumnNameJPMap.put("フォームグループ名　…ディレクトリの単位", "フォームグループ名");
		convertedColumnNameJPMap.put("プロセス作業状況区分(未着手、作業中、作業完了)", "プロセス作業状況区分");
		convertedColumnNameJPMap.put("プロセス作業状況区分（未作業、作業中、作業完了）", "プロセス作業状況区分");
		convertedColumnNameJPMap.put("プロセス処理区分（入荷検品、シングルピック）", "プロセス処理区分");
		convertedColumnNameJPMap.put("プロセス種別（入荷検品、ピッキング、仕分）", "プロセス種別");
		convertedColumnNameJPMap.put("メニュー区分　（ルート、カテゴリルート、画面）", "メニュー区分");
		convertedColumnNameJPMap.put("メニュー端末種類区分　（PC,HT）", "メニュー端末種類区分");
		convertedColumnNameJPMap.put("レイアウト区分　（シングル・マルチ）", "レイアウト区分");
		convertedColumnNameJPMap.put("レコード形式区分　（固定長、可変長）", "レコード形式区分");
		convertedColumnNameJPMap.put("レポート種別（CSV、PDF）", "レポート種別");
		convertedColumnNameJPMap.put("ロケーション属性制御設定区分　（棚入可、棚入可（ワーニング）、棚入不可、・・・）", "ロケーション属性制御設定区分");
		convertedColumnNameJPMap.put("ロケーション属性区分　（冷凍、冷蔵、危険物第一類等）", "ロケーション属性区分");
		convertedColumnNameJPMap.put("ロケーション属性用途区分（棚入れチェック、引当優先…）", "ロケーション属性用途区分");
		convertedColumnNameJPMap.put("ロケーション属性種類区分　（温度帯・危険物・品質区分…）", "ロケーション属性種類区分");
		convertedColumnNameJPMap.put("ロールID（Alternate Key)", "ロールID");
		convertedColumnNameJPMap.put("伝票番号設定内容区分　（発注伝票番号、納品伝票番号）", "伝票番号設定内容区分");
		convertedColumnNameJPMap.put("伝票種別　（入荷伝票、返品伝票、預り伝票等）", "伝票種別");
		convertedColumnNameJPMap.put("伝票種別（品質区分振替,商品振替,商品ロット振替）", "伝票種別");
		convertedColumnNameJPMap.put("伝票種別（在庫調整入庫・在庫調整出庫）", "伝票種別");
		convertedColumnNameJPMap.put("入出力区分　　　（入力、出力）", "入出力区分");
		convertedColumnNameJPMap.put("入出力区分　（入力、出力）", "入出力区分");
		convertedColumnNameJPMap.put("入庫済区分　（未入庫、入庫済）", "入庫済区分");
		convertedColumnNameJPMap.put("入荷済区分　（未入荷、入荷済）", "入荷済区分");
		convertedColumnNameJPMap.put("具象項目ID（Alternate Key)", "具象項目ID");
		convertedColumnNameJPMap.put("具象項目テンプレートID（Alternate Key)", "具象項目テンプレートID");
		convertedColumnNameJPMap.put("具象項目テンプレートID（NN）", "具象項目テンプレートID");
		convertedColumnNameJPMap.put("具象項目テンプレート名称（UK）", "具象項目テンプレート名称");
		convertedColumnNameJPMap.put("具象項目名称（UK）", "具象項目名称");
		convertedColumnNameJPMap.put("処理実行状況区分（0:未実行、1：実行中）", "処理実行状況区分");
		convertedColumnNameJPMap.put("処理種類分類　（入荷××、…）", "処理種類分類");
		convertedColumnNameJPMap.put("処理種類区分（ジョブ、画面）", "処理種類区分");
		convertedColumnNameJPMap.put("出力種別(汎用帳票、個別帳票、配信、印刷）", "出力種別");
		convertedColumnNameJPMap.put("出力種別　（ダウンロードファイル作成、配信、印刷）", "出力種別");
		convertedColumnNameJPMap.put("出力種別（汎用帳票、個別帳票、配信、印刷）", "出力種別");
		convertedColumnNameJPMap.put("出荷伝票検索用汎用区分1名称〜10名称", "出荷伝票検索用汎用区分1〜10名称");
		convertedColumnNameJPMap.put("出荷停止区分　（停止無、停止（保留）、停止（欠品））", "出荷停止区分");
		convertedColumnNameJPMap.put("出荷停止区分　（稼働、停止）", "出荷停止区分");
		convertedColumnNameJPMap.put("出荷指示元区分　（バッチ起動、画面指示）", "出荷指示元区分");
		convertedColumnNameJPMap.put("出荷管理汎用区分1名称〜5名称", "出荷管理汎用区分1〜5名称");
		convertedColumnNameJPMap.put("分納区分　（通常、分納）", "分納区分");
		convertedColumnNameJPMap.put("利用区分（利用する、利用しない）", "利用区分");
		convertedColumnNameJPMap.put("利用機能区分　（画面、権限項目…）", "利用機能区分");
		convertedColumnNameJPMap.put("利用状況区分（有効、無効）", "利用状況区分");
		convertedColumnNameJPMap.put("制御ID（Alternate Key)", "制御ID");
		convertedColumnNameJPMap.put("制御種別（保留／スキップ）", "制御種別");
		convertedColumnNameJPMap.put("制御種別（表示項目、機能、権限、データ、入出力 etc）", "制御種別");
		convertedColumnNameJPMap.put("前回引当結果区分　（引当、欠品）", "前回引当結果区分");
		convertedColumnNameJPMap.put("印刷、汎用帳票、インポート）", "");
		convertedColumnNameJPMap.put("印刷ファイルタイプ（印刷指示、データCSV、帳票フォーム）", "印刷ファイルタイプ");
		convertedColumnNameJPMap.put("印刷モジュール状態（待機中、通信中、印刷処理中、切断）", "印刷モジュール状態");
		convertedColumnNameJPMap.put("印刷状態（未印刷、印刷中、印刷済）", "印刷状態");
		convertedColumnNameJPMap.put("取引先種別　（入荷元、出荷先、届け先、請求先、仕入先…）", "取引先種別");
		convertedColumnNameJPMap.put("取込エラー単位区分　（伝票、明細）", "取込エラー単位区分");
		convertedColumnNameJPMap.put("取込処理シーケンスNO　…取込処理開始時に更新する。", "取込処理シーケンスNO");
		convertedColumnNameJPMap.put("品質区分　（良品、不良品）", "品質区分");
		convertedColumnNameJPMap.put("商品カテゴリ用途区分　（出荷用、商品管理用、汎用１〜４）", "商品カテゴリ用途区分");
		convertedColumnNameJPMap.put("商品ロケーション設定区分　（固定・推奨（自動）・推奨（手入力）・禁止）", "商品ロケーション設定区分");
		convertedColumnNameJPMap.put("商品ロット入力区分　（未入力、一部入力、入力済み）", "商品ロット入力区分");
		convertedColumnNameJPMap.put("問合せ番号発番単位　（単一個口、複数個口）", "問合せ番号発番単位");
		convertedColumnNameJPMap.put("問合せ番号登録方法区分（自動（代表）、自動（分割）、手動）", "問合せ番号登録方法区分");
		convertedColumnNameJPMap.put("在庫引当事由区分　（移動、棚卸、出荷引当、調整待ち）", "在庫引当事由区分");
		convertedColumnNameJPMap.put("在庫引当状況区分　（引当中、引当取消、出庫済み）", "在庫引当状況区分");
		convertedColumnNameJPMap.put("在庫振替明細状況区分（未対応、在庫振替中、変更済、報告済）", "在庫振替明細状況区分");
		convertedColumnNameJPMap.put("在庫振替状況区分　（未対応、在庫振替中、変更済、報告済）", "在庫振替状況区分");
		convertedColumnNameJPMap.put("在庫振替状況区分　（未対応、移動中、名義変更中、変更済）", "在庫振替状況区分");
		convertedColumnNameJPMap.put("在庫振替状況区分（未対応、在庫振替中、変更済、報告済）", "在庫振替状況区分");
		convertedColumnNameJPMap.put("在庫調整区分　　（在庫照合調整、棚卸調整、臨時調整）", "在庫調整区分");
		convertedColumnNameJPMap.put("在庫調整区分　（在庫照合調整、棚卸調整、臨時調整）", "在庫調整区分");
		convertedColumnNameJPMap.put("在庫調整状況区分　（調整中、調整完了、キャンセル）", "在庫調整状況区分");
		convertedColumnNameJPMap.put("在庫調整状況区分（未調整、調整中、調整完了、調整取消）", "在庫調整状況区分");
		convertedColumnNameJPMap.put("報告済区分　　　（未報告、報告済み）", "報告済区分");
		convertedColumnNameJPMap.put("多重起動（可、待ち、中止）", "多重起動");
		convertedColumnNameJPMap.put("子項目構成シーケンスNO（Alternate Key)", "子項目構成シーケンスNO");
		convertedColumnNameJPMap.put("定型文種類区分（伝票文言、記事欄・・・）", "定型文種類区分");
		convertedColumnNameJPMap.put("実績計上区分（入荷、入庫、出荷、出庫等）", "実績計上区分");
		convertedColumnNameJPMap.put("実行処理種別（01：出荷引当、02：出荷引当解除）", "実行処理種別");
		convertedColumnNameJPMap.put("実行制御内容区分（0：実施可能、1：実施不可）", "実行制御内容区分");
		convertedColumnNameJPMap.put("実行区分　（スケジュール実行、手動実行、自動実行）", "実行区分");
		convertedColumnNameJPMap.put("実行状況区分　（未実行、実行待機、実行中、保留中、異常終了、キャンセル、異常終了）", "実行状況区分");
		convertedColumnNameJPMap.put("導入内容区分　（テンプレート、項目設定・・・）", "導入内容区分");
		convertedColumnNameJPMap.put("市区町村コード（NULLABLE）", "市区町村コード");
		convertedColumnNameJPMap.put("引当ロック事由区分　（出荷引当、棚卸開始、）", "引当ロック事由区分");
		convertedColumnNameJPMap.put("引当処理区分　（引当、引当戻し）", "引当処理区分");
		convertedColumnNameJPMap.put("引当処理状況　（引当処理中、引当処理無）", "引当処理状況");
		convertedColumnNameJPMap.put("引当区分　（仮引当、本引当）", "引当区分");
		convertedColumnNameJPMap.put("指定曜日（第1〜5、全て、最終）", "指定曜日");
		convertedColumnNameJPMap.put("指示種別（印刷、CSV連携）", "指示種別");
		convertedColumnNameJPMap.put("挿入タイプ（before、after等）", "挿入タイプ");
		convertedColumnNameJPMap.put("挿入内容タイプ（パネル、具象項目）", "挿入内容タイプ");
		convertedColumnNameJPMap.put("挿入内容特定ID（パネルID、具象項目ID）", "挿入内容特定ID");
		convertedColumnNameJPMap.put("採番種別区分（01：入荷伝票、02：出荷伝票、）", "採番種別区分");
		convertedColumnNameJPMap.put("数量単位区分　（個、枚・・・）", "数量単位区分");
		convertedColumnNameJPMap.put("文字列編集区分（LPAD、RPAD,、Rtrim、Ltrim）", "文字列編集区分");
		convertedColumnNameJPMap.put("日付属性区分（営業、非営業）", "日付属性区分");
		convertedColumnNameJPMap.put("日付種類区分（稼働日区分…）", "日付種類区分");
		convertedColumnNameJPMap.put("日数（月末、運用日、休業日）", "日数");
		convertedColumnNameJPMap.put("日種別（登録、絶対、相対、運用、休業）", "日種別");
		convertedColumnNameJPMap.put("明細番号設定内容区分　（発注伝票明細番号、納品伝票明細番号）", "明細番号設定内容区分");
		convertedColumnNameJPMap.put("更新必須区分（任意、必須）", "更新必須区分");
		convertedColumnNameJPMap.put("棚卸ロケーション集約区分（フロア、エリア、列、連、段、レーン）", "棚卸ロケーション集約区分");
		convertedColumnNameJPMap.put("棚卸作業実施有無区分（する、しない）", "棚卸作業実施有無区分");
		convertedColumnNameJPMap.put("棚卸作業対象区分（全件、明細単位）", "棚卸作業対象区分");
		convertedColumnNameJPMap.put("棚卸区分　　（一斉、循環、臨時）", "棚卸区分");
		convertedColumnNameJPMap.put("棚卸実施状況区分（実施中、実施済）", "棚卸実施状況区分");
		convertedColumnNameJPMap.put("棚卸対象区分（商品、ロケーション）", "棚卸対象区分");
		convertedColumnNameJPMap.put("棚卸対象区分（商品別、棚別）", "棚卸対象区分");
		convertedColumnNameJPMap.put("棚卸帳票区分（差異調査リスト、作業指示リスト）", "棚卸帳票区分");
		convertedColumnNameJPMap.put("棚卸状況区分　　（未対応、棚卸中、棚卸済、確定）", "棚卸状況区分");
		convertedColumnNameJPMap.put("業務区分（入荷、出荷）", "業務区分");
		convertedColumnNameJPMap.put("権限ID（Alternate Key)", "権限ID");
		convertedColumnNameJPMap.put("権限グループID（Alternate Key)", "権限グループID");
		convertedColumnNameJPMap.put("権限サマリグループID（Alternate Key)", "権限サマリグループID");
		convertedColumnNameJPMap.put("権限種別（システム、業務、ダウンロード）", "権限種別");
		convertedColumnNameJPMap.put("権限種別（システム、業務）", "権限種別");
		convertedColumnNameJPMap.put("権限設定単位区分（システム、企業、荷主倉庫）", "権限設定単位区分");
		convertedColumnNameJPMap.put("機能ID（処理ID、プロセスID）", "機能ID");
		convertedColumnNameJPMap.put("機能分類ID（処理定義分類ID、プロセス分類ID）", "機能分類ID");
		convertedColumnNameJPMap.put("比較値（バリデーションタイプが属性の場合に設定される）", "比較値");
		convertedColumnNameJPMap.put("汎用共通BLID　・・・レイアウト変換用の汎用共通BL", "汎用共通BLID");
		convertedColumnNameJPMap.put("汎用出力区分(汎用、個別)", "汎用出力区分");
		convertedColumnNameJPMap.put("汎用項目1〜汎用項目5", "汎用項目1〜5");
		convertedColumnNameJPMap.put("画面種別（業務、システム）", "画面種別");
		convertedColumnNameJPMap.put("移動作業区分　（棚出、棚入、棚出取消）", "移動作業区分");
		convertedColumnNameJPMap.put("移動状況区分　（未対応、移動中、格納中、移動済み、移動取消）", "移動状況区分");
		convertedColumnNameJPMap.put("移動状況区分　（未対応、移動中、棚入中、移動済み、移動取消）", "移動状況区分");
		convertedColumnNameJPMap.put("納品書金額項目区分　　（仕入金額、売上金額…）", "納品書金額項目区分");
		convertedColumnNameJPMap.put("終了遅延監視（無、絶対時刻、相対時刻）", "終了遅延監視");
		convertedColumnNameJPMap.put("編集文字種別区分（パラメータ、採番値、固定値）", "編集文字種別区分");
		convertedColumnNameJPMap.put("要求元区分（ジョブ、画面）", "要求元区分");
		convertedColumnNameJPMap.put("要求状態（未指示、要求待機、要求処理済、要求完了）", "要求状態");
		convertedColumnNameJPMap.put("要素ID（Alternate Key)", "要素ID");
		convertedColumnNameJPMap.put("設定分類区分（その他設定、プロセス分類単位設定、JOB単位設定）", "設定分類区分");
		convertedColumnNameJPMap.put("設定分類区分（画面単位設定、プロセス分類単位設定、ジョブ）", "設定分類区分");
		convertedColumnNameJPMap.put("設定対象項目区分　・・・入荷管理項目等", "設定対象項目区分");
		convertedColumnNameJPMap.put("設定箇所制限区分（11:入荷予定、12:入荷実績計上前、13:入荷実績計上後入庫前、14:入庫後、21:出荷予定、22:出荷引当前、23:出荷引当、24:出荷引当後実績計上前、25:出荷実績計上後）", "設定箇所制限区分");
		convertedColumnNameJPMap.put("設定箇所区分（処理、プロセス）", "設定箇所区分");
		convertedColumnNameJPMap.put("説明文 (帳票、IMP、EXPの説明文)", "説明文");
		convertedColumnNameJPMap.put("論理項目ID（Alternate Key)", "論理項目ID");
		convertedColumnNameJPMap.put("通番（Alternate Key)", "通番");
		convertedColumnNameJPMap.put("通番（一意）", "通番");
		convertedColumnNameJPMap.put("連携区分（指定なし、即時連携、即時連携(ダミー)）", "連携区分");
		convertedColumnNameJPMap.put("選択肢ID 　(区分ID、処理分類ID、共通処理分類ID)", "選択肢ID");
		convertedColumnNameJPMap.put("選択肢ID 　(区分ID、項目ID)", "選択肢ID");
		convertedColumnNameJPMap.put("選択肢分類区分　（区分、処理分類、共通処理分類）", "選択肢分類区分");
		convertedColumnNameJPMap.put("選択肢分類区分　（区分、項目）", "選択肢分類区分");
		convertedColumnNameJPMap.put("都道府県コード（NULLABLE）", "都道府県コード");
		convertedColumnNameJPMap.put("開始日指定（日付、月末、曜日）", "開始日指定");
		convertedColumnNameJPMap.put("開始遅延監視（無、絶対時刻、相対時刻）", "開始遅延監視");
		convertedColumnNameJPMap.put("除外設定区分（商品ID、ロケーションコード）", "除外設定区分");
		convertedColumnNameJPMap.put("集信処理区分　（IF集信、共通インポート）", "集信処理区分");
		convertedColumnNameJPMap.put("集配信状況区分（未実行、実行中、正常終了、取込失敗、除外）", "集配信状況区分");
		convertedColumnNameJPMap.put("集配信種別　（集信、配信）", "集配信種別");
		convertedColumnNameJPMap.put("項目テンプレートID（Alternate Key)", "項目テンプレートID");
		convertedColumnNameJPMap.put("項目名称種別（画面、帳票、IF）", "項目名称種別");
		convertedColumnNameJPMap.put("項目構成シーケンスNO（Alternate Key)", "項目構成シーケンスNO");
		convertedColumnNameJPMap.put("項目種別（通常項目、一覧項目等）", "項目種別");
		convertedColumnNameJPMap.put("項目種類区分(文字,数値,日付,プルダウン)", "項目種類区分");
		convertedColumnNameJPMap.put("（実行無、前実行、後実行、振替無）", "");
	}

}
