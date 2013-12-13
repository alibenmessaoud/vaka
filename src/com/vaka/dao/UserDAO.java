package com.vaka.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.Event;
import com.vaka.model.InvitedUser;
import com.vaka.model.User;
import com.vaka.model.UsersFriendship;

@Stateless
public class UserDAO extends DAO<User> {

	public UserDAO() {
		super(User.class);
	}

	@Override
	public String toString() {
		return "UserDAO [toString()=" + super.toString() + "]";
	}
	
	public List<User> allUsers() {
		l.info("List<User> allUsers() "); 
		Query query = em.createQuery("select u from User u"); 
		return (List<User>) query.getResultList();
	}

	public List<User> findUserByName(String name) {
		//l.info("List<User> findUserByName(String name = "+name+")"); 
		Query query = em.createQuery("select u from User u where u.userFname = :name"); 
		query.setParameter("name", name);
		return (List<User>) query.getResultList();
	}
	
	public List<User> authUser(String userEmail, String userPassword) {
		//l.info("List<User> authUser(String userEmail="+userEmail+", String userPassword="+userPassword+")"); 
		Query query = em.createQuery("select u from User u where u.userEmail = :userEmail and u.userPassword = :userPassword"); 
		query.setParameter("userEmail", userEmail);
		query.setParameter("userPassword", userPassword);
		return (List<User>) query.getResultList();
	}
	
	public List<User> findUserByEmail(String email) {
		//l.info("List<User> findUserByEmail(String email="+email+")"); 
		Query query = em.createQuery("select u from User u where u.userEmail = :email"); 
		query.setParameter("email", email);
		return (List<User>) query.getResultList();
	}
	
	public List<InvitedUser> userInvitaions(User u)
	{
		//l.info("List<InvitedUser> userInvitaions(User u = "+u.toString() +")"); 
		Query query = em.createQuery("select iu from InvitedUser iu where iu.user2 = :invitedUser");
		query.setParameter("invitedUser", u);
		return (List<InvitedUser>) query.getResultList();		
	}

	public List<User> getUsersByLocation(String decodedLocation) {
		//l.info("List<User> getUsersByLocation(String decodedLocation)"); 
		Query query = em.createQuery("select u from User u where u.userAvatar = :decodedLocation ");
		query.setParameter("decodedLocation", decodedLocation);
		return (List<User>) query.getResultList();
	}			
}