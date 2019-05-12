package dtu.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dtu.project.entities.Activity;
import dtu.project.entities.Event;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.repo.UserRepository;
import javafx.util.Pair;

/**
 * 
 * @author Jonathan
 *
 */
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
    /**
     * 
     * @param user
     * @param startDate
     * @param endDate
     * @param activity
     * @param message
     */
    public void addHours(User user, String startDate, String endDate, Activity activity, String message) {
        getUserMap().get(user).add(new Event(startDate, endDate, activity, message));
        if(activity != null) {
        	if(activity.getRegisteredHours().isEmpty())
        		activity.getRegisteredHours().put(user, new ArrayList<>());
        	activity.getRegisteredHours().get(user).add(new TimePeriod(startDate, endDate));
        }
        	
    }
    
    
    
    
    
    public boolean overlaps(TimePeriod event1, TimePeriod event2) {
        return !(event1.getEndDate().isBefore(event2.getStartDate()) || event1.getStartDate().isAfter(event2.getEndDate()));
    }
    


	public Map<User, List<Event>> getUserMap() {
		return userRepository.getUserMap();
	}
	
	public List<User> getUserList() {
        return new ArrayList<>(getUserMap().keySet());
    }
	
}
