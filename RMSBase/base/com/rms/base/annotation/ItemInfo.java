package com.rms.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rms.base.enumeration.DataType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ItemInfo {

	String name() default "";

	DataType valueType();

	long minLength() default 0;

	long maxLength() default Long.MAX_VALUE;

	boolean isReqired() default false;

	String format();
}
