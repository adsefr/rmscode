package com.rms.base.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
public class IOFactory {

	/**
	 * 
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream newFileInputStream(File targetFile) throws FileNotFoundException {

		return new FileInputStream(targetFile);
	}

	/**
	 * 
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static OutputStream newFileOutputStream(File targetFile) throws FileNotFoundException {

		return new FileOutputStream(targetFile);
	}

	/**
	 * 
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedReader newBufferedReader(File operatedFile, Charset charset) throws FileNotFoundException {

		InputStream is = newFileInputStream(operatedFile);

		try {
			return newBufferedReader(is, charset);
		} catch (RuntimeException e) {
			try {
				IOUtil.close(is);
			} catch (IOException e1) {
				e1.printStackTrace();// TODO
			}
			throw e;
		}
	}

	/**
	 * 
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedWriter newBufferedWriter(File operatedFile, Charset charset) throws FileNotFoundException {

		OutputStream os = newFileOutputStream(operatedFile);

		try {
			return newBufferedWriter(os, charset);
		} catch (RuntimeException e) {
			try {
				IOUtil.close(os);
			} catch (IOException e1) {
				e1.printStackTrace();// TODO
			}
			throw e;
		}
	}

	/**
	 * 
	 * @param is
	 * @param charset
	 * @return
	 */
	public static BufferedReader newBufferedReader(InputStream is, Charset charset) {

		return new BufferedReader(new InputStreamReader(is, charset));
	}

	/**
	 * 
	 * @param os
	 * @param charset
	 * @return
	 */
	public static BufferedWriter newBufferedWriter(OutputStream os, Charset charset) {

		return new BufferedWriter(new OutputStreamWriter(os, charset));
	}

}
