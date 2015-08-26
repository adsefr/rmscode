package com.rms.base.jdbc.constant;

import java.sql.ResultSet;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public enum HoldabilityType {

	HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT), //

	CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT), //
	;

	private int type;

	private HoldabilityType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public final int getType() {

		return type;
	}

	public static HoldabilityType valueOf(int type) {

		switch (type) {
		case ResultSet.HOLD_CURSORS_OVER_COMMIT:
			return HOLD_CURSORS_OVER_COMMIT;
		case ResultSet.CLOSE_CURSORS_AT_COMMIT:
			return CLOSE_CURSORS_AT_COMMIT;
		default:
			return null;
		}
	}
}
