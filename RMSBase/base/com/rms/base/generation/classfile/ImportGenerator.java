package com.rms.base.generation.classfile;

import java.util.List;

import com.rms.base.generation.Generator;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public interface ImportGenerator extends Generator {

	public List<String> importClass(ImportInfo importInfo);
}
