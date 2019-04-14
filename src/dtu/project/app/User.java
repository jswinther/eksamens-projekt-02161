package dtu.project.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

	private String name;
	private String idName;
	private Map<Activity, List<Event>> schedule = new HashMap<>();

	public Map<Activity, List<Event>> getSchedule() {
		return schedule;
	}

	public void setSchedule(Map<Activity, List<Event>> schedule) {
		this.schedule = schedule;
	}

	public void addHours(Activity activity, Event event) {
		if(!schedule.containsKey(activity)) {
			schedule.put(activity, new ArrayList<Event>() {{
				add(event);
			}});
			activity.setRegisteredHours(activity.getRegisteredHours() + (event.getEndDate().getHour() - event.getStartDate().getHour()));
		} else {
			schedule.get(activity).add(event);
			activity.setRegisteredHours(activity.getRegisteredHours() + (event.getEndDate().getHour() - event.getStartDate().getHour()));
		}
	}

	public User(String name) {
		super();
		this.name = name;
		if (name.length() > 4) {
		    this.idName = name.substring(0, 4).toLowerCase();
		} else {
		    this.idName = name.toLowerCase();
		    while(idName.length() < 4) {
		    	idName += "x";
		    }
		}
	}
	
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	public String getIdName() {return this.idName;}
	public void setIdName(String idName) {this.idName = idName;}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", idName='" + idName;
	}
}