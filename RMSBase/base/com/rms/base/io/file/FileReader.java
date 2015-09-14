package com.rms.base.io.file;

import java.io.IOException;

import com.rms.base.io.IReader;

/**
 * 
 * 
 * @since 2013/10/25
 * @author ri.meisei
 */
public interface FileReader extends IReader {

	/**
	 * 
	 * @param bufferArray
	 */
	public void setBufferArray(byte[] bufferArray);

	/**
	 * 
	 * @param bufferArray
	 */
	public void setBufferArray(char[] bufferArray);

	/**
	 * 
	 * @return
	 */
	public boolean hasNextByte() throws IOException;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] readBytes() throws IOException;

	/**
	 * 
	 * @return
	 */
	public boolean hasNextChar() throws IOException;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public char[] readChars() throws IOException;

	/**
	 * 
	 * @return
	 */
	public boolean hasNextLine() throws IOException;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readLine() throws IOException;

}
