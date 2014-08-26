package com.bkteam.activylist;

import java.io.Serializable;

public class InfoEntity implements Serializable{
	private String name;
	private String preview;
	private String id;
	private	String cname;
	private	String price;
	
	public InfoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InfoEntity(String cname, String price) {
		
		        this.cname = cname;
		
		        this.price = price;

	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
	
}
