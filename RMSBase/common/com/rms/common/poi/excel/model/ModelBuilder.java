package com.rms.common.poi.excel.model;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/10
 */
interface ModelBuilder<M extends Model> {

	public M build();

	public void clear();
}
