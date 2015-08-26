package com.rms.common.json;

import com.rms.common.json.exception.JsonParseException;
import com.rms.common.json.model.JsonModel;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public interface JsonParser {

	public JsonModel parse() throws JsonParseException;

}
