package com.rms.base.generation.classfile;

import com.rms.base.generation.Generator;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public interface FieldGenerator extends Generator {

	public String annotation(FieldInfo fieldInfo);

	public String fieldModify(FieldInfo fieldInfo);

	public String fieldName(FieldInfo fieldInfo);

	public String fieldValue(FieldInfo fieldInfo);
}
