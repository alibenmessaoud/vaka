package com.vaka.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@XmlRootElement(name = "Post", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="posts")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id", unique=true, nullable=false)
	private Integer postId;

	@Column(name="post_date")
	private Timestamp postDate;

	@Column(name="post_text", length=200)
	private String postText;

	//bi-directional many-to-one association to Event
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post_event_id", nullable=false)
	private Event event;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="post_user_id", nullable=false)
	private User user;

	public Post() {
	}

	public Integer getPostId() {
		return this.postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Timestamp getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getPostText() {
		return this.postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}