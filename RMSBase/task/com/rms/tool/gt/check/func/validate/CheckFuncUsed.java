package com.rms.tool.gt.check.func.validate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.util.TextUtil;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.poi.excel.model.CellModel;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.tool.gt.generateclass.RowInfo;

public class CheckFuncUsed {

	JDBCObject jdbcObject;

	JDBCConnection jdbcConnection;

	List<RowInfo> rowInfos;

	public static void main(String[] args) {

		CheckFuncUsed checkFuncUsed = new CheckFuncUsed();
		try {
			checkFuncUsed.initialize();
			checkFuncUsed.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			checkFuncUsed.release();
		}
	}

	public CheckFuncUsed() {

	}

	private void validate() throws SQLException, IOException {

		System.out.println(rowInfos.size());
		for (RowInfo rowInfo : rowInfos) {
			String funcName = rowInfo.getVbProcessName();
			executeSelectCALL_FUNC_NAME(funcName);
		}
	}

	private void initialize() throws SQLException, IOException {

		String excelPath = "D:\\tmp\\WB_NX_RUI_C030_共通機能一覧.xlsx";
		rowInfos = loadExcel(excelPath);

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("192.168.0.191");
		dataBase.setPort(1521);
		dataBase.setDataBaseType(DataBaseType.ORACLE);
		dataBase.setDataBaseName("deriva");
		dataBase.setUserId("foptrade");
		dataBase.setPassword("trade");

		jdbcObject = JDBCObject.getInstance(dataBase);
		jdbcConnection = jdbcObject.getJDBCConnection();
		jdbcConnection.connection();
	}

	private void release() {

		jdbcConnection.close();
	}

	private void executeSelectCALL_FUNC_NAME(String funcName) throws SQLException {

		JDBCSelector selector = null;
		JDBCQueryExecutor resultSet = null;
		try {

			selector = jdbcObject.createJDBCSelector(jdbcConnection);
			String selectClasue = "select  CALL_FUNC_NAME from P_VB_FUNC_CALL where CALL_FUNC_NAME like ? or CALL_FUNC_NAME = ?";
			selector.setSqlClause(selectClasue);
			selector.addParameters("%." + funcName, funcName);
			resultSet = selector.executeQuery();
			if (resultSet.hasNext()) {
				System.out.println(funcName);
			}
		} finally {
			if (selector != null)
				selector.close();
			if (resultSet != null)
				resultSet.close();
		}
	}

	private JDBCQueryExecutor executeSelectP_VB_FUNC() throws SQLException {

		JDBCSelector selector = jdbcObject.createJDBCSelector(jdbcConnection);
		String selectClasue = "SELECT * FROM P_VB_FUNC";
		selector.setSqlClause(selectClasue);
		JDBCQueryExecutor resultSet = selector.executeQuery();

		return resultSet;
	}

	private JDBCQueryExecutor executeSelectP_VB_FUNC_CALL() throws SQLException {

		JDBCSelector selector = jdbcObject.createJDBCSelector(jdbcConnection);
		String selectClasue = "SELECT * FROM P_VB_FUNC_CALL";
		selector.setSqlClause(selectClasue);
		JDBCQueryExecutor resultSet = selector.executeQuery();
		return resultSet;
	}

	private List<RowInfo> loadExcel(String excelPath) throws IOException {

		ExcelOperator excelOperator = ExcelOperatorFactory.load(excelPath);
		String sheetName = "処理一覧";
		boolean hasSheet = excelOperator.setSelectedSheet(sheetName);

		if (!hasSheet) {
			throw new RuntimeException("the sheet is not found. sheetName:" + sheetName);
		}

		SheetOperator sheetOperator = excelOperator.getSheetOperator();

		int startRow = 3;
		int endRow = excelOperator.getLastRowIndex();

		List<RowInfo> rowInfos = new ArrayList<RowInfo>();

		for (int rowIndex = startRow; rowIndex < endRow; rowIndex++) {
			sheetOperator.setCurrRowIndex(rowIndex);

			CellModel seqNo = excelOperator.getCellValue(rowIndex, 2);
			if (seqNo == null) {
				break;
			}
			if (String.valueOf(seqNo.getValue()).equals("追加")) {
				continue;
			}

			CellModel javaProcessName = excelOperator.getCellValue(rowIndex, 3);

			CellModel vbProcessName = excelOperator.getCellValue(rowIndex, 4);

			CellModel logicName = excelOperator.getCellValue(rowIndex, 6);

			CellModel packageName = excelOperator.getCellValue(rowIndex, 7);

			CellModel className = excelOperator.getCellValue(rowIndex, 8);

			CellModel methodName = excelOperator.getCellValue(rowIndex, 3);

			CellModel returnType = excelOperator.getCellValue(rowIndex, 62);

			CellModel parameterCount = excelOperator.getCellValue(rowIndex, 63);

			CellModel methodParameterList = excelOperator.getCellValue(rowIndex, 64);

			CellModel processDetail = excelOperator.getCellValue(rowIndex, 12);

			CellModel targetType = excelOperator.getCellValue(rowIndex, 16);
			CellModel targetTypeOld = excelOperator.getCellValue(rowIndex, 18);

			CellModel userId = excelOperator.getCellValue(rowIndex, 38);

			CellModel filePath = excelOperator.getCellValue(rowIndex, 37);

			// if (rowIndex == 689) {
			// System.out.println(TextUtil.lPad(String.valueOf(((Double)
			// seqNo.getValue()).intValue()), 3));
			// System.out.println(vbProcessName.getValue() + " " +
			// targetType.getValue());
			// }
			if (!targetType.getValue().equals("Java化対象")) {
				continue;
			}
			RowInfo rowInfo = new RowInfo();
			rowInfo.setSeqNo(TextUtil.lPad(String.valueOf(((Double) seqNo.getValue()).intValue()), 3));
			rowInfo.setJavaProcessName(javaProcessName.getValue());
			rowInfo.setVbProcessName(vbProcessName.getValue());
			rowInfo.setLogicName(logicName.getValue());
			rowInfo.setPackageName(packageName.getValue());
			rowInfo.setClassName(className.getValue());
			rowInfo.setMethodName(methodName.getValue());
			rowInfo.setProcessDetail(processDetail.getValue());
			rowInfo.setTargetType(targetType.getValue());
			rowInfo.setTargetTypeOld(targetTypeOld.getValue());

			// if (userId != null) {
			// rowInfo.setUserId(userId.getValue());
			// }
			// if (filePath != null) {
			// rowInfo.setFilePath(filePath.getValue());
			// }
			// if (returnType != null) {
			// rowInfo.setReturnType(returnType.getValue());
			// }
			// if (parameterCount != null) {
			// rowInfo.setParameterCount(String.valueOf(parameterCount.getValue()));
			// }
			// if (methodParameterList != null) {
			// rowInfo.setMethodParameterList(methodParameterList.getValue());
			// }

			rowInfos.add(rowInfo);
		}

		return rowInfos;
	}
}
