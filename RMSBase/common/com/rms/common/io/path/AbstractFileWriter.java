package com.rms.common.io.path;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;
import com.rms.common.io.IOConst;
import com.rms.common.io.IOFactory;
import com.rms.common.io.IOUtil;
import com.rms.common.io.file.FileConst.FileOperateType;

/**
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
abstract class AbstractFileWriter implements FileWriter {

	private Charset charset = IOConst.DEFAULT_CHARSET;

	private OutputStream outputStream;

	private BufferedWriter writer;

	private File targetFile;

	private FileOperateType operateType;

	/**
	 * 
	 * @param operatedFile
	 * @param charset
	 * @throws FileNotFoundException
	 */
	AbstractFileWriter(File targetFile, Charset charset) throws FileNotFoundException {

		Assertion.assertNotNull("targetFile", targetFile);
		Assertion.assertNotNull("charset", charset);

		this.charset = charset;
		this.targetFile = targetFile;
		this.outputStream = IOFactory.newFileOutputStream(targetFile);
		this.writer = IOFactory.newBufferedWriter(outputStream, charset);
	}

	/**
	 * 
	 * @param outputStream
	 * @param charset
	 * @throws FileNotFoundException
	 */
	AbstractFileWriter(OutputStream outputStream, Charset charset) throws FileNotFoundException {

		Assertion.assertNotNull("outputStream", outputStream);
		Assertion.assertNotNull("charset", charset);

		this.charset = charset;
		this.outputStream = outputStream;
		this.writer = IOFactory.newBufferedWriter(outputStream, charset);
	}

	public File getTargetFile() {

		return targetFile;
	}

	public Charset getCharset() {

		return charset;
	}

	@Override
	public void close() throws IOException {

		if (writer != null) {
			writer.flush();
		} else if (outputStream != null) {
			outputStream.flush();
		}

		IOUtil.close(writer);
	}

	/**
	 * 
	 * @param array
	 * @throws IOException
	 */
	public void write(byte[] bytes) throws IOException {

		checkOperateType(FileOperateType.BYTE_WRITE);

		if (bytes != null)
			writer.write(new String(bytes, charset));
	}

	/**
	 * 
	 * @param chars
	 * @throws IOException
	 */
	public void write(char[] chars) throws IOException {

		checkOperateType(FileOperateType.CHAR_WRTIE);
		if (chars != null)
			writer.write(new String(chars));
	}

	@Override
	public void write(String text) throws IOException {

		checkOperateType(FileOperateType.LINE_WRTIE);

		if (text != null)
			writer.write(text);
	}

	@Override
	public void writeLine(String text) throws IOException {

		checkOperateType(FileOperateType.LINE_WRTIE);

		if (text != null) {
			writer.write(text);
		}
		writer.write(Characters.LINE_SEPARATOR_SYSTEM);
	}

	@Override
	public void write(InputStream is) throws IOException {

		checkOperateType(FileOperateType.BYTE_WRITE);

		FileReader reader = FileOperationFactory.newFileReader(is);
		while (reader.hasNextByte()) {
			outputStream.write(reader.readBytes());
		}
	}

	private void checkOperateType(FileOperateType type) {

		Assertion.assertNotNull("type", type);

		if (this.operateType == null) {
			this.operateType = type;
		}

		if (this.operateType != type) {
			throw new UnsupportedOperationException();
		}
	}
}
