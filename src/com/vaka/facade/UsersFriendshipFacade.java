package com.vaka.facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.dao.SessionDAO;
import com.vaka.dao.UserDAO;
import com.vaka.dao.UsersFriendshipDAO;
import com.vaka.model.Session;
import com.vaka.model.User;
import com.vaka.model.UsersFriendship;
import com.vaka.util.Message;
import com.vaka.util.Utils;
import com.vaka.util.restFollow;

@Stateless
public class UsersFriendshipFacade {

	@EJB
	private UsersFriendshipDAO usersFriendshipDAO = new UsersFriendshipDAO();
	@EJB
	private UserDAO userDAO = new UserDAO();

	public void C(UsersFriendship usersFriendship) {
		usersFriendshipDAO.insert(usersFriendship);
	}

	public UsersFriendship R(int id) {
		return usersFriendshipDAO.get(id);
	}

	public List<UsersFriendship> L() {
		return usersFriendshipDAO.list();
	}
	
	public Message followUser(int id1, int id2)
	{
		UserDAO userDAOa = new UserDAO();
		UsersFriendship ufs = new UsersFriendship();
		UsersFriendshipDAO usersFriendshipDAOa = new UsersFriendshipDAO();
		
		Message message = new Message();
		
		User u1 = userDAOa.get(id1);
		User u2 = userDAOa.get(id2);						
		
		if (usersFriendshipDAOa.isUserFollowingUser(u1, u2)==0)
		{			
			ufs.setUser1(u1);
			ufs.setUser2(u2);
			ufs.setUfDate(new Timestamp(new Date().getTime()));
			C(ufs);			
			message.setMessage("Follow-ok");
			return message;
		}
		else
		{
			message.setMessage("Follow-ko");
			return message;
		}		
	}
	
	public Message unfollowUser(int id1, int id2)
	{
		UserDAO userDAOa = new UserDAO();
		UsersFriendship ufs = new UsersFriendship();
		UsersFriendshipDAO usersFriendshipDAOa = new UsersFriendshipDAO();
		
		Message message = new Message();
		
		User u1 = userDAOa.get(id1);
		User u2 = userDAOa.get(id2);								
		
		if (usersFriendshipDAOa.isUserFollowingUser(u1, u2)==1)
		{			
			List<UsersFriendship> luf = usersFriendshipDAOa.getFriendship(u1, u2);
			usersFriendshipDAOa.remove(luf.get(0).getUfId());			
			message.setMessage("Unfollow-ok");
			return message;
		}
		else
		{
			message.setMessage("Unfollow-ko");
			return message;
		}		
	}
	
	public List<restFollow> getFollowers(int id)
	{
		User u1 = userDAO.get(id);
		List<UsersFriendship> usersFriendships = usersFriendshipDAO.getFollowers(u1);
		List<restFollow> followers = new ArrayList<restFollow>();
		restFollow follow = new restFollow();
		for (UsersFriendship usersFriendship : usersFriendships) {
			follow.setFollowId(usersFriendship.getUfId());
			follow.setFollowerId(usersFriendship.getUser1().getUserId());
			follow.setFollowedName(usersFriendship.getUser1().getUserFname()+" "+usersFriendship.getUser1().getUserLname());
			follow.setFollowedEmail(usersFriendship.getUser1().getUserEmail());
			follow.setFollowedId(usersFriendship.getUser2().getUserId());
			follow.setFollowedName(usersFriendship.getUser2().getUserFname()+" "+usersFriendship.getUser2().getUserLname());
			follow.setFollowerEmail(usersFriendship.getUser2().getUserEmail());
			follow.setFollowDate(usersFriendship.getUfDate());
			followers.add(follow);
		}
		return followers;
	}
	
	public List<restFollow> getFollowings(int id)
	{
		User u1 = userDAO.get(id);
		List<UsersFriendship> usersFriendships = usersFriendshipDAO.getFollowings(u1);
		List<restFollow> followers = new ArrayList<restFollow>();
		restFollow follow = new restFollow();
		for (UsersFriendship usersFriendship : usersFriendships) {
			follow.setFollowId(usersFriendship.getUfId());
			follow.setFollowerId(usersFriendship.getUser1().getUserId());
			follow.setFollowedName(usersFriendship.getUser1().getUserFname()+" "+usersFriendship.getUser1().getUserLname());
			follow.setFollowedEmail(usersFriendship.getUser1().getUserEmail());
			follow.setFollowedId(usersFriendship.getUser2().getUserId());
			follow.setFollowedName(usersFriendship.getUser2().getUserFname()+" "+usersFriendship.getUser2().getUserLname());
			follow.setFollowerEmail(usersFriendship.getUser2().getUserEmail());
			follow.setFollowDate(usersFriendship.getUfDate());
			followers.add(follow);
		}
		return followers;
	}

}
