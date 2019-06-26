package com.VinhNN.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToChange {
	enum TYPE{INT,STRING};
	boolean change() default true;
	TYPE type() default TYPE.INT;
}
