package com.rms.common.json.model;

import com.rms.base.validate.Assertion;
import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
abstract class JsonModelImpl implements JsonModel {

	private JsonType JSON_TYPE;

	protected JsonModelImpl(JsonType jsonType) {

		Assertion.assertNotNull("jsonType", jsonType);

		JSON_TYPE = jsonType;
	}

	@Override
	public JsonType getJsonType() {

		return JSON_TYPE;
	}

	@Override
	public boolean is(JsonType jsonType) {

		return (JSON_TYPE == jsonType);
	}
}
