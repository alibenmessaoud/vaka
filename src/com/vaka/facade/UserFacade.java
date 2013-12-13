package com.vaka.facade;

import java.sql.Timestamp;
import java.util.*;

import javax.ejb.*;

import com.vaka.dao.*;
import com.vaka.model.*;
import com.vaka.util.*;

@Stateless
public class UserFacade {

	@EJB
	private UserDAO userDAO;
	@EJB
	private EventDAO eventDAO;
	@EJB
	private PostDAO postDAO;
	
	public void C(User u) {
		userDAO.insert(u);
	}
	
	
	public User R(int id) {
		User u = new User();		
		u = userDAO.get(id);
		//u.setUserPassword("********");
		//u.setUserDate(null);
		return u;
	}
	
	public User U(User u) {				
		u = userDAO.update(u);		
		return u;
	}

	public List<User> L() {
		return userDAO.list();
	}

	public Message signin(String email, String password, String appkey) {
		User u = new User();
		Session session = new Session();
		String message = new String("");
		Message messageObject = new Message();
		
		//System.out.println(userDAO.authUser(email, password).size() );
		
		if (!appkey.equalsIgnoreCase("akav")) {
			message = "wrong-app-key";
			u.setUserId(-1);
			session.setSessionKey("");

			messageObject.setMessage(message);
			messageObject.setUserID(-1);
			messageObject.setSessionKey("");
		} else {
			if (userDAO.authUser(email, password).size() == 1) {
				u = userDAO.authUser(email, password).get(0);
				session = new SessionFacade().createSession(u.getUserId());
				message = "signin-successfull";

				messageObject.setMessage(message);
				messageObject.setUserID(u.getUserId());
				messageObject.setSessionKey(session.getSessionKey());
			} else {
				u.setUserId(-1);
				session.setSessionKey("");
				message = "wrong-user-password";

				messageObject.setMessage(message);
				messageObject.setUserID(-1);
				messageObject.setSessionKey("");
			}
		}

		/*
		 * Gson gson = new Gson(); HashMap<String, String> map = new
		 * HashMap<>(); map.put("user-id", u.getUserId().toString());
		 * map.put("message", message); map.put("session-key",
		 * session.getSessionKey());
		 * 
		 * return gson.toJson(map);
		 */
		return messageObject;
	}

	public Message signup(User u) {
		Session session = null;
		String message = "";		 

		Message messageObject = new Message();

		if (u.getUserFname().equals(""))
			message = message + "First name field is empty.";
		if (u.getUserLname().equals(""))
			message = message + "_Last name field is empty.";
		if (!new EmailValidator().validate(u.getUserEmail()))
			message = message + "_Invalid email.";
		if (userDAO.findUserByEmail(u.getUserEmail()).size() >= 1)
			message = message + "_Email is used.";
		if (u.getUserPassword().length() < 8
				|| u.getUserPassword().length() > 20)
			message = message + "_Password length must be between 8 and 20 chars.";

		if (message.length() == 0) {
			u.setUserDate(new Timestamp(new Date().getTime()));
			userDAO.insert(u);
			message = "user-signup-successfull";
		}

		messageObject.setMessage(message);
		messageObject.setUserID(-1);
		messageObject.setSessionKey("");

		return messageObject;
	}

	public List<restInvitation> userInvitaions(int id)
	{
		User u = userDAO.get(id);
		restInvitation ri = new restInvitation();
		List<restInvitation> invitationList = new ArrayList<restInvitation>();
		List<InvitedUser> invitedUsers = userDAO.userInvitaions(u);
		Event e = new Event();
		
	    for (InvitedUser invitedUser : invitedUsers) {	    	
	    	e = invitedUser.getEvent();
	    	u = invitedUser.getUser1();
	    	
	    	ri.setEventId(e.getEventId());
	    	ri.setEventAcronym(e.getEventAcronym());
	    	ri.setEventName(e.getEventName());
	    	ri.setEventStartDate(e.getEventStartDate());
	    	ri.setEventEndDate(e.getEventEndDate());
	    	ri.setEventImage(e.getEventImage());
	    	ri.setEventLocation(e.getEventLocation());
	    	ri.setInvitorId(u.getUserId());
	    	ri.setInvitorFName(u.getUserFname());
	    	ri.setInvitorLName(u.getUserLname());
	    	ri.setIuState(invitedUser.getIuState());
	    	ri.setIuDate(invitedUser.getIuDate());
	    	
	    	invitationList.add(ri);
	    }
		return invitationList;
	}

	public List<Event> userEvents(int id) {		
		User u = userDAO.get(id);
		List<Event> eventList = eventDAO.getUserEvents(u);
		return eventList;
	}

	public List<Post> getUserEventPosts(int UserID, int EventID) {
		User u = userDAO.get(UserID);
		Event e = eventDAO.get(EventID);		
		return postDAO.getUserEventPosts(u,e);
	}

	public List<User> getUsersByLocation(String decodedLocation) {
		return userDAO.getUsersByLocation(decodedLocation);
	}
	
	
	public String editUserData(User u){
		String message = "";
		if (userDAO.get(u.getUserId()) instanceof User)
		{
			userDAO.update(u);
			message=("user-data-updated");
		}
		else
			message=("wrong-user-data");
		return message;
	}


	public String editUserEmail(User u) {
		String message = "";
		if (userDAO.get(u.getUserId()) instanceof User)
		{
			if (userDAO.findUserByEmail(u.getUserEmail()).size() >= 1)
				message = "user-email-data-exist";
			else if (!new EmailValidator().validate(u.getUserEmail()))
				message = "user-email-data-invalid";
			else
			{
				userDAO.update(u);
				message=("user-email-data-updated");
			}			
		}
		else
			message=("user-email-data-wrong");
		return message;
	}


	public String editUserPassword(User u) {
		String message = "";
		if (userDAO.get(u.getUserId()) instanceof User)
		{
			if (u.getUserPassword().length() < 8
					|| u.getUserPassword().length() > 20)
				message="user-password-data-length";
			else
			{
				userDAO.update(u);
				message=("user-password-data-updated");
			}			
		}
		else
			message=("user-password-data-wrong");
		return message;
	}


	public String editUserLocation(User u) {
		String message = "";
		if (userDAO.get(u.getUserId()) instanceof User)
		{
			if (u.getUserAvatar().isEmpty())
				u.setUserAvatar("Tunisia");			
			
				userDAO.update(u);
				message=("user-location-data-updated");						
		}
		else
			message=("user-location-data-wrong");
		return message;
	}
	
}
