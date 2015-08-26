package com.rms.common.json.model;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;
import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class StringModelImpl extends PrimitiveModelImpl implements StringModel {

	private String value;

	public StringModelImpl(String value) {

		super(JsonType.STRING);

		Assertion.assertNotNull(value, "value");

		this.value = value;
	}

	@Override
	public String get() {

		return value;
	}

	@Override
	public int getLength() {

		return value.length();
	}

	@Override
	public String jsonText() {

		return Characters.QUOTATION + value + Characters.QUOTATION;
	}

	@Override
	public String stringValue() {

		return value;
	}

	@Override
	public String toString() {

		return value;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof StringModelImpl)) {
			return false;
		}
		StringModelImpl other = (StringModelImpl) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
