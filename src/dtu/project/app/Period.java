/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jonathan
 */
public class Period {
    private Event timePeriod;
    private Activity activity;
    private String message;

    
    
    public LocalDateTime getStartDate() {
		return timePeriod.getStartDate();
	}

	public void setStartDate(LocalDateTime startDate) {
		timePeriod.setStartDate(startDate);
	}

	public LocalDateTime getEndDate() {
		return timePeriod.getEndDate();
	}

	public void setEndDate(LocalDateTime endDate) {
		timePeriod.setEndDate(endDate);
	}

	public List<User> getUsers() {
		return activity.getUsers();
	}

	public void addUser(User user) {
		activity.addUser(user);
	}

	public void removeUser(User user) {
		activity.removeUser(user);
	}

	public void setUsers(List<User> users) {
		activity.setUsers(users);
	}

	public int getEstimatedHours() {
		return activity.getEstimatedHours();
	}

	public void setEstimatedHours(int estimatedHours) {
		activity.setEstimatedHours(estimatedHours);
	}

	public Map<User, List<Event>> getRegisteredHours() {
		return activity.getRegisteredHours();
	}

	public void setRegisteredHours(Map<User, List<Event>> registeredHours) {
		activity.setRegisteredHours(registeredHours);
	}

	public String getActivityName() {
		return activity.getActivityName();
	}

	public void setActivityName(String activityName) {
		activity.setActivityName(activityName);
	}

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
