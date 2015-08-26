package com.rms.common.json.enums;

import com.rms.base.validate.Assertion;

/**
 * 
 * @author ri.meisei
 * @since 2014/01/14
 */
enum ModelType {

	NULL(0), OBJECT(1), ARRAY(2), STRING(3), NUMBER(4), BOOLEAN(5);

	// INTEGER(4), LONG(5), DOUBLE(6), BIGINTEGER(7), BIGDECIMAL(8),

	private int type;

	private ModelType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public int getType() {

		return type;
	}

	public void assertModel(ModelType modelType) {

		Assertion.assertNotNull("modelType", modelType);

		if (type != modelType.getType()) {
			throw new UnsupportedOperationException();
		}
	}
}
