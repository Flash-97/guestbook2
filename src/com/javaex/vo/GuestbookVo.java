package com.javaex.vo;

public class GuestbookVo {
	private int number;
	private String name;
	private String passwd;
	private String content;
	private String date;

	public GuestbookVo(String name, String passwd, String content) {
		this.name = name;
		this.passwd = passwd;
		this.content = content;
	}

	public GuestbookVo(int number, String name, String content, String date) {
		this.number = number;
		this.name = name;
		this.content = content;
		this.date = date;
	}

	public String getPw() {
		return passwd;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "GuestbookVo [number=" + number + ", name=" + name + ", passwd=" + passwd + ", content=" + content
				+ ", date=" + date + "]";
	}

	
	
	
	
	
}