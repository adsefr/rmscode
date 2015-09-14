package com.rms.base.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public final class IOUtil {

	public static boolean isDirectory(File file) {

		return file != null && file.isDirectory();
	}

	public static boolean isFile(File file) {

		return file != null && file.isFile();
	}

	public static void write(File file, String content) throws IOException {

		BufferedWriter bufferedWriter = null;
		try {

			bufferedWriter = Files.newBufferedWriter(Paths.get(file.toString()));
			bufferedWriter.write(content);
		} finally {
			close(bufferedWriter);
		}
	}

	/**
	 *
	 * @param closeable
	 * @throws IOException
	 */
	public static void close(Closeable closeable) throws IOException {

		if (closeable != null) {
			closeable.close();
		}
	}
}
