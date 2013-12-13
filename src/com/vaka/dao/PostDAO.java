package com.vaka.dao;
 
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.vaka.model.Event;
import com.vaka.model.InvitedUser;
import com.vaka.model.Post;
import com.vaka.model.User;
import com.vaka.util.Message;
 
@Stateless
public class PostDAO extends DAO<Post> {
	
    public PostDAO() {
        super(Post.class);
    }

	@Override
	public String toString() {
		return "PostDAO [toString()=" + super.toString() + "]";
	}
    
	public List<Post> getUserEventPosts(User u, Event e)
	{
		Query query = em.createQuery("select p from Post p where p.user = :user and p.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		return (List<Post>) query.getResultList();		
	}

	public List<Post> getEventPosts(Event e) {
		Query query = em.createQuery("select p from Post p where p.event = :event");
		query.setParameter("event", e);
		return (List<Post>) query.getResultList();
	}    
}