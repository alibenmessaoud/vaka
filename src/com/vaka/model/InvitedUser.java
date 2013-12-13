package com.vaka.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the invited_users database table.
 * 
 */
@Entity
@XmlRootElement(name = "InvitedUser", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="invited_users")
@NamedQuery(name="InvitedUser.findAll", query="SELECT i FROM InvitedUser i")
public class InvitedUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iu_id", unique=true, nullable=false)
	private Integer iuId;

	@Column(name="iu_date")
	private Timestamp iuDate;

	@Column(name="iu_state", length=10)
	private String iuState;

	//bi-directional many-to-one association to Event
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_event_id", nullable=false)
	private Event event;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_invited_id", nullable=false)
	private User user2;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_invitor_id", nullable=false)
	private User user1;

	public InvitedUser() {
	}

	public Integer getIuId() {
		return this.iuId;
	}

	public void setIuId(Integer iuId) {
		this.iuId = iuId;
	}

	public Timestamp getIuDate() {
		return this.iuDate;
	}

	public void setIuDate(Timestamp iuDate) {
		this.iuDate = iuDate;
	}

	public String getIuState() {
		return this.iuState;
	}

	public void setIuState(String iuState) {
		this.iuState = iuState;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		return "InvitedUser [iuId=" + iuId + ", iuDate=" + iuDate
				+ ", iuState=" + iuState + ", event=" + event + ", user2="
				+ user2 + ", user1=" + user1 + "]";
	}

	
	
}