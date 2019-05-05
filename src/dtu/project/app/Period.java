/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.app;

import java.util.Optional;

/**
 *
 * @author Jonathan
 */
public class Period {
    private Event timePeriod;
    private Activity activity;
    private String message;

    
    
    public Period(String startDate, String endDate, Activity activity, String message) {
        if(activity != null)
            this.activity = activity;
        if(message != null)
            this.message = message;
        this.timePeriod = new Event(startDate, endDate);
    }

    public Event getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Event timePeriod) {
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

    @Override
    public String toString() {
        return message + (activity != null ? activity.getActivityName() : "") + " " + timePeriod;
    }
    
    
    
}
