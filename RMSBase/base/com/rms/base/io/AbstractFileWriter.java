package com.rms.base.io;

import java.io.IOException;

import com.rms.base.constant.Characters;

/**
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
abstract class AbstractFileWriter extends AbstractWriter implements FileWriter {

	protected AbstractFileWriter() {

	}

	@Override
	public void write(String text) throws IOException {

		if (text != null)
			getWriter().write(text);
	}

	@Override
	public void writeLine(String text) throws IOException {

		if (text != null) {
			getWriter().write(text);
		}
		getWriter().write(Characters.LINE_SEPARATOR_SYSTEM);
	}
}
