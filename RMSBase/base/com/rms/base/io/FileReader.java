package com.rms.base.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @since 2013/10/25
 * @author ri.meisei
 */
public interface FileReader extends IReader {

	@Override
	public FileInputStream getInputStream();

	@Override
	public BufferedReader getReader();

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean hasLines() throws IOException;

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
	 * @param readLineCnt
	 * @return
	 * @throws IOException
	 */
	public List<String> readLines(int readLineCnt) throws IOException;

	/**
	 *
	 * @param lineArray
	 * @return
	 * @throws IOException
	 */
	public int readLines(String[] lineArray) throws IOException;

	/**
	 *
	 * @param lineList
	 * @return
	 * @throws IOException
	 */
	public int readLines(List<String> lineList) throws IOException;
}
