package com.rms.base.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import com.rms.base.io.util.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/18
 */
class DefaultWriter extends AbstractWriter {

	private Charset charset;

	private OutputStream outputStream;

	private Writer writer;

	/**
	 * @param outputStream
	 * @param charset
	 */
	DefaultWriter(Charset charset, OutputStream outputStream) {

		super();

		this.charset = charset;
		this.outputStream = outputStream;
		this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, charset));
	}

	@Override
	public Charset getCharset() {

		return charset;
	}

	@Override
	public OutputStream getOutputStream() {

		return outputStream;
	}

	@Override
	public Writer getWriter() {

		return writer;
	}

	@Override
	public void close() throws IOException {

		IOUtil.close(outputStream);
	}
}
