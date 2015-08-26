package com.rms.common.json;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.common.json.exception.JsonParseException;
import com.rms.common.json.model.ArrayModel;
import com.rms.common.json.model.JsonModel;
import com.rms.common.json.model.JsonModelFactory;
import com.rms.common.json.model.ObjectModel;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/14
 */
public abstract class JDKJsonParser implements JsonParser {

	protected JDKJsonParser() {

	}

	public static JDKJsonParser newInstance(InputStream inputStream) {

		JsonStructure jsonStructure = Json.createReader(inputStream).read();

		return new JDKJsonParserImpl(jsonStructure);
	}

	public static JDKJsonParser newInstance(Reader reader) {

		JsonStructure jsonStructure = Json.createReader(reader).read();

		return new JDKJsonParserImpl(jsonStructure);
	}

	@Override
	public abstract JsonModel parse() throws JsonParseException;

	protected JsonModel parseJsonValue(JsonValue jsonValue) throws JsonParseException {

		ValueType valueType = jsonValue.getValueType();

		switch (valueType) {
		case OBJECT:
			return parseObject((JsonObject) jsonValue);

		case ARRAY:
			return parseArray((JsonArray) jsonValue);

		case STRING:
			String value = ((JsonString) jsonValue).getString();
			return JsonModelFactory.newStringModel(value);

		case NUMBER:
			BigDecimal bigDecimal = ((JsonNumber) jsonValue).bigDecimalValue();
			return JsonModelFactory.newNumberModel(bigDecimal);

		case FALSE:
			return JsonModelFactory.newBooleanModel(false);

		case TRUE:
			return JsonModelFactory.newBooleanModel(true);

		case NULL:
			return JsonModelFactory.newNullModel();

		default:
			throw new UnexpectedDataException("valueType:" + valueType);
		}
	}

	protected ObjectModel parseObject(JsonObject jsonObject) throws JsonParseException {

		ObjectModel objectModel = JsonModelFactory.newObjectModel();

		for (String key : jsonObject.keySet()) {
			JsonValue value = jsonObject.get(key);
			JsonModel jsonModel = parseJsonValue(value);
			objectModel.add(key, jsonModel);
		}

		return objectModel;
	}

	protected ArrayModel parseArray(JsonArray jsonArray) throws JsonParseException {

		ArrayModel arrayModel = JsonModelFactory.newArrayModel();

		for (Iterator<JsonValue> iterator = jsonArray.iterator(); iterator.hasNext();) {
			JsonValue jsonValue = iterator.next();
			JsonModel jsonModel = parseJsonValue(jsonValue);
			arrayModel.add(jsonModel);
		}

		return arrayModel;
	}

}
