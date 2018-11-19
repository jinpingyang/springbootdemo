package com.example.demo.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		for(Method method : User.class.getDeclaredMethods()) {
			System.out.println(method);
			FieldCase fc=method.getAnnotation(FieldCase.class);
			if(fc!=null) {
				System.out.println("id="+fc.id()+",des="+fc.des()+",color="+fc.fruitColor());
			}
		}
		for(Field field : User.class.getDeclaredFields()) {
			System.out.println(field);
			FieldCase fc=field.getAnnotation(FieldCase.class);
			System.out.println("id="+fc.id()+",des="+fc.des()+",color="+fc.fruitColor());
		}
	}
}
