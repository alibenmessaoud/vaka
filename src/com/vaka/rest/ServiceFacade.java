package com.vaka.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vaka.dao.UserDAO;
import com.vaka.facade.EventFacade;
import com.vaka.facade.InvitedUserFacade;
import com.vaka.facade.PostFacade;
import com.vaka.facade.SessionFacade;
import com.vaka.facade.UserFacade;
import com.vaka.facade.UsersFriendshipFacade;
import com.vaka.model.*;
import com.vaka.util.Message;
import com.vaka.util.Signin;
import com.vaka.util.Utils;
import com.vaka.util.restFollow;
import com.vaka.util.restInvitation;
import com.vaka.util.restPost;
import com.vaka.util.restUser;

@Stateless
@Path("vaka")
public class ServiceFacade {

	@EJB
	UserDAO service;
	@EJB
	UserFacade UF;
	@EJB
	SessionFacade SF;
	@EJB
	EventFacade EF;
	@EJB
	UsersFriendshipFacade UFF;
	@EJB
	PostFacade PF;
	@EJB
	InvitedUserFacade IUF;
	
	Utils utils;
	static Logger l = Logger.getLogger(ServiceFacade.class.getName());
		
	public ServiceFacade() {
		PropertyConfigurator.configure("log4j.properties");		
	}
	
	@Path("/signin")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message signin(Signin s) {
		return UF.signin(s.getEmail(), s.getPassword(), s.getAppkey());
	}

	@Path("/signup")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message signup(User u) {
		return UF.signup(u);
	}
	
	
	@Path("users/{id}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public User getUser(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		User u = new User();
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UF.R(id);
		else
			return u;
	}
	
	@Path("users/{id}/email/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public String getUserEmail(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		User u = new User();
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UF.R(id).getUserEmail().toString();
		else
			return "faresjemni@gmail.com";
	}
	
