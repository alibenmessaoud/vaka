package com.vaka.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.vaka.dao.UserDAO;
import com.vaka.dao.UsersFriendshipDAO;
import com.vaka.facade.InvitedUserFacade;
import com.vaka.facade.PostFacade;
import com.vaka.facade.UsersFriendshipFacade;
import com.vaka.model.User;

public class Main {
	public static void main(String[] args) throws Exception {
		
		//PostFacade pf = new PostFacade();
		//System.out.println(pf.getEventPosts(1).size());
		for (int i = 1; i <= 40 ; i++) {
			System.out.println("errorMessages["+i+"]=\"\";");
		}
		
		
		
		/*
		UsersFriendshipDAO ufd = new UsersFriendshipDAO();
		UserDAO ud = new UserDAO();
		User u1 = ud.get(1);
		User u2 = ud.get(2);
		UsersFriendshipFacade uff = new UsersFriendshipFacade();
		System.out.println(uff.followUser(1, 2).toString());		
		
		System.out.println(ufd.isUserFollowingUser(u1, u2));
		
		System.out.println(uff.L().size());
		
		//System.out.println(uff.unfollowUser(1, 2));
		
		System.out.println(uff.L().size());
		
		System.out.println(uff.getFollowers(2).size());
		*/
		
		// results[1].geometry.location.lat
		//geocoding("kairouan");
		
		/*
		 * UserDAO ud = new UserDAO(); User u = ud.get(1);
		 * System.out.println(u.getUserFname());
		 * System.out.println(ud.userInvitaions
		 * (u).get(0).getEvent().getEventName());
		 */

		/*
		 * UserDAO ud = new UserDAO(); User u = new User();
		 * u.setUserFname("Salahos"); // ud.insert(u);
		 * System.out.println(ud.list());
		 * 
		 * // UserFacade uf = new UserFacade(); //
		 * System.out.println(uf.R(10).getUserFname());
		 * 
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("test"); EntityManager em =
		 * emf.createEntityManager(); Query query =
		 * em.createNamedQuery("User.findByName");
		 * query.setParameter("userFname", "Salah"); List<User> employees =
		 * (List<User>) query.getResultList(); System.out.println("employees " +
		 * employees);
		 * 
		 * System.out.println(Long.toString(Math.abs(
		 * UUID.randomUUID().getLeastSignificantBits())));
		 */
		/*
		 * UserDAO ud = new UserDAO(); User u = ud.get(9);
		 * 
		 * System.out.println(u);
		 * 
		 * SessionDAO sd = new SessionDAO(); Session s = new Session();
		 * s.setSessionKey(new Utils().randomKey()); s.setSessionDate(new
		 * Timestamp(new Date().getTime())); s.setUser(u); sd.insert(s);
		 * 
		 * System.out.println(s);
		 */

		// UserFacade uf = new UserFacade();

		// System.out.println(uf.signin("mail2alibm@gmail.com",
		// "mail2alibm@gmail.com", "adkav"));
		/*
		 * Gson g = new Gson(); JsonObject jso = new
		 * JsonParser().parse(uf.signin("al", "s",
		 * "akdav").toString()).getAsJsonObject();
		 * System.out.println(jso.get("message").getAsString());
		 */
		// UserDAO ud = new UserDAO();
		// System.out.println(ud.authUser("mail2alibm@gmail.com",
		// "mail2alibm@gmail.com").size());

		/*
		 * SessionDAO sd = new SessionDAO(); Session s = new Session(); User u =
		 * new User(); u.setUserId(2); s.setSessionDate(new Timestamp(new
		 * Date().getTime())); s.setUser(u); s.setSessionKey("aliii");
		 * sd.insert(s);
		 */

		/*
		 * 
		 * UserDAO ud = new UserDAO(); SessionDAO sd = new SessionDAO(); User u
		 * = ud.get(3);
		 * //System.out.println(sd.getLastSessionByUser(u).get(0).getSessionKey
		 * ()); SessionFacade sf = new SessionFacade();
		 * System.out.println(sf.verifySessionKey("413352099"));
		 */
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }

	}
	
	
	public static void geocoding(String addr) throws Exception
	{
	    String s = "http://maps.google.com/maps/api/geocode/json?sensor=false&address="+URLEncoder.encode(addr, "UTF-8");
	    URL url = new URL(s);
	 
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	 
	    JSONObject obj = new JSONObject(str);
	    if (! obj.getString("status").equals("OK"))
	        return;
	 
	    JSONObject res = obj.getJSONArray("results").getJSONObject(0);
	    System.out.println(res.getString("formatted_address"));
	    JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");
	    System.out.println("lat: " + loc.getDouble("lat") + ", lng: " + loc.getDouble("lng"));
	}
}