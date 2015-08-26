package com.rms.common.json;

import java.io.InputStream;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/31
 */
public class JsonParserFactory {

	private static JsonParserFactory jsonParserFactory = new JsonParserFactory();

	private JsonParserFactory() {

	}

	public static JsonParserFactory newInstance() {

		return jsonParserFactory;
	}

	public static JsonParser createJsonParser(InputStream inputStream) {

		return JDKJsonParser.newInstance(inputStream);// TODO
	}
}
