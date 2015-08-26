package com.rms.common.json.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.constant.Characters;
import com.rms.common.json.enums.JsonType;
import com.rms.common.json.enums.TokenType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class ObjectModelImpl extends StructureModelIml implements ObjectModel {

	private Map<String, JsonModel> jsonModelMap = new LinkedHashMap<String, JsonModel>();

	public ObjectModelImpl() {

		super(JsonType.OBJECT);
	}

	@Override
	public boolean isExist(String name) {

		return (name != null && jsonModelMap.containsKey(name));
	}

	@Override
	public boolean isObjectModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.OBJECT);
	}

	@Override
	public boolean isArrayModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.ARRAY);
	}

	@Override
	public boolean isBooleanModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.BOOLEAN);
	}

	@Override
	public boolean isStringModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.STRING);
	}

	@Override
	public boolean isNumberModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.NUMBER);
	}

	@Override
	public boolean isNullModel(String name) {

		return isExist(name) && getJsonModel(name).is(JsonType.NULL);
	}

	@Override
	public JsonModel getJsonModel(String name) {

		return jsonModelMap.get(name);
	}

	@Override
	public ObjectModel getObjectModel(String name) {

		return ObjectModel.class.cast(getJsonModel(name));
	}

	@Override
	public ArrayModel getArrayModel(String name) {

		return ArrayModel.class.cast(getJsonModel(name));
	}

	@Override
	public BooleanModel getBooleanModel(String name) {

		return BooleanModel.class.cast(getJsonModel(name));
	}

	@Override
	public StringModel getStringModel(String name) {

		return StringModel.class.cast(getJsonModel(name));
	}

	@Override
	public NumberModel getNumberModel(String name) {

		return NumberModel.class.cast(getJsonModel(name));
	}

	@Override
	public NullModel getNullModel(String name) {

		return NullModel.class.cast(getJsonModel(name));
	}

	@Override
	public ObjectModel add(String name, JsonModel jsonModel) {

		jsonModelMap.put(name, jsonModel);

		return this;
	}

	@Override
	public ObjectModel add(String name, boolean value) {

		BooleanModel booleanModel = JsonModelFactory.newBooleanModel(value);

		return add(name, booleanModel);
	}

	@Override
	public ObjectModel add(String name, String value) {

		StringModel stringModel = JsonModelFactory.newStringModel(value);

		return add(name, stringModel);
	}

	@Override
	public ObjectModel add(String name, long value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(name, numberModel);
	}

	@Override
	public ObjectModel add(String name, double value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(name, numberModel);
	}

	@Override
	public ObjectModel add(String name, BigDecimal value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(name, numberModel);
	}

	@Override
	public String jsonText() {

		StringBuilder builder = new StringBuilder();

		builder.append(TokenType.OBJECT_BEGIN);

		for (String name : jsonModelMap.keySet()) {
			JsonModel jsonModel = jsonModelMap.get(name);
			builder.append(Characters.QUOTATION);
			builder.append(name);
			builder.append(Characters.QUOTATION);
			builder.append(TokenType.NAME_SEPARATOR);
			builder.append(jsonModel.jsonText());
			builder.append(TokenType.VALUE_SEPARATOR);
		}

		if (!jsonModelMap.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}

		builder.append(TokenType.OBJECT_END);

		return builder.toString();
	}

	@Override
	public String stringValue() {

		return jsonText();
	}
}
