package dtu.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dtu.project.entities.Activity;
import dtu.project.entities.Event;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.repo.UserRepository;

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
    }
    
    
    
    public List<User> getFreeUsers(String startDate, String endDate) {
        TimePeriod event = new TimePeriod(startDate, endDate);
        List<User> users = getUserList();

        for (Map.Entry<User, List<Event>> entry : getUserMap().entrySet()) {
            User key = entry.getKey();
            List<Event> value = entry.getValue();
            for (Event period : value) {
                if (overlaps(event, period)) {
                    users.remove(key);
                }
            }
        }
        return users;
    }
    
    private boolean overlaps(TimePeriod event, Event period) {
        return !(event.getEndDate().isBefore(period.getTimePeriod().getStartDate()) || event.getStartDate().isAfter(period.getTimePeriod().getEndDate()));
    }
    


	public Map<User, List<Event>> getUserMap() {
		return userRepository.getUserMap();
	}
	
	public List<User> getUserList() {
        return new ArrayList<>(getUserMap().keySet());
    }
	
}
