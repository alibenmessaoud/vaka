package com.vaka.facade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.vaka.dao.EventDAO;
import com.vaka.model.Event;
import com.vaka.util.DateUtil;
import com.vaka.util.Message;
import com.vaka.util.UsernameValidator;

@Stateless
public class EventFacade {

	@EJB
	private EventDAO eventDAO = new EventDAO();
	UsernameValidator textValidator = new UsernameValidator();
	//DateUtil dateUtil = new DateUtil();

	public void C(Event e) {
		eventDAO.insert(e);
	}

	public Event R(int id) {
		return eventDAO.get(id);
	}

	public List<Event> L() {
		return eventDAO.list();
	}

	public Message eventAdd(Event e) {		
		Message message = new Message();
		String strMessage = "";
		if (e.getEventName().equals(""))
			strMessage = strMessage + "Event name is empty.<br>";
		if (!textValidator.validate(e.getEventName()))
			strMessage = strMessage + "Event name is invalid.<br>";
		if (!textValidator.validate(e.getEventAcronym()))
			strMessage = strMessage + "_Event abbreviation is invalid.<br>";
		if (e.getEventLocation().equals(""))
			strMessage = strMessage + "_Location is empty.<br>";
		if (!textValidator.validate(e.getEventLocation()))
			strMessage = strMessage + "_Location is invalid.<br>";		
		if (e.getEventStartDate().equals(""))
			strMessage = strMessage + "_Start date is empty.<br>";
//		if (dateUtil.convertToDate(e.getEventStartDate()) == null )
//			strMessage = strMessage + "_Start date is invalid format.<br>";
		if (e.getEventEndDate().equals(""))
			strMessage = strMessage + "_End date is empty.";
//		if (dateUtil.convertToDate(e.getEventEndDate()) == null )
//			strMessage = strMessage + "End date is invalid format.<br>";
		if (!textValidator.validate(e.getEventDescription()))
			strMessage = strMessage + "_Description is invalid.<br>";
		if (e.getUser().getUserId().equals(""))
			strMessage = strMessage + "_User is empty";
		if (e.getEventImage().equals(""))
			e.setEventImage("def.png");
		
		if (strMessage.length() == 0) {
			e.setEventDate(new Timestamp(new Date().getTime()));
			eventDAO.insert(e);			
			strMessage = "event-added";
		}
		message.setMessage(strMessage);
		message.setUserID(e.getEventId());

		return message;
	}

	public Message eventEdit(Event e) {		
		Message message = new Message();
		String strMessage = "";
		if (e.getEventName().equals(""))
			strMessage = strMessage + "10";
		if (!textValidator.validate(e.getEventName()))
			strMessage = strMessage + "_11";
		if (!textValidator.validate(e.getEventAcronym()))
			strMessage = strMessage + "_12";
		if (e.getEventLocation().equals(""))
			strMessage = strMessage + "_13";
		if (!textValidator.validate(e.getEventLocation()))
			strMessage = strMessage + "_14";
		if (e.getEventStartDate().equals(""))
			strMessage = strMessage + "_15";
		if (e.getEventEndDate().equals(""))
			strMessage = strMessage + "_16";
		if (!textValidator.validate(e.getEventDescription()))
			strMessage = strMessage + "_17";
		if (e.getUser().getUserId().equals(""))
			strMessage = strMessage + "_18";
		if (e.getEventImage().equals(""))
			e.setEventImage("default.png");
		
		if (strMessage.length() == 0) {			
			eventDAO.update(e);			
			strMessage = "event-updated";
		}
		message.setMessage(strMessage);
		message.setUserID(e.getEventId());

		return message;
	}

	public Message eventDelete(int id) {
		eventDAO.remove(id);
		Message message = new Message();
		message.setMessage("event-deleted");
		return message;
	}
	
	
	

	 

}
