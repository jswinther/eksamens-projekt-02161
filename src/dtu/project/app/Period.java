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
    private Optional<Activity> activity;
    private Optional<String> message;

    
    
    public Period(String startDate, String endDate, Optional<Activity> activity, Optional<String> message) {
        if(activity.isPresent())
            this.activity = activity;
        if(message.isPresent())
            this.message = message;
        this.timePeriod = new Event(startDate, endDate);
    }

    public Event getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Event timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Optional<Activity> getActivity() {
        return activity;
    }

    public void setActivity(Optional<Activity> activity) {
        this.activity = activity;
    }
    
    public Optional<String> getMessage() {
        return message;
    }

    public void setMessage(Optional<String> message) {
        this.message = message;
    }
    
}
