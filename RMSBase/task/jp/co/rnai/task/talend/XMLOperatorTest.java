package jp.co.rnai.task.talend;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class XMLOperatorTest {

	private static final String CHARSET = "UTF-8";

	public static void main(String[] args) throws Exception {

		testGetValueExceptTags();
	}

	private static void testGetValueExceptTags() throws XPathExpressionException, SAXException, IOException,
			ParserConfigurationException, TransformerException {

		String content = "<ce:simple-para xmlns:ce=\"http://www.elsevier.com/xml/common/dtd\" view=\"all\">The electron-donating strains in anode suspension of microbial electrolysis cell (MEC) have not been probed. This work for the first time isolated from the anode suspension in a double-chamber MEC a Fe(III)-reducing strain W7, which is a Gram-negative, short rod, polar flagellum, non-spore-forming bacterium. Physiological–biochemical characterization and 16S rRNA gene sequence analysis suggested that this strain is a typical fermentative bacterium and is designated to the strain <ce:italic>Bacteroides</ce:italic> sp. W7 (GeneBank accession no. <ce:inter-ref xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"ncbi-n:FJ862827\" xlink:type=\"simple\">FJ862827</ce:inter-ref>). The strain W7 could reduce Fe(III) to form 8.5 mmol L<ce:sup loc=\"post\">−1</ce:sup> of Fe(II) in 39 h cultivation. Moreover, this strain can transfer electrons to Fe(III) during its growth by adopting glucose, yeast extract and sodium lactate as carbon source. The presence of this Fe(III)-reducing fermentative bacterium suggests the possible contribution of suspended organisms to the efficiency of MEC.</ce:simple-para>";
		content = "<ce:ArticleTitle xmlns:ce=\"http://www.elsevier.com/xml/common/dtd\">The Low‐Temperature Cosintering of<ce:p> Cobalt<ce:sub>sub<ce:inf loc='loc'>inf</ce:inf></ce:sub>Ferrite<ce:sup>sup<ce:inf>inf</ce:inf></ce:sup><ce:b>bf<ce:sup>sup</ce:sup><ce:b>b</ce:b><ce:i>i</ce:i><ce:u>u</ce:u></ce:b><ce:i>i</ce:i><ce:u>u</ce:u></ce:p> and Lead Zirconate Titanate Ceramic Composites</ce:ArticleTitle>";

		String trim = XMLOperator.trimTag(content);
		System.out.println(trim);

	}
}
