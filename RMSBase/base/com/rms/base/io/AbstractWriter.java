package com.rms.base.io;

import java.io.IOException;

/**
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
abstract class AbstractWriter implements IWriter {

	/**
	 *
	 * @param outputStream
	 * @param charset
	 */
	protected AbstractWriter() {

	}

	/**
	 *
	 * @param bytes
	 * @throws IOException
	 */
	@Override
	public final void write(byte[] bytes) throws IOException {

		if (bytes != null) {
			getWriter().write(new String(bytes, getCharset()));
		}
	}

	/**
	 *
	 * @param chars
	 * @throws IOException
	 */
	@Override
	public final void write(char[] chars) throws IOException {

		if (chars != null) {
			getWriter().write(new String(chars));
		}
	}
}
