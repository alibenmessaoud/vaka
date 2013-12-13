package com.vaka.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Signin", namespace="")
public class Signin {
	
	private String email; 
	private String password;
	private String appkey;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	
	
}
