package com.vaka.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@XmlRootElement(name = "User", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="users")
@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
@NamedQuery(name="User.findById", query="SELECT e FROM User e WHERE e.userId = ?1"),
@NamedQuery(name="User.findByName", query="SELECT e FROM User e WHERE e.userFname = :userFname ")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Column(name="user_avatar", length=50)
	private String userAvatar;

	@Column(name="user_date")
	private Timestamp userDate;

	@Column(name="user_email", length=100)
	private String userEmail;

	@Column(name="user_fname", length=50)
	private String userFname;

	@Column(name="user_lname", length=50)
	private String userLname;

	@Column(name="user_password", length=20)
	private String userPassword;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="user")
	private List<Event> events;

	//bi-directional many-to-one association to InvitedUser
	@OneToMany(mappedBy="user1")
	private List<InvitedUser> invitedUsers1;

	//bi-directional many-to-one association to InvitedUser
	@OneToMany(mappedBy="user2")
	private List<InvitedUser> invitedUsers2;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="user")
	private List<Session> sessions;

	//bi-directional many-to-one association to UsersFriendship
	@OneToMany(mappedBy="user1")
	private List<UsersFriendship> usersFriendships1;

	//bi-directional many-to-one association to UsersFriendship
	@OneToMany(mappedBy="user2")
	private List<UsersFriendship> usersFriendships2;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAvatar() {
		return this.userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Timestamp getUserDate() {
		return this.userDate;
	}

	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return this.userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return this.userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setUser(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setUser(null);

		return event;
	}

	public List<InvitedUser> getInvitedUsers1() {
		return this.invitedUsers1;
	}

	public void setInvitedUsers1(List<InvitedUser> invitedUsers1) {
		this.invitedUsers1 = invitedUsers1;
	}

	public InvitedUser addInvitedUsers1(InvitedUser invitedUsers1) {
		getInvitedUsers1().add(invitedUsers1);
		invitedUsers1.setUser1(this);

		return invitedUsers1;
	}

	public InvitedUser removeInvitedUsers1(InvitedUser invitedUsers1) {
		getInvitedUsers1().remove(invitedUsers1);
		invitedUsers1.setUser1(null);

		return invitedUsers1;
	}

	public List<InvitedUser> getInvitedUsers2() {
		return this.invitedUsers2;
	}

	public void setInvitedUsers2(List<InvitedUser> invitedUsers2) {
		this.invitedUsers2 = invitedUsers2;
	}

	public InvitedUser addInvitedUsers2(InvitedUser invitedUsers2) {
		getInvitedUsers2().add(invitedUsers2);
		invitedUsers2.setUser2(this);

		return invitedUsers2;
	}

	public InvitedUser removeInvitedUsers2(InvitedUser invitedUsers2) {
		getInvitedUsers2().remove(invitedUsers2);
		invitedUsers2.setUser2(null);

		return invitedUsers2;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setUser(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setUser(null);

		return session;
	}

	public List<UsersFriendship> getUsersFriendships1() {
		return this.usersFriendships1;
	}

	public void setUsersFriendships1(List<UsersFriendship> usersFriendships1) {
		this.usersFriendships1 = usersFriendships1;
	}

	public UsersFriendship addUsersFriendships1(UsersFriendship usersFriendships1) {
		getUsersFriendships1().add(usersFriendships1);
		usersFriendships1.setUser1(this);

		return usersFriendships1;
	}

	public UsersFriendship removeUsersFriendships1(UsersFriendship usersFriendships1) {
		getUsersFriendships1().remove(usersFriendships1);
		usersFriendships1.setUser1(null);

		return usersFriendships1;
	}

	public List<UsersFriendship> getUsersFriendships2() {
		return this.usersFriendships2;
	}

	public void setUsersFriendships2(List<UsersFriendship> usersFriendships2) {
		this.usersFriendships2 = usersFriendships2;
	}

	public UsersFriendship addUsersFriendships2(UsersFriendship usersFriendships2) {
		getUsersFriendships2().add(usersFriendships2);
		usersFriendships2.setUser2(this);

		return usersFriendships2;
	}

	public UsersFriendship removeUsersFriendships2(UsersFriendship usersFriendships2) {
		getUsersFriendships2().remove(usersFriendships2);
		usersFriendships2.setUser2(null);

		return usersFriendships2;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAvatar=" + userAvatar
				+ ", userDate=" + userDate + ", userEmail=" + userEmail
				+ ", userFname=" + userFname + ", userLname=" + userLname
				+ ", userPassword=" + userPassword + ", events=" + events
				+ ", invitedUsers1=" + invitedUsers1 + ", invitedUsers2="
				+ invitedUsers2 + ", posts=" + posts + ", sessions=" + sessions
				+ ", usersFriendships1=" + usersFriendships1
				+ ", usersFriendships2=" + usersFriendships2 + "]";
	}

}