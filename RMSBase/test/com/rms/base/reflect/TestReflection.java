package com.rms.base.reflect;

import org.junit.Test;

public class TestReflection {

	@Test
	public void invoke() {

		try {
			Reflection.invoke(new A(), "method1", new Class<?>[] { Object[].class }, (Object) null);
			Reflection.invoke(new A(), "method1", new Class<?>[] { Object[].class }, (Object) new Object[] { "1" });
			Reflection.invoke(new A(), "method1", new Class<?>[] { Object[].class }, (Object) new Object[] { "method1", "1", "2" });
			Reflection.invoke(new A(), "method1", new Class<?>[] { Object[].class }, (Object) new Object[] { "1", "2", null, "4" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class A {

	private void method1(Object... values) {

		if (values == null) {
			System.out.println("");
		} else {
			System.out.println(values.length);
		}
	}
}
