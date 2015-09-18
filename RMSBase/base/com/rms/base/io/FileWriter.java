package com.rms.base.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
public interface FileWriter extends IWriter {

	@Override
	public FileOutputStream getOutputStream();

	@Override
	public BufferedWriter getWriter();

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
}
