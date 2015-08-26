package com.rms.common.json.model;

import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class NullModelImpl extends PrimitiveModelImpl implements NullModel {

	NullModelImpl() {

		super(JsonType.NULL);
	}

	@Override
	public String jsonText() {

		return "null";
	}

	@Override
	public String stringValue() {

		return "null";
	}

	@Override
	public String toString() {

		return "null";
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof NumberModelImpl) {
			return true;
		}

		return false;
	}

}
