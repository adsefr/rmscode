package com.rms.common.json.model;

import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class BooleanModelImpl extends PrimitiveModelImpl implements BooleanModel {

	private boolean value;

	public BooleanModelImpl(boolean value) {

		super(JsonType.BOOLEAN);

		this.value = value;
	}

	@Override
	public boolean get() {

		return value;
	}

	@Override
	public String jsonText() {

		return String.valueOf(value);
	}

	@Override
	public String stringValue() {

		return String.valueOf(value);
	}

	@Override
	public String toString() {

		return String.valueOf(value);
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (value ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BooleanModelImpl)) {
			return false;
		}
		BooleanModelImpl other = (BooleanModelImpl) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}

}
