package com.rms.common.poi.visio;

import java.io.File;

import org.apache.poi.hdgf.HDGFDiagram;
import org.apache.poi.hdgf.streams.Stream;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;

import com.rms.base.logging.Logger;

public final class VSDDumper2 {

	private final static Logger logger = Logger.getLogger(VSDDumper2.class);

	public static void main(String[] args) throws Exception {

		String fileName = "C:/document/作業内容/【20151013_標準データ及びERD確認】/【WMS】【SD】概念ERD.vsd";
		HDGFDiagram hdgf = new HDGFDiagram(
				new NPOIFSFileSystem(new File(fileName)));

		System.out.println("Opened " + fileName);
		System.out.println("The document claims a size of " +
				hdgf.getDocumentSize() + "   (" +
				Long.toHexString(hdgf.getDocumentSize()) + ")");

		dumpStream(hdgf);
	}

	public static void dumpStream(HDGFDiagram hdgf) {

		Stream[] streams = hdgf.getTopLevelStreams();
		logger.debug(String.valueOf(streams.length));
	}
}
