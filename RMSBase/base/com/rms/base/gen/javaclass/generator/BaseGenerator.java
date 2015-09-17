package com.rms.base.gen.javaclass.generator;

import com.rms.base.constant.Characters;

/**
 *
 * @author ri.meisei
 * @since 2015/09/14
 */
public abstract class BaseGenerator {

	private final StringBuilder buffer = new StringBuilder();

	protected String lineSeparator = Characters.LINE_SEPARATOR_SYSTEM;

	protected BaseGenerator() {

	}

	public abstract void generate();

	protected final BaseGenerator append(String content) {

		buffer.append(content);

		return this;
	}

	public final <T extends BaseGenerator> T appendTo(T baseGenerator) {

		((BaseGenerator) baseGenerator).getBuffer().append(buffer);

		return baseGenerator;
	}

	private StringBuilder getBuffer() {

		return buffer;
	}

	protected final int getLength() {

		return buffer.length();
	}

	protected final boolean isEmpty() {

		return (buffer.length() == 0);
	}

	protected final void clearBuffer() {

		buffer.setLength(0);
	}

	/**
	 * @return lineSeparator
	 */
	protected final String getLineSeparator() {

		return lineSeparator;
	}

	/**
	 * @param lineSeparator
	 *            セットする lineSeparator
	 */
	protected final void setLineSeparator(String lineSeparator) {

		this.lineSeparator = lineSeparator;
	}

	@Override
	public String toString() {

		return buffer.toString();
	}
}
