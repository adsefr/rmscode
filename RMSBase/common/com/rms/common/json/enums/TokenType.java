package com.rms.common.json.enums;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/16
 */
public enum TokenType {
	/**
	 * begin-object = ws %x7B ws ; { left curly bracket
	 */
	OBJECT_BEGIN('{'),

	/**
	 * end-object = ws %x7D ws ; } right curly bracket
	 */
	OBJECT_END('}'),

	/**
	 * begin-array = ws %x5B ws ; [ left square bracket
	 */
	ARRAY_BEGIN('['),

	/**
	 * end-array = ws %x5D ws ; ] right square bracket
	 */
	ARRAY_END(']'),

	/**
	 * name-separator = ws %x3A ws ; : colon
	 */
	NAME_SEPARATOR(':'),

	/**
	 * value-separator = ws %x2C ws ; , comma
	 */
	VALUE_SEPARATOR(',');

	private Character character;

	private TokenType(char c) {

		character = Character.valueOf(c);
	}

	public char charValue() {

		return character.charValue();
	}

	public static boolean isToken(char c) {

		for (TokenType tokenType : TokenType.values()) {
			if (tokenType.charValue() == c) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {

		return Character.toString(character);
	}
}
