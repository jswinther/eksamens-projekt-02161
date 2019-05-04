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
		return name;
	}
}