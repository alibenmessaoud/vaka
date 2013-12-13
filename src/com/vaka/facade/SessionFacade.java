package com.vaka.facade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.vaka.dao.SessionDAO;
import com.vaka.model.Session;
import com.vaka.model.User;
import com.vaka.util.Utils;

@Stateless
public class SessionFacade {

	@EJB
	private SessionDAO sessionDAO = new SessionDAO();	

	public void C(Session session) {
		sessionDAO.insert(session);
	}

	public Session R(int id) {
		return sessionDAO.get(id);
	}

	public List<Session> L() {
		return sessionDAO.list();
	}

	public Session createSession(Integer userId) {
		SessionDAO sessionDAO = new SessionDAO();
		Session session = new Session();
		User user = new User();
		user.setUserId(userId);
		session.setSessionDate(new Timestamp(new Date().getTime()));
		session.setUser(user);
		session.setSessionKey(new Utils().randomKey());
		sessionDAO.insert(session);
		return session;
	}
	
	public int verifySessionKey(String key)
	{
		SessionDAO sessionDAO = new SessionDAO();
		List<Session> sessionList = sessionDAO.getLastSessionByKey(key);
		if (sessionList.size()==1)
			return 1;
		else
			return 0;		
	}

}
