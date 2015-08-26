package com.rms.common.json.model;

import java.math.BigDecimal;

/**
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
public class JsonModelFactory {

	private static final NullModel NULL = new NullModelImpl();

	private static final BooleanModel TRUE = new BooleanModelImpl(true);

	private static final BooleanModel FALSE = new BooleanModelImpl(false);

	private JsonModelFactory() {

	}

	public static ObjectModel newObjectModel() {

		return new ObjectModelImpl();
	}

	public static ArrayModel newArrayModel() {

		return new ArrayModelImpl();
	}

	public static StringModel newStringModel(String value) {

		StringModel stringModel = new StringModelImpl(value);

		return stringModel;
	}

	public static BooleanModel newBooleanModel(boolean value) {

		return value ? TRUE : FALSE;
	}

	public static NumberModel newNumberModel(long value) {

		return newNumberModel(new BigDecimal(value));
	}

	public static NumberModel newNumberModel(double value) {

		return newNumberModel(new BigDecimal(value));
	}

	public static NumberModel newNumberModel(BigDecimal value) {

		NumberModel numberModel = new NumberModelImpl(value);

		return numberModel;
	}

	public static NullModel newNullModel() {

		return NULL;
	}

}
