package com.rms.base.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.rms.base.io.util.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
class DefaultFileWriter extends AbstractFileWriter {

	private Charset charset;

	private FileOutputStream fileOutputStream;

	private BufferedWriter writer;

	/**
	 * @param fileOutputStream
	 * @param charset
	 */
	DefaultFileWriter(Charset charset, FileOutputStream fileOutputStream) {

		super();

		this.charset = charset;
		this.fileOutputStream = fileOutputStream;
		this.writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, charset));
	}

	@Override
	public Charset getCharset() {

		return charset;
	}

	@Override
	public FileOutputStream getOutputStream() {

		return fileOutputStream;
	}

	@Override
	public BufferedWriter getWriter() {

		return writer;
	}

	@Override
	public void close() throws IOException {

		IOUtil.close(fileOutputStream);
	}
}
