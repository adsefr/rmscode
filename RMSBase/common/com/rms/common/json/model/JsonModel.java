package com.rms.common.json.model;

import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/14
 */
public interface JsonModel {

	public JsonType getJsonType();

	public boolean is(JsonType jsonType);

	public String jsonText();

	public String stringValue();

}
