package com.vaka.dao;
 
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.InvitedUser;
import com.vaka.model.Session;
import com.vaka.model.User;
 
//@Stateless
public class SessionDAO extends DAO<Session> {
	
    public SessionDAO() {
        super(Session.class);
    }

	@Override
	public String toString() {
		return "SessionDAO [toString()=" + super.toString() + "]";
	}
	
	public List<Session> getLastSessionByUser(User u)
	{
		Query query = em.createQuery("select s from Session s where s.user = :user order by s.sessionId desc");
		query.setParameter("user", u);
		query.setMaxResults(1);
		return (List<Session>) query.getResultList();		
	}
	
	public List<Session> getLastSessionByKey(String key)
	{
		Query query = em.createQuery("select s from Session s where s.sessionKey = :sessionKey");
		query.setParameter("sessionKey", key);		
		return (List<Session>) query.getResultList();		
	}
    
    
}