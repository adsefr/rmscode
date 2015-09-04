package com.rms.util.baseclass;

import com.rms.base.logging.Logger;

/**
 *
 * @author ri.meisei
 * @since 2015/09/02
 */
public abstract class BaseProcess {

	private final static Logger logger = Logger.getLogger(BaseProcess.class);

	protected BaseProcess() {

	}

	public final void execute() throws Exception {

		try {
			initialize();

			beforeProcess();

			process();

			afterProcessSuccess();

		} catch (Exception e) {
			logger.error(e);
			afterProcessFailure();
			throw e;
		} finally {
			destory();
		}
	}

	public abstract void initialize() throws Exception;

	public abstract void beforeProcess() throws Exception;

	public abstract void process() throws Exception;

	public abstract void afterProcessSuccess() throws Exception;

	public abstract void afterProcessFailure() throws Exception;

	public abstract void destory() throws Exception;
}
