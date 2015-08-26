package com.rms.common.xml.bean.backup;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * xmlのelemenｔの情報を保持するクラス
 * 
 * @author ri.meisei
 * 
 */
public class ElementInfo {

	/** elementの階層 */
	private int level;

	/** elementの名称 */
	private String name;

	/** elementのpath情報 */
	private String path;

	/** elementの回数 */
	private int times;

	/** elementのテキスト内容 */
	private String textValue;

	/** elementの属性情報 */
	private Map<String, String> attribueMap = new LinkedHashMap<String, String>();

	/**
	 * デフォルトコンストラクター
	 */
	public ElementInfo() {

	}

	/**
	 * 内部持ち情報をクリアする
	 */
	public void clear() {

		level = 0;
		name = null;
		path = null;
		times = 0;
		textValue = null;
		attribueMap.clear();
	}

	/**
	 * @return level
	 */
	public int getLevel() {

		return level;
	}

	/**
	 * @param level
	 *            セットする level
	 */
	public void setLevel(int level) {

		this.level = level;
	}

	/**
	 * @return name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return path
	 */
	public String getPath() {

		return path;
	}

	/**
	 * @param path
	 *            セットする path
	 */
	public void setPath(String path) {

		this.path = path;
	}

	/**
	 * @return times
	 */
	public int getTimes() {

		return times;
	}

	/**
	 * @param times
	 *            セットする times
	 */
	public void setTimes(int times) {

		this.times = times;
	}

	/**
	 * @return textValue
	 */
	public String getTextValue() {

		return textValue;
	}

	/**
	 * @param textValue
	 *            セットする textValue
	 */
	public void setTextValue(String textValue) {

		this.textValue = textValue;
	}

	/**
	 * @return attribueMap
	 */
	public Map<String, String> getAttribueMap() {

		return attribueMap;
	}

	/**
	 * @param attribueMap
	 *            セットする attribueMap
	 */
	public void setAttribueMap(Map<String, String> attribueMap) {

		this.attribueMap = attribueMap;
	}

}
