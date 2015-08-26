package com.rms.common.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerTest {

	static Logger logger = Logger.getGlobal();

	public static void main(String[] args) {

		logger.log(Level.OFF, "OFF msg");
		logger.log(Level.SEVERE, "SEVERE msg");
		logger.log(Level.WARNING, "WARNING msg");
		logger.log(Level.INFO, "INFO msg");
		logger.log(Level.FINE, "FINE msg");
		logger.log(Level.FINER, "FINER msg");
		logger.log(Level.FINEST, "FINEST msg");
		logger.log(Level.ALL, "ALL msg");
	}
}
