package com.rms.common.io.path;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.common.io.IOUtil;
import com.rms.common.io.file.FileConst.FileType;

public final class FileHelper {

	/**
	 * 指定ディレクトリの下のファイルを取得する
	 * 
	 * @param dirPath
	 *            ディレクトリ
	 * @param regex
	 *            この文字列との一致を判定する正規表現
	 * @param type
	 *            取得ファイルのタイプ
	 * @return　取得ファイルリスト
	 */
	public static List<File> getFileList(final String dirPath, final String regex, final FileType type) {

		return getFileList(new File(dirPath), regex, type, false);
	}

	/**
	 * 指定ディレクトリの下のファイルを取得する
	 * 
	 * @param dirPath
	 *            ディレクトリ
	 * @param regex
	 *            この文字列との一致を判定する正規表現
	 * @param type
	 *            取得ファイルのタイプ
	 * @return　取得ファイルリスト
	 */
	public static List<File> getFileList(final File targetDir, final String regex, final FileType type) {

		return getFileList(targetDir, regex, type, false);
	}

	/**
	 * 指定ディレクトリの下の指定形式のファイルを取得する
	 * 
	 * @param dirPath
	 *            ディレクトリ
	 * @param regex
	 *            この文字列との一致を判定する正規表現
	 * @param type
	 *            取得ファイルのタイプ
	 * @param contains
	 *            サブディレクトリが対象になるか表示フラグ
	 * @return　取得ファイルリスト
	 */
	public static List<File> getFileList(final String dirPath, final String regex, final FileType type, boolean contains) {

		return getFileList(new File(dirPath), regex, type, contains);
	}

	/**
	 * 指定ディレクトリの下の指定形式のファイルを取得する
	 * 
	 * @param targetDir
	 *            ディレクトリ
	 * @param regex
	 *            この文字列との一致を判定する正規表現
	 * @param type
	 *            取得ファイルのタイプ
	 * @param contains
	 *            サブディレクトリが対象になるか表示フラグ
	 * @return　取得ファイルリスト
	 */
	public static List<File> getFileList(final File targetDir, final String regex, final FileType type, boolean contains) {

		List<File> targetFileList = new ArrayList<File>();
		List<File> fileList = Arrays.asList(targetDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {

				if (FileType.FILE == type && file.isDirectory()) {
					return false;
				}

				if (FileType.DIRECTORY == type && file.isFile()) {
					return false;
				}

				if (regex != null) {
					return file.getName().matches(regex);
				}

				return true;
			}
		}));

		targetFileList.addAll(fileList);

		if (contains) {
			for (File dirFile : getFileList(targetDir, null, FileType.DIRECTORY)) {
				List<File> subDirFileList = getFileList(dirFile, regex, type, contains);
				targetFileList.addAll(subDirFileList);
			}
		}

		return targetFileList;
	}

	/**
	 * 指定フォルダの下のファイルをすべて削除する
	 * 
	 * @param dirPath
	 * @param contains
	 */
	public static void delete(String dirPath, boolean contains) {

		delete(new File(dirPath), contains);
	}

	/**
	 * 指定フォルダの下のファイルをすべて削除する
	 * 
	 * @param dirFile
	 * @param contains
	 */
	public static void delete(File dirFile, boolean contains) {

		for (File element : dirFile.listFiles()) {
			if (element.isFile()) {
				element.delete();
			} else if (contains && element.isDirectory()) {
				delete(element, contains);
				element.delete();
			}
		}
	}

	/**
	 * ファイルの拡張子を変更する
	 * 
	 * @param fileName
	 *            ファイル名
	 * @param extension
	 *            変更後拡張子
	 * @return
	 */
	public static String changeExtension(String fileName, String extension) {

		String chaged = fileName;

		int index = fileName.lastIndexOf(Characters.DOT);
		if (index > 0) {
			chaged = fileName.substring(0, index) + extension;
		}

		return chaged;
	}

	/**
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static byte[] readAllBytes(File targetFile, String encoding) throws IOException {

		return readAll(targetFile, encoding).getBytes(Charset.forName(encoding));
	}

	/**
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static char[] readAllChars(File targetFile, String encoding) throws IOException {

		return readAll(targetFile, encoding).toCharArray();
	}

	/**
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readAll(File targetFile, String encoding) throws IOException {

		FileReader reader = FileOperationFactory.newFileReader(targetFile, encoding);

		StringBuilder builder = new StringBuilder();

		try {
			while (reader.hasNextChar()) {
				builder.append(reader.readChars());
			}
		} finally {
			IOUtil.close(reader);
		}

		return builder.toString();
	}

	/**
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static List<String> readAllLine(File targetFile, String encoding) throws IOException {

		FileReader reader = FileOperationFactory.newFileReader(targetFile, encoding);

		List<String> lines = new ArrayList<String>();

		try {
			while (reader.hasNextLine()) {
				lines.add(reader.readLine());
			}
		} finally {
			IOUtil.close(reader);
		}

		return lines;
	}
}
