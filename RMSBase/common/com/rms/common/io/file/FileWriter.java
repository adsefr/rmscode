package com.rms.common.io.file;

import java.io.IOException;
import java.io.InputStream;

import com.rms.common.io.IWriter;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
public interface FileWriter extends IWriter {

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

	/**
	 * 
	 * @param text
	 * @throws IOException
	 */
	public void write(String text) throws IOException;

	/**
	 * 
	 * @param text
	 * @throws IOException
	 */
	public void writeLine(String text) throws IOException;

	/**
	 * 
	 * @param is
	 * @throws IOException
	 */
	public void write(InputStream is) throws IOException;
}
