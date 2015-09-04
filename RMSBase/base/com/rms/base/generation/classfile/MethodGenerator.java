package com.rms.base.generation.classfile;

import com.rms.base.generation.Generator;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public interface MethodGenerator extends Generator {

	public String annotation(MethodInfo methodInfo);

	public String methodModify(MethodInfo methodInfo);

	public String methodName(MethodInfo methodInfo);

	public String methodParameter(MethodInfo methodInfo);

	public String methodException(MethodInfo methodInfo);

	public String methodBody(MethodInfo methodInfo);
}
