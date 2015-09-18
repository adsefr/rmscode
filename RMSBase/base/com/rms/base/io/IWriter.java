package com.rms.base.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * @author ri.meisei
 * @since 2013/10/25
 */
public interface IWriter extends Closeable {

	/**
	 *
	 * @return
	 */
	public Charset getCharset();

	/**
	 *
	 * @return
	 */
	public OutputStream getOutputStream();

	/**
	 *
	 * @return
	 */
	public Writer getWriter();

	/**
	 *
	 * @param bytes
	 * @throws IOException
	 */
	public void write(byte[] bytes) throws IOException;

	/**
	 *
	 * @param chars
	 * @throws IOException
	 */
	public void write(char[] chars) throws IOException;
}
