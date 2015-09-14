package com.rms.base.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestArrayUtil {

	@Test
	public void join() {

		List<String> list = new ArrayList<>();
		list.add("1");
		String result = null;

		result = ArrayUtil.join(list, ",");
		assertEquals("", "1", result);

		list.add("2");
		list.add("3");
		result = ArrayUtil.join(list, ",");
		assertEquals("", "1,2,3", result);

		list.add(null);
		result = ArrayUtil.join(list, ",");
		assertEquals("", "1,2,3,null", result);

		result = ArrayUtil.join(list, ",", false);
		assertEquals("", "1,2,3,null", result);

		result = ArrayUtil.join(list, ",", true);
		assertEquals("", "1,2,3", result);

		result = ArrayUtil.join(list, ",", "");
		assertEquals("", "1,2,3,", result);
	}
}
