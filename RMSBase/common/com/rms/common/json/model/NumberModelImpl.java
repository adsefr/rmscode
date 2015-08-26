package com.rms.common.json.model;

import java.math.BigDecimal;

import com.rms.base.validate.Assertion;
import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
class NumberModelImpl extends PrimitiveModelImpl implements NumberModel {

	private BigDecimal value;

	private NumberModelImpl() {

		super(JsonType.NUMBER);
	}

	public NumberModelImpl(long value) {

		this();

		this.value = new BigDecimal(value);
	}

	public NumberModelImpl(double value) {

		this();

		this.value = new BigDecimal(value);
	}

	public NumberModelImpl(BigDecimal value) {

		this();

		Assertion.assertNotNull("value", value);

		this.value = value;
	}

	public NumberModelImpl(String value) {

		this();

		Assertion.assertNotNull(value, "value");

		this.value = new BigDecimal(value);
	}

	@Override
	public byte getByte() {

		return value.byteValueExact();
	}

	@Override
	public short getShort() {

		return value.shortValueExact();
	}

	@Override
	public int getInt() {

		return value.intValueExact();
	}

	@Override
	public long getLong() {

		return value.longValueExact();
	}

	@Override
	public float getFloat() {

		return value.floatValue();
	}

	@Override
	public double getDobule() {

		return value.doubleValue();
	}

	@Override
	public BigDecimal getBigDecimal() {

		return value;
	}

	@Override
	public String jsonText() {

		return toString();
	}

	@Override
	public String stringValue() {

		return toString();
	}

	@Override
	public String toString() {

		return value.toString();
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
		if (!(obj instanceof NumberModelImpl)) {
			return false;
		}
		NumberModelImpl other = (NumberModelImpl) obj;
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
