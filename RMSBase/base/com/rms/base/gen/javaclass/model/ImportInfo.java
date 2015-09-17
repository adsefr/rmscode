package com.rms.base.gen.javaclass.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/14
 */
public class ImportInfo extends BaseInfo {

	private final Set<String> classNameCollection = new HashSet<>();

	ImportInfo() {

	}

	public void addImportClass(Collection<String> classNameCollection) {

		Assertion.assertNotNull("classNameCollection", classNameCollection);

		classNameCollection.stream().forEach(e -> {
			this.classNameCollection.add(e);
		});
	}

	/**
	 * @return classNameCollection
	 */
	public Set<String> getClassNameCollection() {

		return classNameCollection;
	}

	public void clear() {

		classNameCollection.clear();
	}
}
