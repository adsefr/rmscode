package com.rms.base.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.rms.base.io.constant.IOConst;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/10/25
 */
public final class IOFactory {

	public static IReader newIReader(InputStream inputStream) {

		return new DefaultReader(IOConst.DEFAULT_CHARSET, inputStream);
	}

	public static IReader newIReader(String charsetName, InputStream inputStream) {

		return new DefaultReader(Charset.forName(charsetName), inputStream);
	}

	public static IReader newIReader(Charset charset, InputStream inputStream) {

		return new DefaultReader(charset, inputStream);
	}

	public static IWriter newWriter(OutputStream outputStream) {

		return new DefaultWriter(IOConst.DEFAULT_CHARSET, outputStream);
	}

	public static IWriter newWriter(String charsetName, OutputStream outputStream) {

		return new DefaultWriter(Charset.forName(charsetName), outputStream);
	}

	public static IWriter newWriter(Charset charset, OutputStream outputStream) {

		return new DefaultWriter(charset, outputStream);
	}

	public static FileReader newFileReader(String targetFile) throws FileNotFoundException {

		return new DefaultFileReader(IOConst.DEFAULT_CHARSET, new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(String charsetName, String targetFile) throws FileNotFoundException {

		return new DefaultFileReader(Charset.forName(charsetName), new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(Charset charset, String targetFile) throws FileNotFoundException {

		return new DefaultFileReader(charset, new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(File targetFile) throws FileNotFoundException {

		return new DefaultFileReader(IOConst.DEFAULT_CHARSET, new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(String charsetName, File targetFile) throws FileNotFoundException {

		return new DefaultFileReader(Charset.forName(charsetName), new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(Charset charset, File targetFile) throws FileNotFoundException {

		return new DefaultFileReader(charset, new FileInputStream(targetFile));
	}

	public static FileReader newFileReader(FileInputStream fileInputStream) {

		return new DefaultFileReader(IOConst.DEFAULT_CHARSET, fileInputStream);
	}

	public static FileReader newFileReader(String charsetName, FileInputStream fileInputStream) {

		return new DefaultFileReader(Charset.forName(charsetName), fileInputStream);
	}

	public static FileReader newFileReader(Charset charset, FileInputStream fileInputStream) {

		return new DefaultFileReader(charset, fileInputStream);
	}

	public static FileWriter newFileWriter(String targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(IOConst.DEFAULT_CHARSET, new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(String charsetName, String targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(Charset.forName(charsetName), new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(Charset charset, String targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(charset, new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(File targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(IOConst.DEFAULT_CHARSET, new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(String charsetName, File targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(Charset.forName(charsetName), new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(Charset charset, File targetFile) throws FileNotFoundException {

		return new DefaultFileWriter(charset, new FileOutputStream(targetFile));
	}

	public static FileWriter newFileWriter(FileOutputStream fileOutputStream) {

		return new DefaultFileWriter(IOConst.DEFAULT_CHARSET, fileOutputStream);
	}

	public static FileWriter newFileWriter(String charsetName, FileOutputStream fileOutputStream) {

		return new DefaultFileWriter(Charset.forName(charsetName), fileOutputStream);
	}

	public static FileWriter newFileWriter(Charset charset, FileOutputStream fileOutputStream) {

		return new DefaultFileWriter(charset, fileOutputStream);
	}

	/**
	 *
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static FileInputStream newFileInputStream(File targetFile) throws FileNotFoundException {

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
	public static BufferedReader newBufferedReader(String operatedFile, Charset charset) throws FileNotFoundException {

		return new BufferedReader(new InputStreamReader(new FileInputStream(operatedFile), charset));
	}

	/**
	 *
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedReader newBufferedReader(Charset charset, File operatedFile) throws FileNotFoundException {

		return new BufferedReader(new InputStreamReader(new FileInputStream(operatedFile), charset));
	}

	/**
	 *
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedWriter newBufferedWriter(String operatedFile, Charset charset) throws FileNotFoundException {

		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(operatedFile), charset));
	}

	/**
	 *
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedWriter newBufferedWriter(File operatedFile, Charset charset) throws FileNotFoundException {

		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(operatedFile), charset));
	}

	/**
	 *
	 * @param targetFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedWriter newBufferedWriter(File operatedFile) throws FileNotFoundException {

		return newBufferedWriter(newFileOutputStream(operatedFile));
	}

	/**
	 *
	 * @param is
	 * @param charset
	 * @return
	 */
	public static BufferedReader newBufferedReader(InputStream is) {

		return new BufferedReader(new InputStreamReader(is));
	}

	/**
	 *
	 * @param os
	 * @param charset
	 * @return
	 */
	public static BufferedWriter newBufferedWriter(OutputStream os)
	{

		return new BufferedWriter(new OutputStreamWriter(os));
	}

}
