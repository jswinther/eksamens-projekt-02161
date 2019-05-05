package dtu.project.app;

import dtu.project.enums.ProjectType;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Project {

	private String projectName;
	private ProjectType projectType;
	private Event timePeriod;
	private List<Activity> activities;
	private User projectManager;

	private Project(String projectName, ProjectType projectType, Event timePeriod, List<Activity> activities, User projectManager) {
		this.projectName = projectName;
		this.projectType = projectType;
		this.timePeriod = timePeriod;
		this.activities = activities;
		this.projectManager = projectManager;
	}

	/**
	 * Jonathan Inspiration is drawn from the source below.
	 * https://medium.com/@ajinkyabadve/builder-design-patterns-in-java-1ffb12648850
	 * Using builder pattern allows for easier creation objects and simplifies
	 * huge constructors.
	 */
	public static class Builder {

		private String projectName;
		private ProjectType projectType;
		private Event timePeriod;
		private List<Activity> activities = new ArrayList<>();
		private User projectManager;

		public Builder() {

		}

		public Builder(Project project) {
			this.projectName = project.getProjectName();
			this.projectType = project.getProjectType();
			this.timePeriod = project.getTimePeriod();
			this.activities = project.getActivities();
			this.projectManager = project.getProjectManager();
		}


		public Builder setProjectName(String projectName) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
			if(projectName.matches("[a-zA-Z1-9]+[ a-zA-Z1-9]*"))
				//check for duplicates too
				this.projectName = projectName;
			else
				throw new PatternSyntaxException(projectName + "Project name can neither be empty, nor only be a space. It must match regex: '[a-zA-Z1-9]+[ a-zA-Z1-9]*'", projectName, 0);
/*			
			if (!projectName.matches("^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$")) {
				if(projectName.length() == 0) throw new ArrayIndexOutOfBoundsException("Project names can't be empty");
				
				char[] pn = projectName.toCharArray();
				
				
				if (pn[0] == ' ') {
					throw new PatternSyntaxException(projectName
							+ "illegal project name for regex, no spaces at beginning: ^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$\nillegal character is \'"
							+ pn[0] + "\'", projectName, 0);
				} else {
					for (int i = 0; i < pn.length; i++) {
						if (!(Character.isAlphabetic(pn[i]) || Character.isDigit(pn[i]) || pn[i] == ' ')) {
							throw new PatternSyntaxException(projectName
									+ "illegal project name for regex: ^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$\nillegal character is \'" + pn[i]
											+ "\'", projectName, i);
						}
					}
				}
			} else {
				this.projectName = projectName;
			}
*/
			return this;
		}

		public Builder setProjectType(ProjectType projectType) {
			this.projectType = projectType;
			return this;
		}

		public Builder setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
			Event event = new Event(startDate, endDate);
			if(event.getEndDate().isAfter(event.getStartDate()))
				this.timePeriod = new Event(startDate, endDate);
			else throw new DateTimeParseException("End date: " + endDate + " must be after start date: " + startDate, endDate, 0);
			return this;
		}

		public Builder setActivities(List<Activity> activities) {
			this.activities = activities;
			return this;
		}

		public Builder setProjectManager(User projectManager) {
			this.projectManager = projectManager;
			return this;
		}

		public Project build() {
			return new Project(projectName, projectType, timePeriod, activities, projectManager);
		}

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public ProjectType getProjectType() {
		return this.projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public Event getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Event timePeriod) {
		this.timePeriod = timePeriod;
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	public void removeActivity(Activity activity) {
		this.activities.remove(activity);
	}

	@Override
	public String toString() {
		return "projectName='" + projectName + '\''
				+ ", projectType=" + projectType
				+ ", timePeriod=" + (timePeriod == null ? "undefined" : timePeriod)
				+ ", activities=" + activities;
	}

	/**
	 * @return the projectManager
	 */
	public User getProjectManager() {
		return projectManager;
	}

	/**
	 * @param projectManager the projectManager to set
	 */
	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}
}
