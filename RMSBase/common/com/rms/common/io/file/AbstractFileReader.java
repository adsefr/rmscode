package com.rms.common.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.rms.base.validate.Assertion;
import com.rms.common.io.IOConst;
import com.rms.common.io.IOFactory;
import com.rms.common.io.IOUtil;
import com.rms.common.io.file.FileConst.FileOperateType;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */

abstract class AbstractFileReader implements FileReader {

	private Charset charset = IOConst.DEFAULT_CHARSET;

	private File targetFile;

	private InputStream inputStream;

	private BufferedReader reader;

	private FileOperateType operateType;

	private byte[] bufferBytes = new byte[IOConst.DEFAULT_BUFFER_SIZE];

	private char[] bufferChars = new char[IOConst.DEFAULT_BUFFER_SIZE];

	private int readedByteCnt;

	private int readedCharCnt;

	private String readedLine;

	/**
	 * 
	 * @param targetFile
	 * @param charset
	 * @throws IOException
	 */
	AbstractFileReader(File targetFile, Charset charset) throws FileNotFoundException {

		Assertion.assertNotNull("targetFile", targetFile);
		Assertion.assertNotNull("charset", charset);

		this.charset = charset;
		this.targetFile = targetFile;
		this.inputStream = IOFactory.newFileInputStream(targetFile);
		this.reader = IOFactory.newBufferedReader(inputStream, charset);
	}

	/**
	 * 
	 * @param inputStream
	 * @param charset
	 */
	AbstractFileReader(InputStream inputStream, Charset charset) {

		Assertion.assertNotNull("inputStream", inputStream);
		Assertion.assertNotNull("charset", charset);

		this.charset = charset;
		this.inputStream = inputStream;
		this.reader = IOFactory.newBufferedReader(inputStream, charset);
	}

	public final File getTargetFile() {

		return targetFile;
	}

	public final Charset getCharset() {

		return this.charset;
	}

	@Override
	public final void close() throws IOException {

		IOUtil.close(reader);
	}

	@Override
	public void setBufferArray(byte[] bufferArray) {

		this.bufferBytes = bufferArray;
	}

	@Override
	public void setBufferArray(char[] bufferArray) {

		if (bufferArray != null) {
			this.bufferChars = bufferArray;
		}
	}

	@Override
	public byte[] readBytes() throws IOException {

		checkOperateType(FileOperateType.BYTE_READ);

		return Arrays.copyOf(bufferBytes, readedByteCnt);
	}

	@Override
	public boolean hasNextByte() throws IOException {

		checkOperateType(FileOperateType.BYTE_READ);

		readedByteCnt = inputStream.read(bufferBytes);

		return (readedByteCnt != -1);
	}

	@Override
	public boolean hasNextChar() throws IOException {

		checkOperateType(FileOperateType.CHAR_READ);

		readedCharCnt = reader.read(bufferChars);

		return (readedCharCnt != -1);
	}

	@Override
	public char[] readChars() throws IOException {

		checkOperateType(FileOperateType.CHAR_READ);

		return Arrays.copyOf(bufferChars, readedCharCnt);
	}

	@Override
	public String readLine() throws IOException {

		checkOperateType(FileOperateType.LINE_READ);

		return readedLine;
	}

	@Override
	public boolean hasNextLine() throws IOException {

		checkOperateType(FileOperateType.LINE_READ);

		readedLine = reader.readLine();

		return (readedLine != null);
	}

	private void checkOperateType(FileOperateType type) {

		if (type == null) {
			throw new IllegalArgumentException("type is null.");
		}

		if (this.operateType == null) {
			this.operateType = type;
		}

		if (this.operateType != type) {
			throw new UnsupportedOperationException();
		}
	}
}
