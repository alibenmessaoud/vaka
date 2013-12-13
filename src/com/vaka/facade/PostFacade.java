package com.vaka.facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.vaka.dao.DAO;
import com.vaka.dao.EventDAO;
import com.vaka.dao.PostDAO;
import com.vaka.dao.SessionDAO;
import com.vaka.model.Event;
import com.vaka.model.Post;
import com.vaka.model.Session;
import com.vaka.model.User;
import com.vaka.rest.ServiceFacade;
import com.vaka.util.Message;
import com.vaka.util.Utils;
import com.vaka.util.restPost;

@Stateless
public class PostFacade {
	static Logger l = Logger.getLogger(PostFacade.class.getName());
	@EJB
	private PostDAO postDAO = new PostDAO();	
	@EJB
	private EventDAO eventDAO = new EventDAO();
	
	public void C(Post post) {
		postDAO.insert(post);
	}

	public Post R(int id) {
		return postDAO.get(id);
	}

	public List<Post> L() {
		return postDAO.list();
	}

	public List<restPost> getEventPosts(int id) {
		Event e = eventDAO.get(id);
		List<restPost> lrp = new ArrayList<restPost>();
		restPost rp = new restPost();
		User u = new User();
		if (e instanceof Event)
		{
			List<Post> lp = postDAO.getEventPosts(e);			
			for (Post post : lp) {
				u = post.getUser();
				rp.setPostId(post.getPostId());
				rp.setUserId(u.getUserId());
				rp.setUserName(u.getUserFname() + " " + u.getUserLname());
				rp.setUserEmail(u.getUserEmail());
				rp.setPostText(post.getPostText());
				rp.setPostDate(post.getPostDate());
				lrp.add(rp);
			}
		}
		l.info("List<restPost> getEventPosts(int id = "+id+") : return " + lrp.size() ); 
		return lrp;
	}

	public Message deleteEventPost(int id) {
		Message message = new Message();
		Post p = postDAO.get(id);
		postDAO.remove(id);
		message.setMessage("event-post-deleted");
		return message;
	}
	 

}
