package jp.co.rnai.task.talend;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class JobUtils {

	/** 共通情報のキー */
	public final static String JOB_NAME = "JOB_NAME";

	public final static String JOB_START_TIME = "JOB_START_TIME";

	public final static String JOB_STATUS = "JOB_STATUS";

	public final static String JOB_EXIT_CODE = "JOB_EXIT_CODE";

	public final static String JOB_PROOF_MODULE_TYPE = "JOB_PROOF_MODULE_TYPE";

	public final static String INPUTDATA_FILEDIRECTORY = "INPUTDATA_FILEDIRECTORY";

	public final static String CURRENT_FILEDIRECTORY = "CURRENT_FILEDIRECTORY";

	public final static String CURRENT_FILEPATH = "CURRENT_FILEPATH";

	public final static String CURRENT_TABLE = "CURRENT_TABLE";

	public final static String CURRENT_DATA_KEY = "CURRENT_DATA_KEY";

	public final static String TBL_OPERATION = "TBL_OPERATION";

	public final static String LINE_SEPERATOR = "\n";

	/** コンポーネントタイプ */
	public final static String JOB_PROOF_MODULE_JDBCOUTPUT = "tJDBCOutput";

	public final static String JOB_PROOF_MODULE_POSTGRESQLOUTPUT = "tPostgresqlOutput";

	/** プルーフ情報のキー */
	public final static String PROOF_HEAD_LINE = "PROOF_HEAD_LINE";

	public final static String PROOF_SPLIT_LINE = "PROOF_SPLIT_LINE";

	public final static String PROOF_DATE_FORMAT = "PROOF_DATE_FORMAT";

	public final static String PROOF_JOB_NAME = "PROOF_JOB_NAME";

	public final static String PROOF_START_TIME = "PROOF_START_TIME";

	public final static String PROOF_INPUTDATA_DIRECTORY = "PROOF_INPUTDATA_DIRECTORY";

	public final static String PROOF_TAGET_FILE = "PROOF_TAGET_FILE";

	public final static String PROOF_TAGET_TABLE = "PROOF_TAGET_TABLE";

	public final static String PROOF_TBL_OPERATION = "PROOF_TBL_OPERATION";

	public final static String PROOF_NORMAL_COUNT = "PROOF_NORMAL_COUNT";

	public final static String PROOF_WARN_COUNT = "PROOF_WARN_COUNT";

	public final static String PROOF_ERROR_COUNT = "PROOF_ERROR_COUNT";

	public final static String PROOF_TBL_INSERT_COUNT = "PROOF_TBL_INSERT_COUNT";

	public final static String PROOF_TBL_UPDATE_COUNT = "PROOF_TBL_UPDATE_COUNT";

	public final static String PROOF_TBL_DELETE_COUNT = "PROOF_TBL_DELETE_COUNT";

	public final static String PROOF_EXIT_STATUS = "PROOF_EXIT_STATUS";

	public final static String PROOF_EXIT_CODE = "PROOF_EXIT_CODE";

	public final static String PROOF_END_TIME = "PROOF_END_TIME";

	public final static String PROOF_TAIL_LINE = "PROOF_TAIL_LINE";

	/** ログ情報のキー */
	public final static String LOG_HEAD_LINE = "LOG_HEAD_LINE";

	public final static String LOG_TAIL_LINE = "LOG_TAIL_LINE";

	public final static String LOG_JOB_NAME = "LOG_JOB_NAME";

	public final static String LOG_DATE = "LOG_DATE";

	public final static String LOG_ORIGIN = "LOG_ORIGIN";

	public final static String LOG_MSG_TYPE = "LOG_MSG_TYPE";

	public final static String LOG_EXIT_CODE = "LOG_EXIT_CODE";

	public final static String LOG_JOB_PROCESS = "LOG_JOB_PROCESS";

	public final static String LOG_MSG_INFO = "LOG_MSG_INFO";

	public final static String LOG_MSG_DETAIL = "LOG_MSG_DETAIL";

	public final static String JOB_PROCESS = "JOB_PROCESS";

	/** メッセージ情報のキー */
	private final static String MSG_ERR_FILE_READ_DELIMITED = "MSG_ERR_FILE_READ_DELIMITED";

	private final static String MSG_ERR_FILE_WRITE_DELIMITED = "MSG_ERR_FILE_WRITE_DELIMITED";

	private final static String MSG_ERR_FILE_READ_EXCEL = "MSG_ERR_FILE_READ_EXCEL";

	private final static String MSG_ERR_FILE_NOTFOUND = "MSG_ERR_FILE_NOTFOUND";

	private final static String MSG_ERR_DIRECTORY_NOTFOUND = "MSG_ERR_DIRECTORY_NOTFOUND";

	private final static String MSG_ERR_PARSE_XML = "MSG_ERR_PARSE_XML";

	private final static String MSG_ERR_DB_CONNECTION_JDBC = "MSG_ERR_DB_CONNECTION_JDBC";

	private final static String MSG_ERR_TBL_OPERATION = "MSG_ERR_TBL_OPERATION";

	private final static String MSG_ERR_UNKOWN = "MSG_ERR_UNKOWN";

	private JobUtils() {

	}

	public final static JobUtils getInstance() {

		return new JobUtils();
	}

	public static String processLogCatcher(Properties context, final Map<String, Object> globalMap,
			Map<String, String> infos) {

		StringBuilder builder = new StringBuilder();

		builder.append(format(context.getProperty(LOG_HEAD_LINE)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_DATE), infos.get(LOG_DATE)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_JOB_NAME), infos.get(LOG_JOB_NAME)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_JOB_PROCESS), globalMap.get(JOB_PROCESS)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_EXIT_CODE), infos.get(LOG_EXIT_CODE)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_ORIGIN), infos.get(LOG_ORIGIN)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_MSG_TYPE), getMssageType(infos.get(LOG_MSG_TYPE))));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_MSG_INFO), getMessage(context, globalMap, infos)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_MSG_DETAIL), infos.get(LOG_MSG_DETAIL)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(LOG_TAIL_LINE)));
		builder.append(LINE_SEPERATOR);

		return builder.toString();
	}

	private static String getMessage(Properties context, final java.util.Map<String, Object> globalMap,
			Map<String, String> infos) {

		String message = "";
		if (infos.get(LOG_ORIGIN).matches("tFileInputDelimited_\\d+")) {
			message = getMessageForTFileInputDelimited(context, globalMap, infos);

		} else if (infos.get(LOG_ORIGIN).matches("tFileOutputDelimited_\\d+")) {
			message = getMessageForTFileOutputDelimited(context, globalMap, infos);

		} else if (infos.get(LOG_ORIGIN).matches("tFileInputExcel_\\d+")) {
			message = getMessageForTFileInputExcel(context, globalMap, infos);

		} else if (infos.get(LOG_ORIGIN).matches("tExtractXMLField_\\d+")) {
			message = getMessageForTExtractXMLField(context, globalMap);

		} else if (infos.get(LOG_ORIGIN).matches("tFileList_\\d+")) {
			message = getMessageForTFileList(context, globalMap);

		} else if (infos.get(LOG_ORIGIN).matches("tJDBCConnection_\\d+")) {
			message = getMessageForTJDBCConnection(context);

		} else if (infos.get(LOG_ORIGIN).matches("tJDBCOutput_\\d+")) {
			message = getMessageForTJDBCOutput(context, globalMap);

		} else if (infos.get(LOG_ORIGIN).matches("tPostgresqlOutput_\\d+")) {
			message = getMessageForTPostgresqlOutput(context, globalMap);

		} else {
			message = getParamsForUnkownError(context, globalMap);
		}

		return message;
	}

	private static String getMessageForTFileInputDelimited(Properties context,
			final java.util.Map<String, Object> globalMap, Map<String, String> infos) {

		String message = context.getProperty(MSG_ERR_FILE_READ_DELIMITED);
		Object[] params = new Object[2];
		params[0] = globalMap.get(CURRENT_FILEPATH);
		params[1] = globalMap.get(infos.get(LOG_ORIGIN) + "_NB_LINE");
		params[1] = (params[1] == null) ? 1 : ((Integer) params[1] + 1);

		return format(message, params);
	}

	private static String getMessageForTFileOutputDelimited(Properties context,
			final java.util.Map<String, Object> globalMap, Map<String, String> infos) {

		String message = context.getProperty(MSG_ERR_FILE_WRITE_DELIMITED);
		Object[] params = new Object[2];
		params[0] = globalMap.get(infos.get(LOG_ORIGIN) + "_FILE_NAME");
		params[1] = globalMap.get(infos.get(LOG_ORIGIN) + "_NB_LINE");
		if (params[1] == null) {
			params[1] = 0;
		}

		return format(message, params);
	}

	private static String getMessageForTFileInputExcel(Properties context,
			final java.util.Map<String, Object> globalMap, Map<String, String> infos) {

		String message = context.getProperty(MSG_ERR_FILE_READ_EXCEL);
		Object[] params = new Object[3];
		params[0] = globalMap.get(CURRENT_FILEPATH);
		params[1] = globalMap.get(infos.get(LOG_ORIGIN) + "_CURRENT_SHEET");
		params[2] = globalMap.get(infos.get(LOG_ORIGIN) + "_NB_LINE");
		params[1] = (params[1] == null) ? 1 : ((Integer) params[1] + 1);

		return format(message, params);
	}

	private static String getMessageForTExtractXMLField(Properties context,
			final java.util.Map<String, Object> globalMap) {

		String message = context.getProperty(MSG_ERR_PARSE_XML);
		Object[] params = new Object[1];
		params[0] = globalMap.get(CURRENT_FILEPATH);

		return format(message, params);
	}

	private static String getMessageForTFileList(Properties context, final java.util.Map<String, Object> globalMap) {

		String maskType = (String) globalMap.get("MASK_TYPE");
		String message = "";

		if (maskType.equals(context.getProperty("MASK_TYPE_FILE"))) {
			message = context.getProperty(MSG_ERR_FILE_NOTFOUND);
		} else if (maskType.equals(context.getProperty("MASK_TYPE_DIRECTORY"))) {
			message = context.getProperty(MSG_ERR_DIRECTORY_NOTFOUND);
		}

		Object[] params = new Object[2];
		params[0] = globalMap.get(CURRENT_FILEDIRECTORY);
		params[1] = globalMap.get("FILE_MASK");

		return format(message, params);
	}

	private static String getMessageForTJDBCConnection(Properties context) {

		String message = context.getProperty(MSG_ERR_DB_CONNECTION_JDBC);
		Object[] params = new Object[3];
		params[0] = context.getProperty("DB_JdbcUrl");
		params[1] = context.getProperty("DB_Login");
		params[2] = context.getProperty("DB_Password");

		return format(message, params);
	}

	private static String getMessageForTJDBCOutput(Properties context, final java.util.Map<String, Object> globalMap) {

		String message = context.getProperty(MSG_ERR_TBL_OPERATION);
		Object[] params = new Object[4];
		params[0] = globalMap.get(CURRENT_TABLE);
		params[1] = globalMap.get(TBL_OPERATION);
		params[2] = globalMap.get(CURRENT_FILEPATH);
		params[3] = globalMap.get(CURRENT_DATA_KEY);

		return format(message, params);
	}

	private static String getMessageForTPostgresqlOutput(Properties context,
			final java.util.Map<String, Object> globalMap) {

		return getMessageForTJDBCOutput(context, globalMap);
	}

	private static String getParamsForUnkownError(Properties context, final java.util.Map<String, Object> globalMap) {

		String message = context.getProperty(MSG_ERR_UNKOWN);

		Object[] params = new Object[2];
		params[0] = globalMap.get(CURRENT_FILEPATH);
		if (params[0] == null) {
			params[0] = "";
		}

		return format(message, params);
	}

	private static String format(String message, Object... params) {

		for (int i = 0; i < params.length; i++) {
			params[i] = (params[i] == null) ? "" : params[i];
		}
		return MessageFormat.format(message, params);
	}

	private static String getMssageType(String messageType) {

		String value = "";

		if (messageType != null) {
			if (messageType.equals("Java Exception") || messageType.equals("tDie")) {
				value = "エラー情報";
			} else if (messageType.equals("tWarn")) {
				value = "警告情報";
			} else {
				value = "正常情報";
			}
		}

		return value;
	}

	/**
	 * プルーフの開始情報を返す
	 *
	 * @param properties
	 * @param globalMap
	 * @return
	 */
	public static String printProofStartInfo(Properties context, final Map<String, Object> globalMap) {

		StringBuilder builder = new StringBuilder();

		builder.append(format(context.getProperty(PROOF_HEAD_LINE)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_JOB_NAME), globalMap.get(JOB_NAME)));
		builder.append(LINE_SEPERATOR);

		String dateFormat = context.getProperty(PROOF_DATE_FORMAT);
		Date date = new Date((Long) globalMap.get(JOB_START_TIME));
		builder.append(format(context.getProperty(PROOF_START_TIME), new SimpleDateFormat(dateFormat).format(date)));
		builder.append(LINE_SEPERATOR);

		return builder.toString();
	}

	/**
	 * コンポーネントのプルーフ情報を返す
	 *
	 * @param properties
	 * @param globalMap
	 * @param startHash
	 * @return
	 */
	public static String printProofModuleInfo(Properties context, final Map<String, Object> globalMap,
			Map<String, Long> startHash) {

		String type = (String) globalMap.get(JOB_PROOF_MODULE_TYPE);
		String origin = "";
		long max = 0;
		for (String originId : startHash.keySet()) {
			if (originId.startsWith(type)) {
				if (startHash.get(originId) > max) {
					max = startHash.get(originId);
					origin = originId;
				}
			}
		}

		String proofInfo = "";

		if (origin.startsWith(JOB_PROOF_MODULE_JDBCOUTPUT)) {
			proofInfo = printProofJDBCOutput(context, globalMap, origin);

		} else if (origin.startsWith(JOB_PROOF_MODULE_POSTGRESQLOUTPUT)) {
			proofInfo = printProofPostgresqlOutput(context, globalMap, origin);
		}

		return proofInfo;
	}

	/**
	 *
	 * @param properties
	 * @param globalMap
	 * @param origin
	 * @return
	 */
	private static String printProofJDBCOutput(Properties context, final Map<String, Object> globalMap, String origin) {

		StringBuilder builder = new StringBuilder();

		builder.append(context.get(PROOF_SPLIT_LINE));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_INPUTDATA_DIRECTORY), globalMap.get(INPUTDATA_FILEDIRECTORY)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TAGET_TABLE), globalMap.get(CURRENT_TABLE)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TBL_OPERATION), globalMap.get(TBL_OPERATION)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_NORMAL_COUNT), (Integer) globalMap.get(origin + "_NB_LINE")));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_ERROR_COUNT),
				(Integer) globalMap.get(origin + "_NB_LINE_REJECTED")));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TBL_INSERT_COUNT),
				(Integer) globalMap.get(origin + "_NB_LINE_INSERTED")));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TBL_UPDATE_COUNT),
				(Integer) globalMap.get(origin + "_NB_LINE_UPDATED")));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TBL_DELETE_COUNT),
				(Integer) globalMap.get(origin + "_NB_LINE_DELETED")));
		builder.append(LINE_SEPERATOR);

		builder.append(context.get(PROOF_SPLIT_LINE));
		builder.append(LINE_SEPERATOR);

		return builder.toString();
	}

	/**
	 *
	 * @param properties
	 * @param globalMap
	 * @param origin
	 * @return
	 */
	private static String printProofPostgresqlOutput(Properties context, final Map<String, Object> globalMap,
			String origin) {

		return printProofJDBCOutput(context, globalMap, origin);
	}

	/**
	 * プルーフの終了情報を返す
	 *
	 * @param properties
	 * @param globalMap
	 * @return
	 */
	public static String printProofEndInfo(Properties context, final Map<String, Object> globalMap) {

		StringBuilder builder = new StringBuilder();

		builder.append(format(context.getProperty(PROOF_EXIT_STATUS), getJobStatus(globalMap)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_EXIT_CODE), getJobExitCode(globalMap)));
		builder.append(LINE_SEPERATOR);

		String dateFormat = context.getProperty(PROOF_DATE_FORMAT);
		Date date = new Date(System.currentTimeMillis());
		builder.append(format(context.getProperty(PROOF_END_TIME), new SimpleDateFormat(dateFormat).format(date)));
		builder.append(LINE_SEPERATOR);

		builder.append(format(context.getProperty(PROOF_TAIL_LINE)));
		builder.append(LINE_SEPERATOR);

		return builder.toString();
	}

	/**
	 * ジョブの終了状態を返す
	 *
	 * @param globalMap
	 *            グローバル変数マップ
	 * @return ジョブ終了状態
	 */
	private static String getJobStatus(final Map<String, Object> globalMap) {

		String status = (String) globalMap.get(JOB_STATUS);

		if (status != null && "failure".equals(status)) {
			return "異常終了";
		}

		return "正常終了";
	}

	/**
	 * ジョブの終了コードを返す
	 *
	 * @param globalMap
	 *            グローバル変数マップ
	 *
	 * @return ジョブ終了コード
	 */
	private static Integer getJobExitCode(final Map<String, Object> globalMap) {

		String status = (String) globalMap.get(JOB_STATUS);
		Integer exitCode = (Integer) globalMap.get(JOB_EXIT_CODE);

		if (exitCode == null) {
			if (status != null && "failure".equals(status)) {
				exitCode = 1;
			} else {
				exitCode = 0;
			}
		}

		return exitCode;

	}
}