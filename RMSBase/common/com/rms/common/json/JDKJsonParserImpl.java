package com.rms.common.json;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.common.json.exception.JsonParseException;
import com.rms.common.json.model.JsonModel;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/14
 */
class JDKJsonParserImpl extends JDKJsonParser {

	private JsonStructure jsonStructure;

	JDKJsonParserImpl(JsonStructure jsonStructure) {

		this.jsonStructure = jsonStructure;
	}

	public JsonModel parse() throws JsonParseException {

		ValueType valueType = jsonStructure.getValueType();

		if (ValueType.OBJECT == valueType) {
			return parseObject((JsonObject) jsonStructure);
		}

		if (ValueType.ARRAY == valueType) {
			return parseArray((JsonArray) jsonStructure);
		}

		throw new UnexpectedTypeException(valueType.getClass().getName());
	}

}
