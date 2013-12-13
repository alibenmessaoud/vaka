package com.vaka.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Message", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	
	private int userID;
	private String sessionKey;
	private String message;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [userID=" + userID + ", sessionKey=" + sessionKey
				+ ", message=" + message + "]";
	}
	
	
	
	
}
