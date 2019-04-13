package dtu.project.app;

import java.util.Date;

public class Event {

	private String eventName;
	private Date startDate;
	private Date endDate;

	public Event(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public double computeHours() {
		return (this.endDate.getTime() - this.startDate.getTime())/3600000;
	}
	
	public String getEventName() {return this.eventName;}
	public void setEventName(String eventName) {this.eventName = eventName;}
	public Date getStartDate() {return this.startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public Date getEndDate() {return this.endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
}