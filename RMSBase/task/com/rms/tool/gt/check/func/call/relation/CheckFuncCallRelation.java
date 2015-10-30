package com.rms.tool.gt.check.func.call.relation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.constant.Encodes;
import com.rms.base.io.FileWriter;
import com.rms.base.io.IOFactory;
import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.util.TextUtil;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.poi.excel.model.CellModel;
import com.rms.common.poi.excel.model.FontModel;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.tool.gt.generateclass.RowInfo;

public class CheckFuncCallRelation {

	private JDBCObject jdbcObject;

	private JDBCConnection jdbcConnection;

	private List<RowInfo> rowInfos;

	private ExcelOperator outExcelOperator;

	public static void main(String[] args) {

		CheckFuncCallRelation callRelation = new CheckFuncCallRelation();
		try {
			callRelation.initialize();
			callRelation.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			callRelation.release();
		}
	}

	public CheckFuncCallRelation() {

	}

	private void validate() throws SQLException, IOException {

		// for (int i = 0; i < rowInfos.size(); i++) {
		//
		// if (i / 183 != 1 || i >= rowInfos.size()) {
		// continue;
		// }
		// RowInfo rowInfo = rowInfos.get(i);
		// List<FuncInfoBean> calledInfoList =
		// executeSelectCallFuncInfo(rowInfo);
		// if (calledInfoList.size() > 30) {
		// continue;
		// }
		//
		// System.out.println(rowInfo.getVbProcessName() + " " +
		// calledInfoList.size());
		// for (FuncInfoBean funcInfoBean : calledInfoList) {
		// List<FuncInfoBean> callInfoList =
		// executeSelectFuncInfo(funcInfoBean);
		// writeToExcel(callInfoList);
		// }
		// }
		FileWriter writer = IOFactory.newFileWriter("D:\\tmp\\共通関数呼出情報一覧.tsv", Encodes.CHARSET_UTF8);

		StringBuilder sb = new StringBuilder();
		for (RowInfo rowInfo : rowInfos) {
			List<FuncInfoBean> calledInfoList = executeSelectFuncInfo(rowInfo);
			System.out.println(rowInfo.getVbProcessName() + " " + calledInfoList.size());
			writer.writeLine(rowInfo.getVbProcessName() + " " + calledInfoList.size());
			for (FuncInfoBean funcInfoBean : calledInfoList) {
				sb.append(funcInfoBean.getUSER_ID());
				sb.append("\t");
				sb.append(funcInfoBean.getPROJECT_FILE_PATH());
				sb.append("\t");
				sb.append(funcInfoBean.getSOURCE_FILE_PATH());
				sb.append("\t");
				sb.append(funcInfoBean.getFUNC_NAME());
				sb.append("\t");
				sb.append(funcInfoBean.getCALL_SOURCE_FILE_PATH());
				sb.append("\t");
				sb.append(funcInfoBean.getCALL_FUNC_NAME());
				writer.writeLine(sb.toString());
				sb.setLength(0);
			}
			// writeToExcel(calledInfoList);
		}

		writer.close();
		// writeExcel();

	}

	private void initialize() throws SQLException, IOException {

		rowInfos = loadExcel();
		dbInitialize();
	}

	private void release() {

		jdbcConnection.close();
	}