	@Path("users/{iduser}/follow/{idfriend}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public Message follow(@PathParam("iduser") int iduser, @PathParam("idfriend") int idfriend, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");					
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UFF.followUser(iduser, idfriend);
		else
			return new Message();
	}
	
	@Path("users/{iduser}/unfollow/{idfriend}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public Message unfollow(@PathParam("iduser") int iduser, @PathParam("idfriend") int idfriend, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");					
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UFF.unfollowUser(iduser, idfriend);
		else
			return new Message();
	}
	
	@Path("users/{id}/followers/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public List<restFollow> getFollowers(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		List<restFollow> lf = new ArrayList<restFollow>();
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UFF.getFollowers(id);
		else
			return lf;
	}
	
	@Path("users/{id}/followings/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@GET
	public List<restFollow> getFollowings(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		List<restFollow> lf = new ArrayList<restFollow>();
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)			
			return UFF.getFollowings(id);
		else
			return lf;
	}
	
	
	@Path("users/edit/data")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message editUserData(User u){
		Message message = new Message();		 
		message.setMessage(UF.editUserData(u));	
		message.setUserID(u.getUserId());
		return message;
	}
	
	@Path("users/edit/email")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message editUserEmail(User u){
		Message message = new Message();		 
		message.setMessage(UF.editUserEmail(u));	
		message.setUserID(u.getUserId());
		return message;
	}
	
	@Path("users/edit/password")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message editUserPassword(User u){
		Message message = new Message();		 
		message.setMessage(UF.editUserPassword(u));	
		message.setUserID(u.getUserId());
		return message;
	}
	
	@Path("users/edit/location")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message editUserLocation(User u){
		Message message = new Message();		 
		message.setMessage(UF.editUserLocation(u));	
		message.setUserID(u.getUserId());
		return message;
	}
	
	@Path("/users/{sessionKey}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@GET
	public List<User> getUsers(@PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		List<User> lu = new ArrayList<User>();
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return UF.L();
		else
			return lu; 	 	
	}
	
	@Path("/users/location/{location}/{sessionKey}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@GET
	public List<User> getUsersByLocation(@PathParam("sessionKey") String sessionKey, @PathParam("location") String location) throws UnsupportedEncodingException {
		String decodedLocation = URLDecoder.decode(location, "UTF-8");
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		List<User> lu = new ArrayList<User>();			
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return UF.getUsersByLocation(decodedLocation);
		else
			return lu; 	 	
	}
	
	@Path("users/{id}/invitations/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<restInvitation> getUserInvitaions(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {		
		List<restInvitation> lri = new ArrayList<restInvitation>() ;
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return UF.userInvitaions(id);
		else
			return lri;			
	}

	@Path("users/{id}/events/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<Event> getUserEvents(	@PathParam("id") int id, 
									 	@PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		List<Event> le = new ArrayList<Event>() ;
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return UF.userEvents(id);
		else
			return le;
	}		

	@Path("users/{idUser}/events/{idEvent}/posts/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<Post> getUserEventPosts(@PathParam("idUser") int idUser,
			@PathParam("idEvent") int idEvent, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		 List<Post> lp = new ArrayList<Post>();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return UF.getUserEventPosts(idUser, idEvent);
		else
			return lp;
	}
	
	@Path("events/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<Event> vakaEvents( @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		List<Event> le = new ArrayList<Event>();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return EF.L();
		else
			return le;

	}
	
	@Path("events/{id}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Event getEvent(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		Event e = new Event();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return EF.R(id);
		else
			return e;
	}
	
	@Path("events/{id}/posts/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<restPost> getEventPosts(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		List<restPost> lrp = new ArrayList<restPost>();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return PF.getEventPosts(id);
		else
			return lrp;
	}
	
	@Path("posts/{id}/delete/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Message deleteEventPost(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		Message message = new Message();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return PF.deleteEventPost(id);
		else
			return message;
	}
	
	
	@Path("events/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message eventAdd(Event e){		
		return (EF.eventAdd(e));
	}
	
	@Path("events/edit")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	@POST
	public Message eventEdit(Event e){		
		return (EF.eventEdit(e));
	}
	
	@Path("events/{id}/delete/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Message eventDelete(@PathParam("id") int id, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		Message message = new Message();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return EF.eventDelete(id);
		else
			return message;
	}
	
	//@Path("invite")
	@Path("events/{ide}/invite/from/{idu1}/to/{idu2}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	//@POST
	public InvitedUser inviteOne(@PathParam("ide") int ide, @PathParam("idu1") int idu1, @PathParam("idu2") int idu2, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		InvitedUser iu = new InvitedUser();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return IUF.inviteOne(ide, idu1, idu2);
		else
			return iu;
		//return IUF.C(iu);		
	}
	
	
	@Path("events/{ide}/users/{idu}/state/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public InvitedUser userEventAttendState(@PathParam("ide") int ide, @PathParam("idu") int idu, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		InvitedUser iu = new InvitedUser();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return IUF.userEventAttendState(ide, idu);
		else
			return iu;
	}
	
	
	@Path("events/{ide}/users/{idu}/state/{state}/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public InvitedUser userEventChangeState(@PathParam("ide") int ide, @PathParam("idu") int idu, @PathParam("sessionKey") String sessionKey, @PathParam("state") String state) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		String decodedState = URLDecoder.decode(state, "UTF-8");
		InvitedUser iu = new InvitedUser();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return IUF.userEventChangeState(ide, idu, state);
		else
			return iu;
	}
	
	@Path("events/{ide}/users/{idu}/invitor/{sessionKey}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public User userEventInvitor(@PathParam("ide") int ide, @PathParam("idu") int idu, @PathParam("sessionKey") String sessionKey) throws UnsupportedEncodingException {
		String decodedSessionKey = URLDecoder.decode(sessionKey, "UTF-8");
		User u = new User();
		if (SF.verifySessionKey(decodedSessionKey)==1)
			return IUF.userEventInvitor(ide, idu);
		else
			return u;
	}
	
	
	
	/////////////////////////////////////////////
	/*
	 * public User show(@PathParam("id") String id) { if (utils.isInteger(id))
	 * return service.get(Integer.parseInt(id)); else return service.get(1); }
	 */



}