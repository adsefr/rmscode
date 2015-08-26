package com.rms.base.logging;

import org.slf4j.LoggerFactory;

import com.rms.base.validate.Assertion;

public class Logger {

	private org.slf4j.Logger logger;

	private Logger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public static Logger getLogger() {

		String loggerName = Thread.currentThread().getStackTrace()[2].getClassName();

		return getLogger(loggerName);
	}

	public static Logger getLogger(String loggerName) {

		Assertion.assertNotBlank("loggerName", loggerName);

		return new Logger(LoggerFactory.getLogger(loggerName));
	}

	public static Logger getLogger(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return new Logger(LoggerFactory.getLogger(clazz));
	}

	public String getName() {

		return logger.getName();
	}

	// public void fatal(Throwable t) {
	//
	// logger.fatal("", t);
	// }
	//
	// public void fatal(String message) {
	//
	// logger.fatal(message);
	// }
	//
	// public void fatal(String format, Object parameter) {
	//
	// logger.fatal(format, parameter);
	// }
	//
	// public void fatal(Throwable t, String format, Object parameter) {
	//
	// logger.fatal(format, parameter);// TODO
	// }
	//
	// public void fatal(String format, Object... parameters) {
	//
	// logger.fatal(format, parameters);
	// }
	//
	// public void fatal(Throwable t, String format, Object... parameters) {
	//
	// logger.fatal(format, parameters);// TODO
	// }

	public void error(Throwable t) {

		logger.error("", t);
	}

	public void error(String message) {

		logger.error(message);
	}

	public void error(String format, Object parameter) {

		logger.error(format, parameter);
	}

	public void error(Throwable t, String format, Object parameter) {

		logger.error(format, parameter);// TODO
	}

	public void error(String format, Object... parameters) {

		logger.error(format, parameters);
	}

	public void error(Throwable t, String format, Object... parameters) {

		logger.error(format, parameters);// TODO
	}

	public void warn(Throwable t) {

		logger.warn("", t);
	}

	public void warn(String message) {

		logger.warn(message);
	}

	public void warn(String format, Object parameter) {

		logger.warn(format, parameter);
	}

	public void warn(Throwable t, String format, Object parameter) {

		logger.warn(format, parameter);// TODO
	}

	public void warn(String format, Object... parameters) {

		logger.warn(format, parameters);
	}

	public void warn(Throwable t, String format, Object... parameters) {

		logger.warn(format, parameters);// TODO
	}

	public void info(Throwable t) {

		logger.info("", t);
	}

	public void info(String message) {

		logger.info(message);
	}

	public void info(String format, Object parameter) {

		logger.info(format, parameter);
	}

	public void info(Throwable t, String format, Object parameter) {

		logger.info(format, parameter);// TODO
	}

	public void info(String format, Object... parameters) {

		logger.info(format, parameters);
	}

	public void info(Throwable t, String format, Object... parameters) {

		logger.info(format, parameters);// TODO
	}

	public void debug(Throwable t) {

		logger.debug("", t);
	}

	public void debug(String message) {

		logger.debug(message);
	}

	public void debug(String format, Object parameter) {

		logger.debug(format, parameter);
	}

	public void debug(Throwable t, String format, Object parameter) {

		logger.debug(format, parameter);// TODO
	}

	public void debug(String format, Object... parameters) {

		logger.debug(format, parameters);
	}

	public void debug(Throwable t, String format, Object... parameters) {

		logger.debug(format, parameters);// TODO
	}

	public void trace(Throwable t) {

		logger.trace("", t);
	}

	public void trace(String message) {

		logger.trace(message);
	}

	public void trace(String format, Object parameter) {

		logger.trace(format, parameter);
	}

	public void trace(Throwable t, String format, Object parameter) {

		logger.trace(format, parameter);// TODO
	}

	public void trace(String format, Object... parameters) {

		logger.trace(format, parameters);
	}

	public void trace(Throwable t, String format, Object... parameters) {

		logger.trace(format, parameters);// TODO
	}
}