	private List<FuncInfoBean> executeSelectFuncInfo(RowInfo rowInfo) throws SQLException {

		JDBCSelector selector = null;
		JDBCQueryExecutor resultSet = null;
		try {

			selector = jdbcObject.createJDBCSelector(jdbcConnection);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("SELECT");
			sBuilder.append("        PF1.USER_ID");
			sBuilder.append("        ,PF1.PROJECT_FILE_PATH");
			sBuilder.append("        ,PF1.SOURCE_FILE_PATH");
			sBuilder.append("        ,FC1.FUNC_NAME");
			sBuilder.append("        ,FC2.FILE_PATH");
			sBuilder.append("        ,FC1.CALL_FUNC_NAME");
			sBuilder.append("    FROM");
			sBuilder.append("        (");
			sBuilder.append("            SELECT");
			sBuilder.append("                    DISTINCT P_VB_PROJECT_FILE.USER_ID");
			sBuilder.append("                    ,P_VB_PROJECT_FILE.PROJECT_FILE_PATH");
			sBuilder.append("                    ,P_VB_PROJECT_FILE.SOURCE_FILE_PATH");
			sBuilder.append("                FROM");
			sBuilder.append("                    P_VB_PROJECT_FILE");
			sBuilder.append("                WHERE");
			sBuilder.append("                    P_VB_PROJECT_FILE.USER_ID = ?");
			sBuilder.append("                ORDER BY");
			sBuilder.append("                    USER_ID");
			sBuilder.append("                    ,PROJECT_FILE_PATH");
			sBuilder.append("                    ,SOURCE_FILE_PATH");
			sBuilder.append("        ) PF1");
			sBuilder.append("        ,(");
			sBuilder.append("            SELECT");
			sBuilder.append("                    DISTINCT P_VB_PROJECT_FILE.USER_ID");
			sBuilder.append("                    ,P_VB_PROJECT_FILE.PROJECT_FILE_PATH");
			sBuilder.append("                    ,P_VB_PROJECT_FILE.SOURCE_FILE_PATH");
			sBuilder.append("                    ,UPPER(P_VB_PROJECT_FILE.SOURCE_FILE_PATH) SOURCE_FILE_PATH_UPPER");
			sBuilder.append("                FROM");
			sBuilder.append("                    P_VB_PROJECT_FILE");
			sBuilder.append("                WHERE");
			sBuilder.append("                    P_VB_PROJECT_FILE.USER_ID = ?");
			sBuilder.append("                    AND UPPER(P_VB_PROJECT_FILE.SOURCE_FILE_PATH) = ?");
			sBuilder.append("        ) PF2");
			sBuilder.append("        ,(");
			sBuilder.append("            SELECT");
			sBuilder.append("                    DISTINCT USER_ID");
			sBuilder.append("                    ,FILE_PATH");
			sBuilder.append("                    ,FUNC_NAME");
			sBuilder.append("                    ,CALL_FUNC_NAME");
			sBuilder.append("                FROM");
			sBuilder.append("                    P_VB_FUNC_CALL");
			sBuilder.append("                WHERE");
			sBuilder.append("                    USER_ID = ?");
			sBuilder.append("                    AND (");
			sBuilder.append("                        CALL_FUNC_NAME = ?");
			sBuilder.append("                        OR CALL_FUNC_NAME LIKE ?");
			sBuilder.append("                    )");
			sBuilder.append("        ) FC1");
			sBuilder.append("        ,(");
			sBuilder.append("            SELECT");
			sBuilder.append("                    DISTINCT USER_ID");
			sBuilder.append("                    ,FILE_PATH");
			sBuilder.append("                    ,FUNC_NAME");
			sBuilder.append("                FROM");
			sBuilder.append("                    P_VB_FUNC_CALL");
			sBuilder.append("                WHERE");
			sBuilder.append("                    USER_ID = ?");
			// sBuilder.append(" AND FUNC_NAME = ?");
			sBuilder.append("        ) FC2");
			sBuilder.append("    WHERE");
			sBuilder.append("        PF1.PROJECT_FILE_PATH = PF2.PROJECT_FILE_PATH");
			sBuilder.append("        AND UPPER(PF1.SOURCE_FILE_PATH) = UPPER(FC1.FILE_PATH)");
			sBuilder.append("        AND UPPER(PF2.SOURCE_FILE_PATH) = UPPER(FC2.FILE_PATH)");

			selector.setQueryTimeout(999);
			selector.setSqlClause(sBuilder.toString());
			selector.addParameters(rowInfo.getUserId(), rowInfo.getUserId(), rowInfo.getFilePath().toUpperCase(), rowInfo.getUserId(), rowInfo.getVbProcessName(), "%." + rowInfo.getVbProcessName(),
					rowInfo.getUserId());
			resultSet = selector.executeQuery();
			List<FuncInfoBean> resultList = new ArrayList<FuncInfoBean>();
			while (resultSet.hasNext()) {
				FuncInfoBean funcInfoBean = new FuncInfoBean();
				resultList.add(funcInfoBean);
				funcInfoBean.setUSER_ID(resultSet.getValue("USER_ID", String.class));
				funcInfoBean.setPROJECT_FILE_PATH(resultSet.getValue("PROJECT_FILE_PATH", String.class));
				funcInfoBean.setSOURCE_FILE_PATH(resultSet.getValue("SOURCE_FILE_PATH", String.class));
				funcInfoBean.setFUNC_NAME(resultSet.getValue("FUNC_NAME", String.class));
				funcInfoBean.setCALL_SOURCE_FILE_PATH(resultSet.getValue("FILE_PATH", String.class));
				funcInfoBean.setCALL_FUNC_NAME(resultSet.getValue("CALL_FUNC_NAME", String.class));
			}
			return resultList;
		} finally {
			if (selector != null)
				selector.close();
			if (resultSet != null)
				resultSet.close();
		}

	}

