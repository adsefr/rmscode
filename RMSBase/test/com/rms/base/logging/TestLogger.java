package com.rms.base.logging;

import org.junit.Test;

public class TestLogger {

	@Test
	public void getLogger() {

		Logger logger = null;

		logger = Logger.getLogger();
		logger.error(logger.getName());

		logger = Logger.getLogger(TestLogger.class);
		logger.warn(logger.getName());

		logger = Logger.getLogger(TestLogger.class.getName());
		logger.info(logger.getName());
		logger = Logger.getLogger(TestLogger.class.getName());
		logger.debug(logger.getName());
		logger = Logger.getLogger(TestLogger.class.getName());
		logger.trace(logger.getName());

	}
}