package com.rms.common.poi.excel.object;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.rms.base.util.ArrayUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
abstract class AbstractExcelOperator implements ExcelOperator {

	protected final ExcelObject excelObject;

	private final Workbook workbook;

	public AbstractExcelOperator(ExcelObject excelObject) {

		this.excelObject = excelObject;
		workbook = ((AbstractExcelObject) excelObject).getWorkbook();
	}

	@Override
	public boolean createSheet(String sheetName) {

		Sheet sheet = WorkbookHelper.createSheet(workbook, sheetName);

		return (sheet != null);
	}

	@Override
	public boolean createSheet(String sheetName, int sheetIndex) {

		Sheet sheet = WorkbookHelper.createSheet(workbook, sheetName, sheetIndex);

		return (sheet != null);
	}

	@Override
	public boolean setSelectedSheet(String sheetName) {

		return WorkbookHelper.setSelectedSheet(workbook, sheetName);

	}

	@Override
	public boolean setSelectedSheet(int sheetIndex) {

		return WorkbookHelper.setSelectedSheet(workbook, sheetIndex);
	}

	@Override
	public boolean removeSheet(String sheetName) {

		return WorkbookHelper.removeSheet(workbook, sheetName);
	}

	@Override
	public boolean removeSheet(int sheetIndex) {

		return WorkbookHelper.removeSheet(workbook, sheetIndex);
	}

	@Override
	public boolean removeSheetsExclude(String... sheetNames) {

		if (ArrayUtil.isEmpty(sheetNames)) {
			return false;
		}

		boolean isExist = false;
		for (String sheetName : sheetNames) {
			if (excelObject.hasSheet(sheetName)) {
				isExist = true;
				break;
			}
		}

		if (isExist) {
			List<String> sheetNameList = Arrays.asList(sheetNames);
			int number = workbook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < number; sheetIndex++) {
				String sheetName = WorkbookHelper.getSheetName(workbook, sheetIndex);
				if (!sheetNameList.contains(sheetName)) {
					WorkbookHelper.removeSheet(workbook, sheetName);
				}
			}
		}

		return isExist;
	}

	@Override
	public boolean removeSheetsExclude(int... sheetIndexs) {

		if (ArrayUtil.isEmpty(sheetIndexs)) {
			return false;
		}

		String[] sheetNames = new String[sheetIndexs.length];
		for (int i = 0; i < sheetIndexs.length; i++) {
			sheetNames[i] = excelObject.getSheetName(sheetIndexs[i]);
		}

		return removeSheetsExclude(sheetNames);
	}

}
