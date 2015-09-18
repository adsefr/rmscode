package com.rms.base.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.io.constant.IOConst;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/10/25
 */

abstract class AbstractFileReader extends AbstractReader implements FileReader {

	private String lineSeparator = Characters.LINE_SEPARATOR_SYSTEM;

	private int charBufferedLength = IOConst.DEFAULT_BUFFER_SIZE;

	private StringBuilder lineBuffer = new StringBuilder(charBufferedLength);

	private int offSetLineSeparator = 0;

	private List<Integer> lineSeparatorIndexList = new ArrayList<>();

	/**
	 * @param inputStream
	 * @param charset
	 */
	protected AbstractFileReader() {

		super();
	}

	@Override
	public final boolean hasLines() throws IOException {

		if (offSetLineSeparator >= lineSeparatorIndexList.size()) {

			lineBuffer.setLength(0);
			lineSeparatorIndexList.clear();
			offSetLineSeparator = 0;

			String line = null;
			while ((line = getReader().readLine()) != null) {
				lineBuffer.append(line).append(lineSeparator);
				lineSeparatorIndexList.add(lineBuffer.length() - 1);
				if ((lineBuffer.length() - lineSeparatorIndexList.size()) >= charBufferedLength) {
					break;
				}
			}
		}

		return (lineBuffer.length() > 0);
	}

	@Override
	public final String readLine() throws IOException {

		int from = lineSeparatorIndexList.get(offSetLineSeparator);
		int to = lineSeparatorIndexList.get(offSetLineSeparator + 1);
		offSetLineSeparator += 1;

		String line = lineBuffer.substring(from, to);

		return line;
	}

	@Override
	public final List<String> readLines() throws IOException {

		List<String> lineList = new ArrayList<>();

		readLines(lineList);

		return lineList;
	}

	@Override
	public final List<String> readLines(int readLineCnt) throws IOException {

		List<String> lines = new ArrayList<>();

		for (Integer index : lineSeparatorIndexList) {
			int from = lineSeparatorIndexList.get(index);
			int to = lineSeparatorIndexList.get(index + 1);
			offSetLineSeparator += 1;
			String line = lineBuffer.substring(from, to);
			lines.add(line);
		}

		return lines;
	}

	@Override
	public final int readLines(String[] lineArray) throws IOException {

		int lineIndex = 0;
		int length = lineArray.length;

		for (Integer index : lineSeparatorIndexList) {
			int from = lineSeparatorIndexList.get(index);
			int to = lineSeparatorIndexList.get(index + 1);
			offSetLineSeparator += 1;
			lineArray[lineIndex++] = lineBuffer.substring(from, to);
			if ((lineIndex + 1) >= length) {
				break;
			}
		}

		return lineIndex;
	}

	@Override
	public final int readLines(List<String> lineList) throws IOException {

		int lineCnt = 0;

		for (Integer index : lineSeparatorIndexList) {
			int from = lineSeparatorIndexList.get(index);
			int to = lineSeparatorIndexList.get(index + 1);
			offSetLineSeparator += 1;
			String line = lineBuffer.substring(from, to);

			lineList.add(line);
			lineCnt++;
		}

		return lineCnt;
	}
}
