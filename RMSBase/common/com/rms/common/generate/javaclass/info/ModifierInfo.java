package com.rms.common.generate.javaclass.info;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/03/20
 */
public class ModifierInfo {

	private final TargetType targetType;

	private final Set<ModifierType> modifierTypeCollection = new HashSet<ModifierType>();

	ModifierInfo(TargetType targetType) {

		Assertion.assertNotNull("targetType", targetType);
		this.targetType = targetType;
	}

	ModifierInfo(TargetType targetType, ModifierType... modifierTypeArray) {

		this(targetType);
		add(modifierTypeArray);
	}

	ModifierInfo(TargetType targetType, List<ModifierType> modifierTypeList) {

		this(targetType);
		add(modifierTypeList);
	}

	public void add(ModifierType... modifierTypeArray) {

		Assertion.assertNotNull("modifierTypeArray", modifierTypeArray);

		for (ModifierType modifierType : modifierTypeArray) {
			if (modifierType != null) {
				modifierTypeCollection.add(modifierType);
			}
		}
	}

	public void add(List<ModifierType> modifierTypeList) {

		Assertion.assertNotNull("modifierTypeList", modifierTypeList);

		for (ModifierType modifierType : modifierTypeList) {
			if (modifierType != null) {
				modifierTypeCollection.add(modifierType);
			}
		}
	}

	public void remove(ModifierType... modifierTypeArray) {

		Assertion.assertNotNull("modifierTypeArray", modifierTypeArray);

		for (ModifierType modifierType : modifierTypeArray) {
			if (modifierType != null) {
				modifierTypeCollection.remove(modifierType);
			}
		}
	}

	public void remove(List<ModifierType> modifierTypeList) {

		Assertion.assertNotNull("modifierTypeList", modifierTypeList);

		for (ModifierType modifierType : modifierTypeList) {
			if (modifierType != null) {
				modifierTypeCollection.remove(modifierType);
			}
		}
	}

	public void clear() {

		modifierTypeCollection.clear();
	}

	public boolean isEmpty() {

		return modifierTypeCollection.isEmpty();
	}

	@Override
	public String toString() {

		StringBuilder sbBuilder = new StringBuilder();
		if (targetType == TargetType.CLASS) {
			// private static final int CLASS_MODIFIERS =
			// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
			// Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL |
			// Modifier.STRICT;
			if (modifierTypeCollection.contains(ModifierType.PUBLIC)) {
				sbBuilder.append(ModifierType.PUBLIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PROTECTED)) {
				sbBuilder.append(ModifierType.PROTECTED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PRIVATE)) {
				sbBuilder.append(ModifierType.PRIVATE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.ABSTRACT)) {
				sbBuilder.append(ModifierType.ABSTRACT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.FINAL)) {
				sbBuilder.append(ModifierType.FINAL);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STATIC)) {
				sbBuilder.append(ModifierType.STATIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STRICT)) {
				sbBuilder.append(ModifierType.STRICT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
		} else if (targetType == TargetType.INTERFACE) {
			// /**
			// * See JLSv3 section 9.1.1.
			// */
			// private static final int INTERFACE_MODIFIERS =
			// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
			// Modifier.ABSTRACT | Modifier.STATIC | Modifier.STRICT;
			//
			//
			if (modifierTypeCollection.contains(ModifierType.PUBLIC)) {
				sbBuilder.append(ModifierType.PUBLIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PROTECTED)) {
				sbBuilder.append(ModifierType.PROTECTED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PRIVATE)) {
				sbBuilder.append(ModifierType.PRIVATE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.ABSTRACT)) {
				sbBuilder.append(ModifierType.ABSTRACT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STATIC)) {
				sbBuilder.append(ModifierType.STATIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STRICT)) {
				sbBuilder.append(ModifierType.STRICT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
		} else if (targetType == TargetType.FIELD) {
			// /**
			// * See JLSv3 section 8.3.1.
			// */
			// private static final int FIELD_MODIFIERS =
			// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
			// Modifier.STATIC | Modifier.FINAL | Modifier.TRANSIENT |
			// Modifier.VOLATILE;

			if (modifierTypeCollection.contains(ModifierType.PUBLIC)) {
				sbBuilder.append(ModifierType.PUBLIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PROTECTED)) {
				sbBuilder.append(ModifierType.PROTECTED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PRIVATE)) {
				sbBuilder.append(ModifierType.PRIVATE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.FINAL)) {
				sbBuilder.append(ModifierType.FINAL);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STATIC)) {
				sbBuilder.append(ModifierType.STATIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.TRANSIENT)) {
				sbBuilder.append(ModifierType.TRANSIENT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.VOLATILE)) {
				sbBuilder.append(ModifierType.VOLATILE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
		} else if (targetType == TargetType.CONSTRUCTOR) {
			// /**
			// * See JLSv3 section 8.8.3.
			// */
			// private static final int CONSTRUCTOR_MODIFIERS =
			// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

			if (modifierTypeCollection.contains(ModifierType.PUBLIC)) {
				sbBuilder.append(ModifierType.PUBLIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PROTECTED)) {
				sbBuilder.append(ModifierType.PROTECTED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PRIVATE)) {
				sbBuilder.append(ModifierType.PRIVATE);
				sbBuilder.append(Characters.SPACE_HALF);
			}

		} else if (targetType == TargetType.METHOD) {
			//
			// /**
			// * See JLSv3 section 8.4.3.
			// */
			// private static final int METHOD_MODIFIERS =
			// Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE |
			// Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL |
			// Modifier.SYNCHRONIZED | Modifier.NATIVE | Modifier.STRICT;
			//
			if (modifierTypeCollection.contains(ModifierType.PUBLIC)) {
				sbBuilder.append(ModifierType.PUBLIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PROTECTED)) {
				sbBuilder.append(ModifierType.PROTECTED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.PRIVATE)) {
				sbBuilder.append(ModifierType.PRIVATE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.ABSTRACT)) {
				sbBuilder.append(ModifierType.ABSTRACT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.FINAL)) {
				sbBuilder.append(ModifierType.FINAL);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STATIC)) {
				sbBuilder.append(ModifierType.STATIC);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.SYNCHRONIZED)) {
				sbBuilder.append(ModifierType.SYNCHRONIZED);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.NATIVE)) {
				sbBuilder.append(ModifierType.NATIVE);
				sbBuilder.append(Characters.SPACE_HALF);
			}
			if (modifierTypeCollection.contains(ModifierType.STRICT)) {
				sbBuilder.append(ModifierType.STRICT);
				sbBuilder.append(Characters.SPACE_HALF);
			}
		}

		return sbBuilder.toString().trim().toLowerCase();
	}
}
