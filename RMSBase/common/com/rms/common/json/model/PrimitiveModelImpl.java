package com.rms.common.json.model;

import com.rms.common.json.enums.JsonType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/16
 */
abstract class PrimitiveModelImpl extends JsonModelImpl implements PrimitiveModel {

	protected PrimitiveModelImpl(JsonType jsonType) {

		super(jsonType);
	}
}
