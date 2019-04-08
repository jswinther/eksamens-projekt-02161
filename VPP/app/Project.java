package app;

public class Project {

	private List<Activity> activityList;
	private String projectName;
	private Type projectType;

	public List<Activity> getActivityList() {
		return this.activityList;
	}

	/**
	 * 
	 * @param activityList
	 */
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Type getProjectType() {
		return this.projectType;
	}

	/**
	 * 
	 * @param projectType
	 */
	public void setProjectType(Type projectType) {
		this.projectType = projectType;
	}

}