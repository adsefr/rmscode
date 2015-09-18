package com.rms.base.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
public interface IReader extends Closeable {

	/**
	 *
	 * @return
	 */
	public Charset getCharset();

	/**
	 *
	 * @return
	 */
	public InputStream getInputStream();

	/**
	 *
	 * @return
	 */
	public Reader getReader();

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean hasBytes() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public byte readByte() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public byte[] readBytes() throws IOException;

	/**
	 *
	 * @param array
	 * @return
	 * @throws IOException
	 */
	public int readBytes(byte[] array) throws IOException;

	/**
	 *
	 * @param readByteLength
	 * @return
	 * @throws IOException
	 */
	public byte[] readBytes(int readByteLength) throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean hasChars() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public char readChar() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public char[] readChars() throws IOException;

	/**
	 *
	 * @param array
	 * @return
	 * @throws IOException
	 */
	public int readChars(char[] array) throws IOException;

	/**
	 *
	 * @param readCharLength
	 * @return
	 * @throws IOException
	 */
	public char[] readChars(int readCharLength) throws IOException;
}