	private List<FuncInfoBean> executeSelectFuncInfo(FuncInfoBean infuncInfoBean) throws SQLException {

		JDBCSelector selector = null;
		JDBCQueryExecutor resultSet = null;
		try {

			selector = jdbcObject.createJDBCSelector(jdbcConnection);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("SELECT");
			sBuilder.append(" DISTINCT");
			sBuilder.append(" P_VB_PROJECT_FILE.USER_ID USER_ID");
			sBuilder.append(" ,P_VB_PROJECT_FILE.PROJECT_FILE_PATH PROJECT_FILE_PATH");
			sBuilder.append(" ,P_VB_PROJECT_FILE.SOURCE_FILE_PATH SOURCE_FILE_PATH");
			sBuilder.append(" ,P_VB_FUNC_CALL.FUNC_NAME FUNC_NAME");
			sBuilder.append(" ,P_VB_FUNC_CALL.CALL_FUNC_NAME CALL_FUNC_NAME");
			sBuilder.append(" FROM");
			sBuilder.append(" P_VB_PROJECT_FILE");
			sBuilder.append(" ,P_VB_FUNC_CALL");
			sBuilder.append(" WHERE");
			sBuilder.append(" P_VB_PROJECT_FILE.USER_ID = P_VB_FUNC_CALL.USER_ID");
			sBuilder.append(" AND UPPER(P_VB_PROJECT_FILE.SOURCE_FILE_PATH) = UPPER(P_VB_FUNC_CALL.FILE_PATH)");
			sBuilder.append(" AND P_VB_PROJECT_FILE.USER_ID = ?");
			sBuilder.append(" AND UPPER(P_VB_PROJECT_FILE.PROJECT_FILE_PATH) = UPPER(?)");
			sBuilder.append(" AND UPPER(P_VB_FUNC_CALL.CALL_FUNC_NAME) = UPPER(?)");
			sBuilder.append(" ORDER BY");
			sBuilder.append(" USER_ID");
			sBuilder.append(" ,UPPER(PROJECT_FILE_PATH)");
			sBuilder.append(" ,UPPER(SOURCE_FILE_PATH)");
			sBuilder.append(" ,FUNC_NAME");
			sBuilder.append(" ,CALL_FUNC_NAME");
			selector.setSqlClause(sBuilder.toString());
			selector.addParameters(infuncInfoBean.getUSER_ID(), infuncInfoBean.getPROJECT_FILE_PATH(), infuncInfoBean.getCALL_FUNC_NAME());
			resultSet = selector.executeQuery();
			List<FuncInfoBean> resultList = new ArrayList<FuncInfoBean>();
			while (resultSet.hasNext()) {
				FuncInfoBean funcInfoBean = new FuncInfoBean();
				resultList.add(funcInfoBean);
				funcInfoBean.setUSER_ID(resultSet.getValue("USER_ID", String.class));
				funcInfoBean.setPROJECT_FILE_PATH(resultSet.getValue("PROJECT_FILE_PATH", String.class));
				funcInfoBean.setSOURCE_FILE_PATH(resultSet.getValue("SOURCE_FILE_PATH", String.class));
				funcInfoBean.setFUNC_NAME(resultSet.getValue("FUNC_NAME", String.class));
				funcInfoBean.setCALL_FUNC_NAME(resultSet.getValue("CALL_FUNC_NAME", String.class));
				funcInfoBean.setCALL_SOURCE_FILE_PATH(infuncInfoBean.getCALL_SOURCE_FILE_PATH());
			}
			return resultList;
		} finally {
			if (selector != null)
				selector.close();
			if (resultSet != null)
				resultSet.close();
		}

	}

