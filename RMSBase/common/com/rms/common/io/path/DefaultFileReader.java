package com.rms.common.io.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */

class DefaultFileReader extends AbstractFileReader {

	/**
	 * 
	 * @param operatedFile
	 * @param charset
	 * @throws IOException
	 */
	DefaultFileReader(File operatedFile, Charset charset) throws FileNotFoundException {

		super(operatedFile, charset);
	}

	/**
	 * 
	 * @param is
	 * @param charset
	 */
	DefaultFileReader(InputStream is, Charset charset) {

		super(is, charset);
	}

}
