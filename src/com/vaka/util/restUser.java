package com.vaka.util;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "User", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)

public class restUser {
	
	private Integer userId;
	private String userAvatar;
	private Timestamp userDate;
	private String userEmail;	
	private String userFname;	
	private String userLname;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public Timestamp getUserDate() {
		return userDate;
	}
	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserFname() {
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}	
	
	
}
