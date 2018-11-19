package com.example.demo.annotation;

import com.example.demo.annotation.FieldCase.Color;

public class User {
	
	@FieldCase(id=1,des="id",fruitColor=Color.RED)
	private int id;
	@FieldCase(id=2,des="name",fruitColor=Color.GREEN)
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
