package com.rms.base.io;

import java.io.IOException;

import com.rms.base.io.constant.IOConst;

/**
 *
 * @author ri.meisei
 * @since 2015/09/18
 */
abstract class AbstractReader implements IReader {

	private int byteBufferedLength = IOConst.DEFAULT_BUFFER_SIZE;

	private int charBufferedLength = IOConst.DEFAULT_BUFFER_SIZE;

	private byte[] byteBuffer = new byte[byteBufferedLength];

	private int byteBufferLength = 0;

	private int offSetByteBuffer = 0;

	private char[] charBuffer = new char[charBufferedLength];

	private int charBufferLength = 0;

	private int offSetCharBuffer = 0;

	protected AbstractReader() {

	}

	@Override
	public final boolean hasBytes() throws IOException {

		if (offSetByteBuffer >= byteBufferLength) {
			byteBufferLength = getInputStream().read(byteBuffer);
			offSetByteBuffer = 0;
		}

		return (byteBufferLength != -1);
	}

	@Override
	public final byte readByte() throws IOException {

		if (offSetByteBuffer < byteBufferLength) {
			return byteBuffer[offSetByteBuffer++];
		}

		throw new IndexOutOfBoundsException();
	}

	@Override
	public final byte[] readBytes() throws IOException {

		return readBytes(byteBufferLength - offSetByteBuffer);
	}

	@Override
	public final int readBytes(byte[] array) throws IOException {

		int length = Math.min(array.length, byteBufferLength - offSetByteBuffer);

		System.arraycopy(byteBuffer, offSetByteBuffer, array, 0, length);

		offSetByteBuffer += length;

		return length;
	}

	@Override
	public final byte[] readBytes(int readCharLength) throws IOException {

		int length = Math.min(readCharLength, byteBufferLength - offSetByteBuffer);

		byte[] array = new byte[length];
		System.arraycopy(byteBuffer, offSetByteBuffer, array, 0, length);

		offSetByteBuffer += length;

		return array;
	}

	@Override
	public final boolean hasChars() throws IOException {

		if (offSetCharBuffer >= charBufferLength) {
			charBufferLength = getReader().read(charBuffer);
			offSetByteBuffer = 0;
		}

		return (charBufferLength != -1);
	}

	@Override
	public final char readChar() throws IOException {

		if (offSetCharBuffer < charBufferLength) {
			return charBuffer[offSetCharBuffer++];
		}

		throw new IndexOutOfBoundsException();
	}

	@Override
	public final char[] readChars() throws IOException {

		return readChars(charBufferLength - offSetCharBuffer);
	}

	@Override
	public final int readChars(char[] array) throws IOException {

		int length = Math.min(array.length, charBufferLength - offSetCharBuffer);

		System.arraycopy(charBuffer, offSetCharBuffer, array, 0, length);

		offSetCharBuffer += length;

		return length;
	}

	@Override
	public final char[] readChars(int readCharLength) throws IOException {

		int length = Math.min(readCharLength, charBufferLength - offSetCharBuffer);

		char[] array = new char[length];
		System.arraycopy(charBuffer, offSetCharBuffer, array, 0, length);

		offSetCharBuffer = charBufferLength;

		return array;
	}
}
