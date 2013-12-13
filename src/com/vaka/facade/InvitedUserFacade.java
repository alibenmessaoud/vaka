package com.vaka.facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.vaka.dao.EventDAO;
import com.vaka.dao.InvitedUserDAO;
import com.vaka.dao.UserDAO;
import com.vaka.model.Event;
import com.vaka.model.InvitedUser;
import com.vaka.model.User;
import com.vaka.util.Message;

@Stateless
public class InvitedUserFacade {

	@EJB
	private InvitedUserDAO iuDAO = new InvitedUserDAO();
	@EJB
	private UserDAO ud;
	@EJB
	private EventDAO ed;

	public InvitedUser C(InvitedUser iu) {
		return iuDAO.insert(iu);
	}

	public InvitedUser R(int id) {
		return iuDAO.get(id);
	}

	public List<InvitedUser> L() {
		return iuDAO.list();
	}

	public InvitedUser userEventAttendState(int ide, int idu) {
		Event e = ed.get(ide);
		User u = ud.get(idu);
		InvitedUser iuDefault = new InvitedUser();
		iuDefault.setIuState("None");
		if (iuDAO.userEventVerifyState(e, u).size() == 1) {
			List<InvitedUser> ius = iuDAO.userEventAttendState(e, u);
			InvitedUser iu = ius.get(0);
			return iu;
		} else
			return iuDefault;
	}

	public InvitedUser userEventChangeState(int ide, int idu, String state) {
		Event e = ed.get(ide);
		User u = ud.get(idu);
		InvitedUser iu = null;
		if (iuDAO.userEventVerifyState(e, u).size() == 1) {
			iu = iuDAO.userEventAttendState(e, u).get(0);
			iu.setIuState(state);
			iuDAO.update(iu);
		}
		return iu;
	}

	public User userEventInvitor(int ide, int idu) {
		Event e = ed.get(ide);
		User u = ud.get(idu);
		User i = null;
		InvitedUser iu = null;
		if (iuDAO.userEventVerifyState(e, u).size() == 1) {
			iu = iuDAO.userEventAttendState(e, u).get(0);
			i = iu.getUser1();
		}
		return i;
	}

	public InvitedUser inviteOne(int ide, int idu1, int idu2) {
		/*
		 * EventFacade ef = new EventFacade(); UserFacade uf = new UserFacade();
		 * Event e = ef.R(ide) ; User u1 = uf.R(idu1); User u2 = uf.R(idu2);
		 * 
		 * InvitedUser iu = new InvitedUser(); if (iuDAO.userEventVerifyState(e,
		 * u2).size()==0) { iu.setEvent(e); iu.setUser1(u1); iu.setUser2(u2);
		 * iu.setIuState("None"); iu.setIuDate(new Timestamp(new
		 * Date().getTime())); iuDAO.insert(iu); }
		 */
		InvitedUser iu = new InvitedUser();
		Connection con = null;
		String url = "jdbc:postgresql://localhost:5432/";
		String db = "vaka2";
		String driver = "org.postgresql.Driver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + db, "postgres", "postgres");
			if (iuDAO.userEventVerifyState(ide, idu2).size() == 0)
				try {
					Statement st = con.createStatement();
					String s = "INSERT into invited_users VALUES( nextval('invited_users_iu_id_seq'::regclass), "
							+ idu1
							+ ","
							+ idu2
							+ ","
							+ ide
							+ ", 'None', now() )";
					//int val = st.executeUpdate(s);
					int val = performKeys(s);
					System.out.println(val + " -------------- ");
					iu = iuDAO.get(val);
				} catch (SQLException s) {
				}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return iu;
	}

	public int performKeys(String Query) throws SQLException, ClassNotFoundException {
		Connection con = null;
		String url = "jdbc:postgresql://localhost:5432/";
		String db = "vaka2";
		String driver = "org.postgresql.Driver";
		Class.forName(driver);
		con = DriverManager.getConnection(url + db, "postgres", "postgres");
		
		PreparedStatement pstmt;
		int key = 0;
		try {
			pstmt = con.prepareStatement(Query,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();

			keys.next();
			key = keys.getInt(1);
			keys.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

}
