package com.rms.base.gen.javaclass.model;

import java.util.HashSet;
import java.util.Set;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ModifierInfo<T> {

	private final Set<T> modifierTypeCollection = new HashSet<T>();

	public ModifierInfo() {

	}

	@SuppressWarnings("unchecked")
	public void add(T... modifierTypeArray) {

		Assertion.assertNotNull("modifierTypeArray", modifierTypeArray);

		for (T modifierType : modifierTypeArray) {
			if (modifierType != null) {
				modifierTypeCollection.add(modifierType);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void remove(T... modifierTypeArray) {

		Assertion.assertNotNull("modifierTypeArray", modifierTypeArray);

		for (T modifierType : modifierTypeArray) {
			if (modifierType != null) {
				modifierTypeCollection.remove(modifierType);
			}
		}
	}

	/**
	 * @return modifierTypeCollection
	 */
	public Set<T> getModifierTypeCollection() {

		return modifierTypeCollection;
	}

	public int count() {

		return modifierTypeCollection.size();
	}

	public void clear() {

		modifierTypeCollection.clear();
	}

	public boolean isEmpty() {

		return modifierTypeCollection.isEmpty();
	}
}
