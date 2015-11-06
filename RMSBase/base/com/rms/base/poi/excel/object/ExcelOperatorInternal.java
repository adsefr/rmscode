package com.rms.base.poi.excel.object;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.rms.base.poi.excel.model.CellStyleModel;
import com.rms.base.poi.excel.model.FontModel;

/**
 * Excelファイルを操作するインタフェース
 * 
 * @author ri.meisei
 * @since 2013/12/19
 */
interface ExcelOperatorInternal {

	Font getFont(FontModel fontModel);

	CellStyle getCellStyle(CellStyleModel cellStyleModel);

}
