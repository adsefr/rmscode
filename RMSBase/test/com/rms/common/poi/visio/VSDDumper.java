package com.rms.common.poi.visio;

import java.io.File;

import org.apache.poi.hdgf.HDGFDiagram;
import org.apache.poi.hdgf.chunks.Chunk;
import org.apache.poi.hdgf.chunks.Chunk.Command;
import org.apache.poi.hdgf.pointers.Pointer;
import org.apache.poi.hdgf.streams.ChunkStream;
import org.apache.poi.hdgf.streams.PointerContainingStream;
import org.apache.poi.hdgf.streams.Stream;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;

public final class VSDDumper {
	public static void main(String[] args) throws Exception {

		String fileName = "C:/document/作業内容/【20151013_標準データ及びERD確認】/【WMS】【SD】概念ERD.vsd";
		HDGFDiagram hdgf = new HDGFDiagram(
				new NPOIFSFileSystem(new File(fileName)));

		System.out.println("Opened " + fileName);
		System.out.println("The document claims a size of " +
				hdgf.getDocumentSize() + "   (" +
				Long.toHexString(hdgf.getDocumentSize()) + ")");
		System.out.println();

		dumpStream(hdgf.getTrailerStream(), 0);
	}

	public static void dumpStream(Stream stream, int indent) {
		String ind = "";
		for (int i = 0; i < indent; i++) {
			ind += "    ";
		}
		String ind2 = ind + "    ";
		String ind3 = ind2 + "    ";

		Pointer ptr = stream.getPointer();
		System.out.println(ind + "Stream at\t" + ptr.getOffset() +
				" - " + Integer.toHexString(ptr.getOffset()));
		System.out.println(ind + "  Type is\t" + ptr.getType() +
				" - " + Integer.toHexString(ptr.getType()));
		System.out.println(ind + "  Format is\t" + ptr.getFormat() +
				" - " + Integer.toHexString(ptr.getFormat()));
		System.out.println(ind + "  Length is\t" + ptr.getLength() +
				" - " + Integer.toHexString(ptr.getLength()));
		if (ptr.destinationCompressed()) {
			int decompLen = stream._getContentsLength();
			System.out.println(ind + "  DC.Length is\t" + decompLen +
					" - " + Integer.toHexString(decompLen));
		}
		System.out.println(ind + "  Compressed is\t" + ptr.destinationCompressed());
		System.out.println(ind + "  Stream is\t" + stream.getClass().getName());

		byte[] db = stream._getStore()._getContents();
		String ds = "";
		if (db.length >= 8) {
			for (int i = 0; i < 8; i++) {
				if (i > 0)
					ds += ", ";
				ds += db[i];
			}
		}
		System.out.println(ind + "  First few bytes are\t" + ds);

		if (stream instanceof PointerContainingStream) {
			PointerContainingStream pcs = (PointerContainingStream) stream;
			System.out.println(ind + "  Has " +
					pcs.getPointedToStreams().length + " children:");

			for (int i = 0; i < pcs.getPointedToStreams().length; i++) {
				dumpStream(pcs.getPointedToStreams()[i], (indent + 1));
			}
		}
		if (stream instanceof ChunkStream) {
			ChunkStream cs = (ChunkStream) stream;
			System.out.println(ind + "  Has " + cs.getChunks().length +
					" chunks:");

			for (int i = 0; i < cs.getChunks().length; i++) {
				Chunk chunk = cs.getChunks()[i];
				System.out.println(ind2 + "" + chunk.getName());
				System.out.println(ind2 + chunk.getHeader().getId());
				System.out.println(ind2 + "  Length is " + chunk._getContents().length + " (" + Integer.toHexString(chunk._getContents().length) + ")");
				System.out.println(ind2 + "  OD Size is " + chunk.getOnDiskSize() + " (" + Integer.toHexString(chunk.getOnDiskSize()) + ")");
				System.out.println(ind2 + "  T / S is " + chunk.getTrailer() + " / " + chunk.getSeparator());
				System.out.println(ind2 + "  Holds " + chunk.getCommands().length + " commands");
				for (int j = 0; j < chunk.getCommands().length; j++) {
					Command command = chunk.getCommands()[j];
					System.out.println(ind3 + "" +
							command.getDefinition().getName() +
							" " + command.getValue());
				}
			}
		}
	}
}
