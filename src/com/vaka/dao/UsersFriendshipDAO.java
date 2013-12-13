package com.vaka.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.User;
import com.vaka.model.UsersFriendship;
 
@Stateless
public class UsersFriendshipDAO extends DAO<UsersFriendship> {
	
    public UsersFriendshipDAO() {
        super(UsersFriendship.class);
    }

	@Override
	public String toString() {
		return "UsersFriendship [toString()=" + super.toString() + "]";
	}
	
	public int isUserFollowingUser(User u1, User u2)
	{
		Query query = em.createQuery("SELECT u FROM UsersFriendship u Where u.user1 = :u1 and u.user2 = :u2");
		query.setParameter("u1", u1);
		query.setParameter("u2", u2);
		return  query.getResultList().size();
	}
	
	public List<UsersFriendship> getFriendship(User u1, User u2)
	{
		Query query = em.createQuery("SELECT u FROM UsersFriendship u Where u.user1 = :u1 and u.user2 = :u2");
		query.setParameter("u1", u1);
		query.setParameter("u2", u2);
		return query.getResultList();
	}
	
	public List<UsersFriendship> getFollowers(User u1)
	{
		Query query = em.createQuery("SELECT u FROM UsersFriendship u Where u.user2 = :u1");
		query.setParameter("u1", u1);
		return query.getResultList();
	}
	
	public List<UsersFriendship> getFollowings(User u1)
	{
		Query query = em.createQuery("SELECT u FROM UsersFriendship u Where u.user1 = :u1");
		query.setParameter("u1", u1);
		return query.getResultList();
	}
    
}