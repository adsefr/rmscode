package com.rms.common.poi.excel.enums;

import org.apache.poi.ss.usermodel.Cell;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum CellType {

	/**
	 * Numeric Cell type (0)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_NUMERIC(Cell.CELL_TYPE_NUMERIC),

	/**
	 * String Cell type (1)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_STRING(Cell.CELL_TYPE_STRING),

	/**
	 * Formula Cell type (2)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_FORMULA(Cell.CELL_TYPE_FORMULA),

	/**
	 * Blank Cell type (3)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_BLANK(Cell.CELL_TYPE_BLANK),

	/**
	 * Boolean Cell type (4)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_BOOLEAN(Cell.CELL_TYPE_BOOLEAN),

	/**
	 * Error Cell type (5)
	 * 
	 * @see #setCellType(int)
	 * @see #getCellType()
	 */
	CELL_TYPE_ERROR(Cell.CELL_TYPE_ERROR);

	private int type;

	private CellType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public int getType() {

		return type;
	}

	public static CellType valueOf(int type) {

		for (CellType cellType : CellType.values()) {
			if (cellType.getType() == type) {
				return cellType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
