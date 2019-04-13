package dtu.project.app;

import java.util.List;
import java.util.Map;

public abstract class User {

	private String name;
	private Map<Activity, List<Event>> activityMap;
	private String idName;

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
	public Map<Activity, List<Event>> getActivityMap() {return this.activityMap;}
	public void setActivityMap(Map<Activity, List<Event>> activityMap) {this.activityMap = activityMap;}
	public String getIdName() {return this.idName;}
	public void setIdName(String idName) {this.idName = idName;}
}