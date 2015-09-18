package com.rms.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import com.rms.base.io.util.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/18
 */
class DefaultReader extends AbstractReader {

	private Charset charset;

	private InputStream inputStream;

	private Reader reader;

	/**
	 * @param charset
	 * @param inputStream
	 */
	DefaultReader(Charset charset, InputStream inputStream) {

		super();

		this.charset = charset;
		this.inputStream = inputStream;
		this.reader = new InputStreamReader(inputStream, charset);
	}

	@Override
	public Charset getCharset() {

		return charset;
	}

	@Override
	public InputStream getInputStream() {

		return inputStream;
	}

	@Override
	public Reader getReader() {

		return reader;
	}

	@Override
	public void close() throws IOException {

		IOUtil.close(inputStream);

	}
}
