package com.rms.base.io.file;

import java.io.File;

import com.rms.base.exception.InvalidException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/08
 */
public final class FileUtil {

	/**
	 *
	 * @param fileName
	 * @return
	 */
	public static String getExtendsionName(String fileName) {

		int index = fileName.lastIndexOf(".");

		String extName = null;

		if (index > 0) {
			extName = fileName.substring(index);
		}

		return extName;
	}

	/**
	 *
	 * @param file
	 * @return
	 */
	public static String getExtendsionName(File file) {

		return getExtendsionName(file.getName());
	}
}
