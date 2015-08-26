package com.rms.base.logging;

import org.junit.Test;

public class TestLogger {

	@Test
	public void getLogger() {

		Logger logger = null;

		logger = Logger.getLogger();
		logger.info(logger.getName());

		logger = Logger.getLogger(TestLogger.class);
		logger.info(logger.getName());

		logger = Logger.getLogger(TestLogger.class.getName());
		logger.info(logger.getName());

	}
}
