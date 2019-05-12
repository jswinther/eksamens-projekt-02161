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
 * Used to manipulate data in the user repository.
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
     * Adds an event to schedule of the specified user. If activity is specified,
     * there is also added to registered hours of that activity.
     * @param user
     * @param startDate yyyy-MM-dd HH:mm
     * @param endDate yyyy-MM-dd HH:mm
     * @param activity can be null
     * @param message can be null
     */
    public void addHours(User user, String startDate, String endDate, Activity activity, String message) {
        getUserMap().get(user).add(new Event(startDate, endDate, activity, message));
        if(activity != null) {
        	if(activity.getRegisteredHours().isEmpty())
        		activity.getRegisteredHours().put(user, new ArrayList<>());
        	activity.getRegisteredHours().get(user).add(new TimePeriod(startDate, endDate));
        }
        	
    }
    
    /**
     * Checks to see if two events overlap.
     * @param event1
     * @param event2
     * @return
     */
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
