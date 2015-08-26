package com.rms.common.xml.impl;

import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/24
 */
class XMLTransformer {

	/**
	 * 
	 * @param source
	 * @param result
	 * @param outputKeys
	 */
	static void transform(Source source, Result result, Map<String, String> outputKeys) {

		Assertion.assertNotNull("source", source);
		Assertion.assertNotNull("result", result);

		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			if (outputKeys != null && !outputKeys.isEmpty()) {
				for (String name : outputKeys.keySet()) {
					String value = outputKeys.get(name);
					if (!TextUtil.isBlank(value)) {
						transformer.setOutputProperty(name, value);
					}
				}
			}
			transformer.transform(source, result);
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
}
