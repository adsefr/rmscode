package com.rms.tool.future.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.util.TextUtil;

public class GetDataCount {
	public static void main(String[] args) throws IOException {
		List<File> fileList = getFileList();
		for (File file : fileList) {
			printSheetNames(file);
		}
	}

	private static List<File> getFileList() {
		String targetDir = "C:/document/作業内容/【20151019_データ投入確認】/投入データ";
		return FileHelper.getFileList(targetDir, ".+\\.xlsm", FileType.FILE);
	}

	private static void printSheetNames(File file) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
		int count = xssfWorkbook.getNumberOfSheets();
		for (int index = 0; index < count; index++) {
			Sheet sheet = xssfWorkbook.getSheetAt(index);
			if (sheet.getSheetName().equals("【メモ】祝日確認")) {
				continue;
			}

			int lastRowNumber = sheet.getLastRowNum();
			int dataCount = 0;
			String tableName = sheet.getRow(1).getCell(0).getStringCellValue();

			for (int i = 9; i <= lastRowNumber; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				Cell cell0 = row.getCell(0);
				if (cell0 == null || cell0.getCellType() != Cell.CELL_TYPE_FORMULA) {
					break;
				}

				Cell cell1 = sheet.getRow(i).getCell(1);
				if (cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					dataCount++;
				} else if (cell1 != null && TextUtil.isNotBlank(cell1.getStringCellValue())) {
					dataCount++;
				} else {
					break;
				}
			}

			System.out.println(file.getName() + "-" + sheet.getSheetName() + "-" + tableName + "-" + lastRowNumber + "-" + dataCount);
		}
	}
}
