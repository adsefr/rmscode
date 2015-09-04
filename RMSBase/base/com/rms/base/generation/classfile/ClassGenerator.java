package com.rms.base.generation.classfile;

import com.rms.base.generation.Generator;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public interface ClassGenerator extends Generator {

	public String packageDefine(ClassInfo classInfo);

	public String annotation(ClassInfo classInfo);

	public String classModify(ClassInfo classInfo);

	public String className(ClassInfo classInfo);
}
