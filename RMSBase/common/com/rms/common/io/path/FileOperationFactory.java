package com.rms.common.io.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
public final class FileOperationFactory {

	public static FileReader newFileReader(InputStream is) {

		return newFileReader(is);
	}

	public static FileReader newFileReader(String filePath, String charsetName) throws FileNotFoundException {

		return newFileReader(new File(filePath), Charset.forName(charsetName));
	}

	public static FileReader newFileReader(String filePath, Charset charset) throws FileNotFoundException {

		return newFileReader(new File(filePath), charset);
	}

	public static FileReader newFileReader(File targetFile, String charsetName) throws FileNotFoundException {

		return newFileReader(targetFile, Charset.forName(charsetName));
	}

	public static FileReader newFileReader(File targetFile, Charset charset) throws FileNotFoundException {

		return new DefaultFileReader(targetFile, charset);
	}

	public static FileWriter newFileWriter(String filePath, String charsetName) throws FileNotFoundException {

		return newFileWriter(new File(filePath), Charset.forName(charsetName));
	}

	public static FileWriter newFileWriter(String filePath, Charset charset) throws FileNotFoundException {

		return newFileWriter(new File(filePath), charset);
	}

	public static FileWriter newFileWriter(File targetFile, String charsetName) throws FileNotFoundException {

		return newFileWriter(targetFile, Charset.forName(charsetName));
	}

	public static FileWriter newFileWriter(File targetFile, Charset charset) throws FileNotFoundException {

		return new DefaultFileWriter(targetFile, charset);
	}

}
