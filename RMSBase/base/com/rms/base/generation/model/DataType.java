package com.rms.base.generation.model;

import java.util.HashMap;
import java.util.Map;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
private final class DataType {

	private final static Map<String, DataType> dataTypeCollection = new HashMap<String, DataType>();

	public final static DataType BYTE_PRIMITIVE = new DataType("byte", "0");

	public final static DataType BYTE = new DataType("Byte", "0");

	public final static DataType BYTE_ARRAY = new DataType("Byte[]", "new Byte[0]");

	public final static DataType SHORT_PRIMITIVE = new DataType("short", "0");

	public final static DataType SHORT = new DataType("Short", "0");

	public final static DataType SHORT_ARRAY = new DataType("Short[]", "new Short[0]");

	public final static DataType CHARACTER_PRIMITIVE = new DataType("char", "\"\"");

	public final static DataType CHARACTER = new DataType("Character", "\"\"");

	public final static DataType CHARACTER_ARRAY = new DataType("Character[]", "new Character[0]");

	public final static DataType INTEGER_PRIMITIVE = new DataType("int", "0");

	public final static DataType INTEGER = new DataType("Integer", "0");

	public final static DataType INTEGER_ARRAY = new DataType("Integer[]", "new Integer[0]");

	public final static DataType LONG_PRIMITIVE = new DataType("long", "0L");

	public final static DataType LONG = new DataType("Long", "0L");

	public final static DataType LONG_ARRAY = new DataType("Long[]", "new Long[0]");

	public final static DataType FLOAT_PRIMITIVE = new DataType("float", "0.0f");

	public final static DataType FLOAT = new DataType("Float", "0.0f");

	public final static DataType FLOAT_ARRAY = new DataType("Float[]", "new Float[0]");

	public final static DataType DOUBLE_PRIMITIVE = new DataType("double", "0.0d");

	public final static DataType DOUBLE = new DataType("Double", "0.0d");

	public final static DataType DOUBLE_ARRAY = new DataType("Double[]", "new Double[0]");

	public final static DataType BOOLEAN_PRIMITIVE = new DataType("boolean", "false");

	public final static DataType BOOLEAN = new DataType("Boolean", "false");

	public final static DataType BOOLEAN_ARRAY = new DataType("Boolean[]", "new Boolean[0]");

	public final static DataType STRING = new DataType("String", "\"\"");

	public final static DataType STRING_ARRAY = new DataType("String[]", "new String[0]");

	public final static DataType OBJECT = new DataType("Object", "null");

	public final static DataType OBJECT_ARRAY = new DataType("Object[]", "new Object[0]");

	private final String typeName;

	private final String defaultValue;

	private final String returnText;

	private DataType(String typeName, String defaultValue) {

		Assertion.assertNotBlank("typeName", typeName);
		Assertion.assertNotBlank("defaultValue", defaultValue);

		this.typeName = typeName;
		this.defaultValue = defaultValue;
		returnText = "return " + defaultValue;
		dataTypeCollection.put(typeName, this);
	}

	public static synchronized void addDataType(String typeName, String defaultValue) {

		new DataType(typeName, defaultValue);
	}

	public static synchronized DataType getDataType(String typeName) {

		Assertion.assertNotBlank("typeName", typeName);

		DataType dataType = dataTypeCollection.get(typeName);

		if (dataType == null) {
			dataType = OBJECT;
		}

		return dataType;
	}

	public String getTypeName() {

		return typeName;
	}

	public Object getDefaultValue() {

		return defaultValue;
	}

	public String getReturnText() {

		return returnText;
	}
}
