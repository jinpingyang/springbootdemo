package com.example.demo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = { "id", "name", "no", "address" })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "uid")
	private int uid;
	@XmlElement(name = "id")
	private int id;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "no")
	private String no;
	@XmlElement(name = "address")
	private String address;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", id=" + id + ", name=" + name + ", no=" + no + ", address=" + address + "]";
	}

}
