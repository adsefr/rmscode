package com.rms.base.generation.classfile;

import java.io.BufferedWriter;
import java.io.IOException;

import com.rms.base.constant.Characters;

/**
 *
 * @author ri.meisei
 * @since 2015/09/14
 */
public abstract class BaseGenerator {

	protected final StringBuilder buffered = new StringBuilder();

	protected String lineSeparator = Characters.LINE_SEPARATOR_SYSTEM;

	public BaseGenerator() {

	}

	/**
	 * @return lineSeparator
	 */
	public final String getLineSeparator() {

		return lineSeparator;
	}

	/**
	 * @param lineSeparator
	 *            セットする lineSeparator
	 */
	public final void setLineSeparator(String lineSeparator) {

		this.lineSeparator = lineSeparator;
	}

	protected abstract void generate();

	/**
	 *
	 * @param buffered
	 */
	public final void generate(StringBuilder buffered) {

		generate();

		buffered.append(this.buffered);
	}

	/**
	 *
	 * @param bufferedWriter
	 * @throws IOException
	 */
	public final void generate(BufferedWriter bufferedWriter) throws IOException {

		bufferedWriter.write(buffered.toString());
	}

	public final void clearBuffered() {

		buffered.setLength(0);
	}
}
