package com.rms.common.poi.excel.enums;

import org.apache.poi.ss.usermodel.CellStyle;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum FillPatternType {

	/** No background */
	NO_FILL(CellStyle.NO_FILL),

	/** Solidly filled */
	SOLID_FOREGROUND(CellStyle.SOLID_FOREGROUND),

	/** Small fine dots */
	FINE_DOTS(CellStyle.FINE_DOTS),

	/** Wide dots */
	ALT_BARS(CellStyle.ALT_BARS),

	/** Sparse dots */
	SPARSE_DOTS(CellStyle.SPARSE_DOTS),

	/** Thick horizontal bands */
	THICK_HORZ_BANDS(CellStyle.THICK_HORZ_BANDS),

	/** Thick vertical bands */
	THICK_VERT_BANDS(CellStyle.THICK_VERT_BANDS),

	/** Thick backward facing diagonals */
	THICK_BACKWARD_DIAG(CellStyle.THICK_BACKWARD_DIAG),

	/** Thick forward facing diagonals */
	THICK_FORWARD_DIAG(CellStyle.THICK_FORWARD_DIAG),

	/** Large spots */
	BIG_SPOTS(CellStyle.BIG_SPOTS),

	/** Brick-like layout */
	BRICKS(CellStyle.BRICKS),

	/** Thin horizontal bands */
	THIN_HORZ_BANDS(CellStyle.THIN_HORZ_BANDS),

	/** Thin vertical bands */
	THIN_VERT_BANDS(CellStyle.THIN_VERT_BANDS),

	/** Thin backward diagonal */
	THIN_BACKWARD_DIAG(CellStyle.THIN_BACKWARD_DIAG),

	/** Thin forward diagonal */
	THIN_FORWARD_DIAG(CellStyle.THIN_FORWARD_DIAG),

	/** Squares */
	SQUARES(CellStyle.SQUARES),

	/** Diamonds */
	DIAMONDS(CellStyle.DIAMONDS),

	/** Less Dots */
	LESS_DOTS(CellStyle.LESS_DOTS),

	/** Least Dots */
	LEAST_DOTS(CellStyle.LEAST_DOTS);

	private short type;

	private FillPatternType(short type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public short getType() {

		return type;
	}

	public static FillPatternType valueOf(short type) {

		for (FillPatternType fillPatternType : FillPatternType.values()) {
			if (fillPatternType.getType() == type) {
				return fillPatternType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}

}
