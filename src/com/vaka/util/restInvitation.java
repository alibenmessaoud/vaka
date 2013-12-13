package com.vaka.util;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Invitation", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
public class restInvitation {
	
	private Integer eventId;
	private String eventName;
	private String eventAcronym;
	private String eventStartDate;
	private String eventEndDate;
	private String eventImage;
	private String eventLocation;
	
	private Integer invitorId;
	private String invitorFName;
	private String invitorLName;
	
	private String iuState;
	private Timestamp iuDate;
	
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventAcronym() {
		return eventAcronym;
	}
	public void setEventAcronym(String eventAcronym) {
		this.eventAcronym = eventAcronym;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventImage() {
		return eventImage;
	}
	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getInvitorFName() {
		return invitorFName;
	}
	public void setInvitorFName(String invitorFName) {
		this.invitorFName = invitorFName;
	}
	public String getInvitorLName() {
		return invitorLName;
	}
	public void setInvitorLName(String invitorLName) {
		this.invitorLName = invitorLName;
	}
	public String getIuState() {
		return iuState;
	}
	public void setIuState(String iuState) {
		this.iuState = iuState;
	}
	public Timestamp getIuDate() {
		return iuDate;
	}
	public void setIuDate(Timestamp iuDate) {
		this.iuDate = iuDate;
	}
	public Integer getInvitorId() {
		return invitorId;
	}
	public void setInvitorId(Integer invitorId) {
		this.invitorId = invitorId;
	}	
	
	
}
