package com.rms.common.generate.javaclass.info;

import java.lang.reflect.Modifier;

public enum ModifierType {
	PUBLIC(Modifier.PUBLIC), //
	PRIVATE(Modifier.PRIVATE), //
	PROTECTED(Modifier.PROTECTED), //
	STATIC(Modifier.STATIC), //
	FINAL(Modifier.FINAL), //
	SYNCHRONIZED(Modifier.SYNCHRONIZED), //
	VOLATILE(Modifier.VOLATILE), //
	TRANSIENT(Modifier.TRANSIENT), //
	NATIVE(Modifier.NATIVE), //
	INTERFACE(Modifier.INTERFACE), //
	ABSTRACT(Modifier.ABSTRACT), //
	STRICT(Modifier.STRICT);//

	private final int code;

	private ModifierType(int code) {

		this.code = code;
	}

	public int getCode() {

		return code;
	}

	// private static final int CLASS_MODIFIERS =
	// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
	// Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL |
	// Modifier.STRICT;
	//
	// /**
	// * See JLSv3 section 9.1.1.
	// */
	// private static final int INTERFACE_MODIFIERS =
	// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
	// Modifier.ABSTRACT | Modifier.STATIC | Modifier.STRICT;
	//
	//
	// /**
	// * See JLSv3 section 8.8.3.
	// */
	// private static final int CONSTRUCTOR_MODIFIERS =
	// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
	//
	// /**
	// * See JLSv3 section 8.4.3.
	// */
	// private static final int METHOD_MODIFIERS =
	// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
	// Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL |
	// Modifier.SYNCHRONIZED | Modifier.NATIVE | Modifier.STRICT;
	//
	// /**
	// * See JLSv3 section 8.3.1.
	// */
	// private static final int FIELD_MODIFIERS =
	// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
	// Modifier.STATIC | Modifier.FINAL | Modifier.TRANSIENT |
	// Modifier.VOLATILE;
}
