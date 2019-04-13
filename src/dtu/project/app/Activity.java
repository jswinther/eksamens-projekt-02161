package dtu.project.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {

	private List<User> assignedUsers;
	private int estimatedHours;
	private int registeredHours;
	private Date startDate;
	private Date endDate;
	private User activityResponsibleUser;
	private Project project;

	public Activity(int estimatedHours, Date startDate, Date endDate, User activityResponsibleUser, Project project) {
		super();
		this.assignedUsers = new ArrayList<User>();
		this.estimatedHours = estimatedHours;
		this.registeredHours = 0;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activityResponsibleUser = activityResponsibleUser;
		this.project = project;
	}
	
	public List<User> getAssignedUsers() {return this.assignedUsers;}
	public void setAssignedUsers(List<User> assignedUsers) {this.assignedUsers = assignedUsers;}
	public int getEstimatedHours() {return this.estimatedHours;}
	public void setEstimatedHours(int estimatedHours) {this.estimatedHours = estimatedHours;}
	public int getRegisteredHours() {return this.registeredHours;}
	public void setRegisteredHours(int registeredHours) {this.registeredHours = registeredHours;}
	public Date getStartDate() {return this.startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public Date getEndDate() {return this.endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	public User getActivityResponsibleUser() {return this.activityResponsibleUser;}
	public void setActivityResponsibleUser(User activityResponsibleUser) {this.activityResponsibleUser = activityResponsibleUser;}
	public Project getProject() {return this.project;}
	public void setProject(Project project) {this.project = project;}

}