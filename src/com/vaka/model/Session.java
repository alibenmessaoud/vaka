package com.vaka.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the sessions database table.
 * 
 */
@Entity
@XmlRootElement(name = "Session", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="sessions")
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="session_id", unique=true, nullable=false)
	private Integer sessionId;

	@Column(name="session_date")
	private Timestamp sessionDate;

	@Column(name="session_key", length=50)
	private String sessionKey;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="session_user_id", nullable=false)
	private User user;

	public Session() {
	}

	public Integer getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Timestamp getSessionDate() {
		return this.sessionDate;
	}

	public void setSessionDate(Timestamp sessionDate) {
		this.sessionDate = sessionDate;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", sessionDate="
				+ sessionDate + ", sessionKey=" + sessionKey + ", user=" + user
				+ "]";
	}
	
	
}