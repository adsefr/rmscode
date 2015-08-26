package com.rms.common.json.model;

import java.math.BigDecimal;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
public interface ArrayModel extends StructureModel {

	boolean isExist(int index);

	JsonModel getJsonModel(int index);

	ObjectModel getObjectModel(int index);

	ArrayModel getArrayModel(int index);

	BooleanModel getBooleanModel(int index);

	StringModel getStringModel(int index);

	NumberModel getNumberModel(int index);

	NullModel getNullModel(int index);

	boolean isObjectModel(int index);

	boolean isArrayModel(int index);

	boolean isBooleanModel(int index);

	boolean isStringModel(int index);

	boolean isNumberModel(int index);

	boolean isNullModel(int index);

	ArrayModel add(JsonModel jsonModel);

	ArrayModel add(int index, JsonModel jsonModel);

	ArrayModel add(boolean value);

	ArrayModel add(int index, boolean value);

	ArrayModel add(String value);

	ArrayModel add(int index, String value);

	ArrayModel add(long value);

	ArrayModel add(int index, long value);

	ArrayModel add(float value);

	ArrayModel add(int index, float value);

	ArrayModel add(double value);

	ArrayModel add(int index, double value);

	ArrayModel add(BigDecimal value);

	ArrayModel add(int index, BigDecimal value);

}
