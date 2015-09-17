package com.rms.base.io.file;

import java.io.IOException;
import java.util.List;

import com.rms.base.io.IReader;

/**
 *
 * @since 2013/10/25
 * @author ri.meisei
 */
public interface FileReader extends IReader {

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean hasNext() throws IOException;

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
	public byte[] readBytes(byte[] array) throws IOException;

	/**
	 *
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public byte[] readBytes(int length) throws IOException;

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
	public char[] readChars(char[] array) throws IOException;

	/**
	 *
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public char[] readChars(int length) throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public String readLine() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public List<String> readLines() throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public List<String> readLines(int lines) throws IOException;

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public String[] readLines(String[] lines) throws IOException;

}
