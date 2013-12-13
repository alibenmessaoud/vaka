package com.vaka.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.Event;
import com.vaka.model.InvitedUser;
import com.vaka.model.Session;
import com.vaka.model.User;
 
@Stateless
public class InvitedUserDAO extends DAO<InvitedUser> {
	
    public InvitedUserDAO() {
        super(InvitedUser.class);
    }

	@Override
	public String toString() {
		return "InvitedUser [toString()=" + super.toString() + "]";
	}

	public List<InvitedUser> userEventAttendState(Event e, User u) {
		Query query = em.createQuery("select i from InvitedUser i where i.user2 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
	}

	public List<InvitedUser> userEventVerifyState(Event e, User u) {
		Query query = em.createQuery("select i from InvitedUser i where i.user2 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
		
	}
	
	public List<InvitedUser> userEventVerifyState(int ide, int idu) {
		User u = new UserDAO().get(idu);
		Event e = new EventDAO().get(ide);
		Query query = em.createQuery("select i from InvitedUser i where i.user2 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
		
	}  
}