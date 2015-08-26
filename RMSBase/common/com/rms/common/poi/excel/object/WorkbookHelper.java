package com.rms.common.poi.excel.object;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.base.util.TextUtil;
import com.rms.common.poi.excel.model.CellStyleModel;
import com.rms.common.poi.excel.model.FontModel;

/**
 * Excelを操作する補助クラス
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public final class WorkbookHelper {

	private static Map<Workbook, Map<FontModel, Font>> fontMap = new HashMap<Workbook, Map<FontModel, Font>>();

	private static Map<java.awt.Color, XSSFColor> xssfColorMap = new HashMap<java.awt.Color, XSSFColor>();

	/**
	 * 指定シート名のシートを作成する。<br>
	 * シート作成が失敗した場合、nullを返す。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @return 作成結果
	 */
	public static Sheet createSheet(Workbook workbook, String sheetName) {

		Sheet sheet = null;

		try {
			sheet = workbook.createSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheet;
	}

	/**
	 * 指定位置に指定シート名のシートを作成する。<br>
	 * シート作成が失敗した場合、nullを返す。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @param sheetIndex
	 *            シート位置
	 * @return 作成結果
	 */
	public static Sheet createSheet(Workbook workbook, String sheetName, int sheetIndex) {

		Sheet sheet = createSheet(workbook, sheetName);

		if (sheet != null) {
			workbook.setSheetOrder(sheetName, sheetIndex);
		}

		return sheet;
	}

	/**
	 * 選択されているシートを取得する。<br>
	 *
	 * @param workbook
	 * @return 取得結果
	 */
	public static Sheet getSelectedSheet(Workbook workbook) {

		int sheetIndex = workbook.getActiveSheetIndex();

		Sheet sheet = getSheet(workbook, sheetIndex);

		return sheet;
	}

	/**
	 * 選択されているシート名を取得する。
	 *
	 * @param workbook
	 * @return シート名
	 */
	public static String getActiveSheetName(Workbook workbook) {

		int sheetIndex = workbook.getActiveSheetIndex();

		return getSheetName(workbook, sheetIndex);
	}

	/**
	 * 指定位置のシートを取得する。<br>
	 * シート位置が無効の場合、nullを返す。<br>
	 *
	 * @param workbook
	 * @param sheetIndex
	 *            シート位置
	 * @return 取得結果
	 */
	public static Sheet getSheet(Workbook workbook, int sheetIndex) {

		Sheet sheet = null;

		try {
			sheet = workbook.getSheetAt(sheetIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheet;

	}

	/**
	 * 指定名のシートを取得する。<br>
	 * シート名が存在しない場合、nullを返す。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @return 取得結果
	 */
	public static Sheet getSheet(Workbook workbook, String sheetName) {

		Sheet sheet = null;

		try {
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheet;
	}

	/**
	 * 指定シート名のシート位置を取得する。<br>
	 * シート名が存在しない場合、-1を返す。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @return シート位置
	 */
	public static int getSheetIndex(Workbook workbook, String sheetName) {

		int sheetIndex = -1;

		try {
			sheetIndex = workbook.getSheetIndex(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheetIndex;
	}

	/**
	 * 指定位置のシート名を取得する。<br>
	 * シート位置が無効の場合、nullを返す。<br>
	 *
	 * @param workbook
	 * @param sheetIndex
	 *            シート位置
	 * @return シート名
	 */
	static String getSheetName(Workbook workbook, int sheetIndex) {

		String sheetName = null;

		try {
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			sheetName = sheet.getSheetName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheetName;
	}

	/**
	 * シート名の有効性をチェックする。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return true：有効 false：無効
	 */
	public static boolean isValidSheetName(String sheetName) {

		try {
			WorkbookUtil.validateSheetName(sheetName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 指定位置のシートを選択されるシートに設定する。<br>
	 *
	 * @param workbook
	 * @param sheetIndex
	 *            シート位置
	 * @return true：成功の場合、 false：失敗の場合
	 */
	static boolean setSelectedSheet(Workbook workbook, int sheetIndex) {

		try {
			workbook.setActiveSheet(sheetIndex);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 指定シート名のシートを選択されるシートに設定する。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @return true：成功の場合、 false：失敗の場合
	 */
	public static boolean setSelectedSheet(Workbook workbook, String sheetName) {

		int sheetIndex = getSheetIndex(workbook, sheetName);

		return setSelectedSheet(workbook, sheetIndex);
	}

	/**
	 * 選択されているシートを削除する。
	 *
	 * @param workbook
	 * @return true：成功の場合、 false：失敗の場合
	 */
	public static boolean removeSelectedSheet(Workbook workbook) {

		int sheetIndex = workbook.getActiveSheetIndex();

		return removeSheet(workbook, sheetIndex);
	}

	/**
	 * 指定位置のシートを削除する。<br>
	 *
	 * @param workbook
	 * @param sheetIndex
	 *            シート位置
	 * @return true：成功の場合、 false：失敗の場合
	 */
	static boolean removeSheet(Workbook workbook, int sheetIndex) {

		try {
			workbook.removeSheetAt(sheetIndex);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 指定シート名のシートを削除する。<br>
	 *
	 * @param workbook
	 * @param sheetName
	 *            シート名
	 * @return true：成功の場合、 false：失敗の場合
	 */
	public static boolean removeSheet(Workbook workbook, String sheetName) {

		int sheetIndex = getSheetIndex(workbook, sheetName);

		return removeSheet(workbook, sheetIndex);
	}

	public static CellStyle createCellStyle(Workbook workbook, CellStyleModel cellStyleModel) {// TODO

		XSSFWorkbook xssfWorkbook = null;
		if (workbook instanceof XSSFWorkbook) {
			xssfWorkbook = (XSSFWorkbook) workbook;
		}

		if (xssfWorkbook == null) {
			return null;
		}

		XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();

		if (cellStyleModel != null) {
			xssfCellStyle.setAlignment(cellStyleModel.getAlignHorizontal().getType());
			xssfCellStyle.setVerticalAlignment(cellStyleModel.getAlignVertical().getType());

			if (cellStyleModel.getForegroundColor() != null) {
				xssfCellStyle.setFillPattern(cellStyleModel.getFillPatternType().getType());
				xssfCellStyle.setFillForegroundColor(getXSSFColor(cellStyleModel.getForegroundColor()));
			}
			if (cellStyleModel.getTopBorderColor() != null) {
				xssfCellStyle.setTopBorderColor(getXSSFColor(cellStyleModel.getTopBorderColor()));
			}
			if (cellStyleModel.getBottomBorderColor() != null) {
				xssfCellStyle.setBottomBorderColor(getXSSFColor(cellStyleModel.getBottomBorderColor()));
			}

			if (cellStyleModel.getLeftBorderColor() != null) {
				xssfCellStyle.setLeftBorderColor(getXSSFColor(cellStyleModel.getLeftBorderColor()));
			}
			if (cellStyleModel.getRightBorderColor() != null) {
				xssfCellStyle.setRightBorderColor(getXSSFColor(cellStyleModel.getRightBorderColor()));
			}

			if (cellStyleModel.getTopBorderType() != null) {
				xssfCellStyle.setBorderTop(cellStyleModel.getTopBorderType().getType());
			}
			if (cellStyleModel.getBottomBorderType() != null) {
				xssfCellStyle.setBorderBottom(cellStyleModel.getBottomBorderType().getType());
			}
			if (cellStyleModel.getLeftBorderType() != null) {
				xssfCellStyle.setBorderLeft(cellStyleModel.getLeftBorderType().getType());
			}
			if (cellStyleModel.getRightBorderType() != null) {
				xssfCellStyle.setBorderRight(cellStyleModel.getRightBorderType().getType());
			}

			xssfCellStyle.setWrapText(cellStyleModel.isWrapText());

			if (cellStyleModel.getDataFormat() != null) {
				xssfCellStyle.setDataFormat(workbook.createDataFormat().getFormat(cellStyleModel.getDataFormat()));
			}
		}

		return xssfCellStyle;

	}

	public static XSSFFont getFont(XSSFWorkbook xssfWorkbook, FontModel fontModel) {// TODO

		Map<FontModel, Font> map = fontMap.get(xssfWorkbook);
		if (map == null) {
			map = new HashMap<FontModel, Font>();
			fontMap.put(xssfWorkbook, map);
		}

		XSSFFont xssfFont = (XSSFFont) map.get(fontModel);
		if (xssfFont == null) {
			xssfFont = xssfWorkbook.createFont();
			map.put(fontModel, xssfFont);
		} else {
			return xssfFont;
		}

		// フォント名
		if (!TextUtil.isBlank(fontModel.getFontName())) {
			// TODO to check that it is valid font name
			xssfFont.setFontName(fontModel.getFontName());
		}

		// フォントのサイズ
		xssfFont.setFontHeight(fontModel.getFontSize());

		// フォントの色
		xssfFont.setColor(WorkbookHelper.getXSSFColor(fontModel.getFontColor()));

		// 太字
		xssfFont.setBoldweight(fontModel.getBoldType().getType());

		// 斜体
		xssfFont.setItalic(fontModel.isItalic());

		// 取り消し線
		xssfFont.setStrikeout(fontModel.isStrikeout());

		// 下線
		xssfFont.setUnderline(fontModel.getUnderlineType().getType());

		return xssfFont;
	}

	public static XSSFColor getXSSFColor(java.awt.Color color) {// TODO

		if (color == null) {
			return null;
		}

		XSSFColor c = xssfColorMap.get(color);
		if (c == null) {
			c = new XSSFColor(color);
		}

		xssfColorMap.put(color, c);

		return c;
	}

}
