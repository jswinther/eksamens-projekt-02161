package dtu.project.app;

import dtu.project.enums.ProjectType;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private String projectName;
	private ProjectType projectType;
	private Event timePeriod;
	private List<Activity> activities;

	public Project(String projectName, ProjectType projectType) {
		this.projectName = projectName;
		this.projectType = projectType;
		this.timePeriod = null;
		this.activities =  new ArrayList<>();
	}

	public Project(String projectName, ProjectType projectType, Event timePeriod) {
		this.projectName = projectName;
		this.projectType = projectType;
		this.timePeriod = timePeriod;
		this.activities =  new ArrayList<>();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Activity> getActivities() {return this.activities;}
	public void setActivities(List<Activity> activities) {this.activities = activities;}
	public ProjectType getProjectType() {return this.projectType;}
	public void setProjectType(ProjectType projectType) {this.projectType = projectType;}
	public Event getTimePeriod() {return timePeriod;}
	public void setTimePeriod(Event timePeriod) {this.timePeriod = timePeriod;}
	public void addActivity(Activity activity) {this.activities.add(activity);}
	public void removeActivity(Activity activity) {this.activities.remove(activity);}

	@Override
	public String toString() {
		return "Project{" +
				"projectName='" + projectName + '\'' +
				", projectType=" + projectType +
				", timePeriod=" + timePeriod +
				", activities=" + activities +
				'}';
	}
}