package com.VinhNN.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.VinhNN.annotation.ToChange.TYPE;

public class Demo {
	private static final String MODIFIERS = "modifiers";
	private static final String FIELD_VALUE = " FIELD value : ";
	private static final String FIELD_NAME = " FIELD Name : ";

	@ToChange(change = true, type = TYPE.INT)
	private static int x = 1;

	@ToChange(change = true, type = TYPE.INT)
	static final int y = 11;

	@ToChange(change = false, type = TYPE.INT)
	static int z = 21;

	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Class demoClass = Demo.class;
		for (Field field : demoClass.getDeclaredFields()) {
			ToChange toChangeAnnotation = (ToChange) field.getDeclaredAnnotation(ToChange.class);
			if ((toChangeAnnotation != null) && (toChangeAnnotation.change() == true)
					&& (toChangeAnnotation.type() == TYPE.INT)) {

				Field fieldModifier = Field.class.getDeclaredField(MODIFIERS);
				fieldModifier.setAccessible(true);
				fieldModifier.setInt(field, field.getModifiers() & ~Modifier.FINAL);
				field.setAccessible(true);
				field.set(null, 0);

				System.out.println(FIELD_NAME + field.getName());
				System.out.println(FIELD_VALUE + field.get(null));
			}
		}
	}
}