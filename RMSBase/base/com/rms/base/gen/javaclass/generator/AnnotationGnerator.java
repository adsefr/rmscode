package com.rms.base.gen.javaclass.generator;

import com.rms.base.gen.javaclass.model.AnnotationInfo;

/**
 *
 * @author ri.meisei
 * @since 2015/09/17
 */
public class AnnotationGnerator extends BaseGenerator {

	private AnnotationInfo annotationInfo;

	public AnnotationGnerator() {

		super();
	}

	/**
	 * @param annotationInfo
	 */
	public AnnotationGnerator(AnnotationInfo annotationInfo) {

		super();

		this.annotationInfo = annotationInfo;
	}

	/**
	 * @param annotationInfo
	 *            セットする annotationInfo
	 */
	public void setAnnotationInfo(AnnotationInfo annotationInfo) {

		this.annotationInfo = annotationInfo;
	}

	@Override
	public void generate() {

	}

}
