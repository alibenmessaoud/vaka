package com.vaka.util;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Follow", namespace="")
public class restFollow {
	private Integer followId;	
	private Integer followerId;	
	private String followerName;	
	private String followerEmail;
	private Integer followedId;	
	private String followedName;	
	private String followedEmail;
	private Timestamp followDate;
	
	
	public String getFollowerName() {
		return followerName;
	}
	public void setFollowerName(String followerName) {
		this.followerName = followerName;
	}
	public String getFollowedName() {
		return followedName;
	}
	public void setFollowedName(String followedName) {
		this.followedName = followedName;
	}
	
	
	
	
	public Integer getFollowerId() {
		return followerId;
	}
	public void setFollowerId(Integer followerId) {
		this.followerId = followerId;
	}
	 
	public Integer getFollowedId() {
		return followedId;
	}
	public void setFollowedId(Integer followedId) {
		this.followedId = followedId;
	}
	 
	public Timestamp getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Timestamp followDate) {
		this.followDate = followDate;
	}
	
	public String getFollowerEmail() {
		return followerEmail;
	}
	public void setFollowerEmail(String followerEmail) {
		this.followerEmail = followerEmail;
	}
	public String getFollowedEmail() {
		return followedEmail;
	}
	public void setFollowedEmail(String followedEmail) {
		this.followedEmail = followedEmail;
	}
	public Integer getFollowId() {
		return followId;
	}
	public void setFollowId(Integer followId) {
		this.followId = followId;
	}
	
	
	
	
}
