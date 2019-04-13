package dtu.project.app;

import java.util.Date;
import java.util.List;

public class Project {

	private List<Activity> activities;
	private ProjectType projectType;
	private Date startDate;
	private Date endDate;

	public Project(ProjectType projectType, Date startDate, Date endDate) {
		super();
		this.projectType = projectType;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public List<Activity> getActivities() {return this.activities;}
	public void setActivities(List<Activity> activities) {this.activities = activities;}
	public ProjectType getProjectType() {return this.projectType;}
	public void setProjectType(ProjectType projectType) {this.projectType = projectType;}
	public Date getStartDate() {return this.startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public Date getEndDate() {return this.endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
}