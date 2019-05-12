/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.entities;

/**
 * Every function pretaining to what can happen inside an event.
 * An event is just a storage unit which can hold a timeperiod, an activity and a message.
 * Every function inside this class is a constructor and getter and setters.  
 * @author Nicholas
 */
public class Event {
    private TimePeriod timePeriod;
    private Activity activity;
    private String message;

    // Constructor, takes preconditions that activity and message isn't null
	public Event(String startDate, String endDate, Activity activity, String message) {
        if(activity != null)
            this.activity = activity;
        if(message != null)
            this.message = message;
        this.timePeriod = new TimePeriod(startDate, endDate);
    }
	
	// Getters and setters
	
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    // To string
    
    @Override
    public String toString() {
        return (message != null ? message : "") + (activity != null ? activity.getActivityName() : "") + " " + timePeriod;
    }
    
    
    
}
