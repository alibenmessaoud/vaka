package com.vaka.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the users_friendships database table.
 * 
 */
@Entity
@XmlRootElement(name = "UsersFriendship", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="users_friendships")
@NamedQuery(name="UsersFriendship.findAll", query="SELECT u FROM UsersFriendship u")
public class UsersFriendship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uf_id", unique=true, nullable=false)
	private Integer ufId;

	@Column(name="uf_date")
	private Timestamp ufDate;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uf_user_id", nullable=false)
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uf_friend_id", nullable=false)
	private User user2;

	public UsersFriendship() {
	}

	public Integer getUfId() {
		return this.ufId;
	}

	public void setUfId(Integer ufId) {
		this.ufId = ufId;
	}

	public Timestamp getUfDate() {
		return this.ufDate;
	}

	public void setUfDate(Timestamp ufDate) {
		this.ufDate = ufDate;
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

}