package com.rms.common.json.model;

import java.math.BigDecimal;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
public interface ObjectModel extends StructureModel {

	boolean isExist(String name);

	boolean isObjectModel(String name);

	boolean isArrayModel(String name);

	boolean isBooleanModel(String name);

	boolean isStringModel(String name);

	boolean isNumberModel(String name);

	boolean isNullModel(String name);

	JsonModel getJsonModel(String name);

	ObjectModel getObjectModel(String name);

	ArrayModel getArrayModel(String name);

	BooleanModel getBooleanModel(String name);

	StringModel getStringModel(String name);

	NumberModel getNumberModel(String name);

	NullModel getNullModel(String name);

	ObjectModel add(String name, JsonModel jsonModel);

	ObjectModel add(String name, boolean value);

	ObjectModel add(String name, String value);

	ObjectModel add(String name, long value);

	ObjectModel add(String name, double value);

	ObjectModel add(String name, BigDecimal value);
}
