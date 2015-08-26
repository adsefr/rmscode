package com.rms.common.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public final class IOUtil {

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
