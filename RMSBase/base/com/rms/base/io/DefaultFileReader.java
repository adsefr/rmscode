package com.rms.base.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.rms.base.io.util.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
class DefaultFileReader extends AbstractFileReader {

	private Charset charset;

	private FileInputStream fileInputStream;

	private BufferedReader reader;

	/**
	 * @param charset
	 * @param inputStream
	 */
	DefaultFileReader(Charset charset, FileInputStream fileInputStream) {

		super();

		this.charset = charset;
		this.fileInputStream = fileInputStream;
		this.reader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
	}

	@Override
	public Charset getCharset() {

		return charset;
	}

	@Override
	public FileInputStream getInputStream() {

		return fileInputStream;
	}

	@Override
	public BufferedReader getReader() {

		return reader;
	}

	@Override
	public void close() throws IOException {

		IOUtil.close(fileInputStream);
	}
}