	private List<FuncInfoBean> executeSelectCallFuncInfo(RowInfo rowInfo) throws SQLException {

		JDBCSelector selector = null;
		JDBCQueryExecutor resultSet = null;
		try {
			selector = jdbcObject.createJDBCSelector(jdbcConnection);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" SELECT");
			sBuilder.append(" DISTINCT");
			sBuilder.append(" P_VB_PROJECT_FILE.USER_ID USER_ID");
			sBuilder.append(" ,P_VB_PROJECT_FILE.PROJECT_FILE_PATH PROJECT_FILE_PATH");
			sBuilder.append(" ,P_VB_PROJECT_FILE.SOURCE_FILE_PATH SOURCE_FILE_PATH");
			sBuilder.append(" ,P_VB_FUNC_CALL.FUNC_NAME FUNC_NAME");
			sBuilder.append(" FROM");
			sBuilder.append(" P_VB_PROJECT_FILE");
			sBuilder.append(" ,P_VB_FUNC_CALL");
			sBuilder.append(" WHERE");
			sBuilder.append(" P_VB_PROJECT_FILE.USER_ID = P_VB_FUNC_CALL.USER_ID");
			sBuilder.append(" AND P_VB_PROJECT_FILE.USER_ID = ?");
			sBuilder.append(" AND UPPER(P_VB_PROJECT_FILE.SOURCE_FILE_PATH) = UPPER(P_VB_FUNC_CALL.FILE_PATH)");
			sBuilder.append(" AND UPPER(P_VB_PROJECT_FILE.SOURCE_FILE_PATH) = UPPER(?)");
			sBuilder.append(" AND P_VB_FUNC_CALL.FUNC_NAME = ?");
			sBuilder.append(" ORDER BY");
			sBuilder.append(" USER_ID");
			sBuilder.append(" ,UPPER(PROJECT_FILE_PATH)");
			sBuilder.append(" ,UPPER(SOURCE_FILE_PATH)");
			sBuilder.append(" ,FUNC_NAME");
			selector.setSqlClause(sBuilder.toString());
			selector.addParameters(rowInfo.getUserId(), rowInfo.getFilePath(), rowInfo.getVbProcessName());
			resultSet = selector.executeQuery();
			List<FuncInfoBean> resultList = new ArrayList<FuncInfoBean>();
			while (resultSet.hasNext()) {
				FuncInfoBean funcInfoBean = new FuncInfoBean();
				resultList.add(funcInfoBean);
				funcInfoBean.setUSER_ID(resultSet.getValue("USER_ID", String.class));
				funcInfoBean.setPROJECT_FILE_PATH(resultSet.getValue("PROJECT_FILE_PATH", String.class));
				funcInfoBean.setCALL_SOURCE_FILE_PATH(resultSet.getValue("SOURCE_FILE_PATH", String.class));
				funcInfoBean.setCALL_FUNC_NAME(resultSet.getValue("FUNC_NAME", String.class));
			}
			return resultList;
		} finally {
			if (selector != null)
				selector.close();
			if (resultSet != null)
				resultSet.close();
		}

	}

	private void writeExcel() throws IOException {

		outExcelOperator.write("D:\\tmp\\WB_NX_RUI_C030_共通関数呼出情報一覧.xlsx");
	}

	private void writeToExcel(List<FuncInfoBean> callInfoList) throws IOException {

		if (outExcelOperator == null) {
			outExcelOperator = ExcelOperatorFactory.createExcel2007();
			outExcelOperator.createSheet("共通関数呼出情報一覧");
			// outExcelOperator.removeSheetsExclude("共通関数呼出情報一覧");
			outExcelOperator.setSelectedSheet("共通関数呼出情報一覧");
			SheetOperator sheetOperator = outExcelOperator.getSheetOperator();
			sheetOperator.setCurrentIndex(0, 1);
			CellModel cellModel1 = new CellModel();
			cellModel1.setValue("USER_ID");
			CellModel cellModel2 = new CellModel();
			cellModel2.setValue("PROJECT_FILE_PATH");
			CellModel cellModel3 = new CellModel();
			cellModel3.setValue("SOURCE_FILE_PATH");
			CellModel cellModel4 = new CellModel();
			cellModel4.setValue("FUNC_NAME");
			CellModel cellModel5 = new CellModel();
			cellModel5.setValue("CALL_SOURCE_FILE_PATH");
			CellModel cellModel6 = new CellModel();
			cellModel6.setValue("CALL_FUNC_NAME");
			int lastRowIndex = outExcelOperator.getLastRowIndex();
			int columnIndex = 1;
			cellModel1.setRowIndex(lastRowIndex);
			cellModel2.setRowIndex(lastRowIndex);
			cellModel3.setRowIndex(lastRowIndex);
			cellModel4.setRowIndex(lastRowIndex);
			cellModel5.setRowIndex(lastRowIndex);
			cellModel6.setRowIndex(lastRowIndex);
			cellModel1.setColumnIndex(columnIndex++);
			cellModel2.setColumnIndex(columnIndex++);
			cellModel3.setColumnIndex(columnIndex++);
			cellModel4.setColumnIndex(columnIndex++);
			cellModel5.setColumnIndex(columnIndex++);
			cellModel6.setColumnIndex(columnIndex++);
			FontModel fontModel = new FontModel();
			cellModel1.setFontModel(fontModel);
			cellModel2.setFontModel(fontModel);
			cellModel3.setFontModel(fontModel);
			cellModel4.setFontModel(fontModel);
			cellModel5.setFontModel(fontModel);
			cellModel6.setFontModel(fontModel);

			cellModel1.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel2.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel3.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel4.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel5.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel6.setSheetName(outExcelOperator.getSelectedSheetName());
			outExcelOperator.setCellModels(cellModel1, cellModel2, cellModel3, cellModel4, cellModel5, cellModel6);
		}

		int lastRowIndex = outExcelOperator.getLastRowIndex();
		for (FuncInfoBean funcInfoBean : callInfoList) {
			SheetOperator sheetOperator = outExcelOperator.getSheetOperator();
			sheetOperator.setCurrentIndex(++lastRowIndex, 1);
			int columnIndex = 1;
			CellModel cellModel1 = new CellModel();
			cellModel1.setValue(funcInfoBean.getUSER_ID());
			CellModel cellModel2 = new CellModel();
			cellModel2.setValue(funcInfoBean.getPROJECT_FILE_PATH());
			CellModel cellModel3 = new CellModel();
			cellModel3.setValue(funcInfoBean.getSOURCE_FILE_PATH());
			CellModel cellModel4 = new CellModel();
			cellModel4.setValue(funcInfoBean.getFUNC_NAME());
			CellModel cellModel5 = new CellModel();
			cellModel5.setValue(funcInfoBean.getCALL_SOURCE_FILE_PATH());
			CellModel cellModel6 = new CellModel();
			cellModel6.setValue(funcInfoBean.getCALL_FUNC_NAME());

			cellModel1.setRowIndex(lastRowIndex);
			cellModel2.setRowIndex(lastRowIndex);
			cellModel3.setRowIndex(lastRowIndex);
			cellModel4.setRowIndex(lastRowIndex);
			cellModel5.setRowIndex(lastRowIndex);
			cellModel6.setRowIndex(lastRowIndex);
			cellModel1.setColumnIndex(columnIndex++);
			cellModel2.setColumnIndex(columnIndex++);
			cellModel3.setColumnIndex(columnIndex++);
			cellModel4.setColumnIndex(columnIndex++);
			cellModel5.setColumnIndex(columnIndex++);
			cellModel6.setColumnIndex(columnIndex++);

			FontModel fontModel = new FontModel();
			cellModel1.setFontModel(fontModel);
			cellModel2.setFontModel(fontModel);
			cellModel3.setFontModel(fontModel);
			cellModel4.setFontModel(fontModel);
			cellModel5.setFontModel(fontModel);
			cellModel6.setFontModel(fontModel);

			cellModel1.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel2.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel3.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel4.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel5.setSheetName(outExcelOperator.getSelectedSheetName());
			cellModel6.setSheetName(outExcelOperator.getSelectedSheetName());
			outExcelOperator.setCellModels(cellModel1, cellModel2, cellModel3, cellModel4, cellModel5, cellModel6);
		}

	}

	private List<RowInfo> loadExcel() throws IOException {

		String excelPath = "D:\\tmp\\WB_NX_RUI_C030_共通機能一覧.xlsx";

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

			CellModel vbProcessName = excelOperator.getCellValue(rowIndex, 5);

			CellModel logicName = excelOperator.getCellValue(rowIndex, 7);

			CellModel packageName = excelOperator.getCellValue(rowIndex, 8);

			CellModel className = excelOperator.getCellValue(rowIndex, 9);

			CellModel methodName = excelOperator.getCellValue(rowIndex, 3);

			CellModel returnType = excelOperator.getCellValue(rowIndex, 63);

			CellModel parameterCount = excelOperator.getCellValue(rowIndex, 64);

			CellModel methodParameterList = excelOperator.getCellValue(rowIndex, 65);

			CellModel processDetail = excelOperator.getCellValue(rowIndex, 13);

			CellModel targetType = excelOperator.getCellValue(rowIndex, 17);
			CellModel targetTypeOld = excelOperator.getCellValue(rowIndex, 19);

			CellModel userId = excelOperator.getCellValue(rowIndex, 39);

			CellModel filePath = excelOperator.getCellValue(rowIndex, 40);

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

			if (userId != null) {
				rowInfo.setUserId(userId.getValue());
			}
			if (filePath != null) {
				rowInfo.setFilePath(filePath.getValue());
			}
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

	private void dbInitialize() throws SQLException {

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
}
