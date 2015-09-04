package com.rms.base.generation.classfile;

import com.rms.base.generation.Generator;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public interface PackageGenerator extends Generator {

	public String packageName(MethodInfo methodInfo);
}
