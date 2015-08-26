package com.rms.common.io.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

/**
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
class DefaultFileWriter extends AbstractFileWriter {

	/**
	 * 
	 * @param operatedFile
	 * @param charsetName
	 * @throws FileNotFoundException
	 */
	DefaultFileWriter(File operatedFile, Charset charset) throws FileNotFoundException {

		super(operatedFile, charset);
	}
}
