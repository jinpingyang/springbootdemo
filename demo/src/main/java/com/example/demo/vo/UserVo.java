package com.example.demo.vo;

import lombok.Data;

@Data
public class UserVo {
	private int id;
	private String no;
	private String name;
	private String address;
	
	public UserVo(int id, String no, String name, String address) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.address = address;
	}
	
}
