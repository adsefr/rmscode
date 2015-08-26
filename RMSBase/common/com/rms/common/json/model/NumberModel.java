package com.rms.common.json.model;

import java.math.BigDecimal;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
public interface NumberModel extends PrimitiveModel {

	byte getByte();

	short getShort();

	int getInt();

	long getLong();

	float getFloat();

	double getDobule();

	BigDecimal getBigDecimal();
}
