package com.rms.base.gen.javaclass.generator;

import com.rms.base.gen.javaclass.model.ModifierInfo;

/**
 *
 * @author ri.meisei
 * @since 2015/09/17
 */
public class ModifierGenerator extends BaseGenerator {

	private ModifierInfo<?> modifierInfo;

	public ModifierGenerator() {

		super();
	}

	/**
	 * @param modifierInfo
	 */
	public ModifierGenerator(ModifierInfo<?> modifierInfo) {

		super();

		this.modifierInfo = modifierInfo;
	}

	/**
	 * @param modifierInfo
	 *            セットする modifierInfo
	 */
	public void setModifierInfo(ModifierInfo<?> modifierInfo) {

		this.modifierInfo = modifierInfo;
	}

	@Override
	public void generate() {

		clearBuffer();
		int count = modifierInfo.count();
		for (Object modifier : modifierInfo.getModifierTypeCollection()) {
			append(((Enum<?>) modifier).name().toLowerCase());
			if (count > 1) {
				append(" ");
				count--;
			}
		}
	}

}
