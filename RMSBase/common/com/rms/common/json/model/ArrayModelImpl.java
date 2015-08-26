package com.rms.common.json.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rms.common.json.enums.JsonType;
import com.rms.common.json.enums.TokenType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class ArrayModelImpl extends StructureModelIml implements ArrayModel {

	private List<JsonModel> jsonModels = new ArrayList<JsonModel>();

	public ArrayModelImpl() {

		super(JsonType.ARRAY);
	}

	@Override
	public boolean isExist(int index) {

		return (jsonModels.size() >= index);
	}

	@Override
	public JsonModel getJsonModel(int index) {

		return jsonModels.get(index);
	}

	@Override
	public ObjectModel getObjectModel(int index) {

		return ObjectModel.class.cast(getJsonModel(index));
	}

	@Override
	public ArrayModel getArrayModel(int index) {

		return ArrayModel.class.cast(getJsonModel(index));
	}

	@Override
	public BooleanModel getBooleanModel(int index) {

		return BooleanModel.class.cast(getJsonModel(index));
	}

	@Override
	public StringModel getStringModel(int index) {

		return StringModel.class.cast(getJsonModel(index));
	}

	@Override
	public NumberModel getNumberModel(int index) {

		return NumberModel.class.cast(getJsonModel(index));
	}

	@Override
	public NullModel getNullModel(int index) {

		return NullModel.class.cast(getJsonModel(index));
	}

	@Override
	public boolean isObjectModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.OBJECT);
	}

	@Override
	public boolean isArrayModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.ARRAY);
	}

	@Override
	public boolean isBooleanModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.BOOLEAN);
	}

	@Override
	public boolean isStringModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.STRING);
	}

	@Override
	public boolean isNumberModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.NUMBER);
	}

	@Override
	public boolean isNullModel(int index) {

		return isExist(index) && getJsonModel(index).is(JsonType.NULL);
	}

	@Override
	public ArrayModel add(JsonModel jsonModel) {

		jsonModels.add(jsonModel);

		return this;
	}

	@Override
	public ArrayModel add(int index, JsonModel jsonModel) {

		jsonModels.add(index, jsonModel);

		return this;
	}

	@Override
	public ArrayModel add(boolean value) {

		BooleanModel booleanModel = JsonModelFactory.newBooleanModel(value);

		return add(booleanModel);
	}

	@Override
	public ArrayModel add(int index, boolean value) {

		BooleanModel booleanModel = JsonModelFactory.newBooleanModel(value);

		return add(index, booleanModel);
	}

	@Override
	public ArrayModel add(String value) {

		StringModel stringModel = JsonModelFactory.newStringModel(value);

		return add(stringModel);
	}

	@Override
	public ArrayModel add(int index, String value) {

		StringModel stringModel = JsonModelFactory.newStringModel(value);

		return add(index, stringModel);
	}

	@Override
	public ArrayModel add(long value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(numberModel);
	}

	@Override
	public ArrayModel add(int index, long value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(index, numberModel);
	}

	@Override
	public ArrayModel add(float value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(numberModel);
	}

	@Override
	public ArrayModel add(int index, float value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(index, numberModel);
	}

	@Override
	public ArrayModel add(double value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(numberModel);
	}

	@Override
	public ArrayModel add(int index, double value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(index, numberModel);
	}

	@Override
	public ArrayModel add(BigDecimal value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(numberModel);
	}

	@Override
	public ArrayModel add(int index, BigDecimal value) {

		NumberModel numberModel = JsonModelFactory.newNumberModel(value);

		return add(index, numberModel);
	}

	@Override
	public String jsonText() {

		StringBuilder builder = new StringBuilder();

		builder.append(TokenType.ARRAY_BEGIN);

		for (JsonModel jsonModel : jsonModels) {
			builder.append(jsonModel.jsonText());
			builder.append(TokenType.VALUE_SEPARATOR);
		}

		if (!jsonModels.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}

		builder.append(TokenType.ARRAY_END);

		return builder.toString();
	}

	@Override
	public String stringValue() {

		return jsonText();
	}
}
