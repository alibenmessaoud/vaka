package com.vaka.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.Event;
import com.vaka.model.User;

@Stateless
public class EventDAO extends DAO<Event> {

	public EventDAO() {
		super(Event.class);
	}

	@Override
	public String toString() {
		return "EventDAO [toString()=" + super.toString() + "]";
	}

	public List<Event> findEventByName(String name) {
		Query query = em.createQuery("select u from Event"); 		
		return (List<Event>) query.getResultList();
	}
	
	public List<Event> getUserEvents(User u) {
		Query query = em.createQuery("SELECT e FROM Event e where e.user = :user"); 		
		query.setParameter("user", u);
		return (List<Event>) query.getResultList();
	}
}