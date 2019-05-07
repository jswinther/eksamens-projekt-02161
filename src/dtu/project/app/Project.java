package dtu.project.app;

import dtu.project.enums.ProjectType;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Project {

    private String projectName;
    private ProjectType projectType;
    private User projectManager;
    private Event timePeriod;
    private List<Activity> activities = new ArrayList<>();;
    

    private Project(String projectName, ProjectType projectType, User projectManager, Event timePeriod) {
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectManager = projectManager;
        this.timePeriod = timePeriod;      
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
        private User projectManager;

        public Builder() {

        }

        public Builder setProjectName(String projectName) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
            if (projectName.matches("[a-zA-Z1-9]+[ a-zA-Z1-9]*")) {
                this.projectName = projectName;
            } else {
                throw new PatternSyntaxException(projectName + "Project name can neither be empty, nor only be a space. It must match regex: '[a-zA-Z1-9]+[ a-zA-Z1-9]*'", projectName, 0);
            }
            return this;
        }

        public Builder setProjectType(ProjectType projectType) {
            this.projectType = projectType;
            return this;
        }

        public Builder setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
            Event event = new Event(startDate, endDate);
            if (event.getEndDate().isAfter(event.getStartDate())) {
                this.timePeriod = event; //new Event(startDate, endDate);
            } else {
                throw new DateTimeParseException("End date: " + endDate + " must be after start date: " + startDate, endDate, 0);
            }
            return this;
        }

        public Builder setProjectManager(User projectManager) {
            this.projectManager = projectManager;
            return this;
        }

        public Project build() {
            return new Project(projectName, projectType, projectManager, timePeriod);
        }

    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) throws PatternSyntaxException {
		if (projectName.matches("[a-zA-Z1-9]+[ a-zA-Z1-9]*")) {
            this.projectName = projectName;
        } else {
            throw new PatternSyntaxException(projectName + "Project name can neither be empty, nor only be a space. It must match regex: '[a-zA-Z1-9]+[ a-zA-Z1-9]*'", projectName, 0);
        }
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}

	public Event getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String startDate, String endDate) {
		Event event = new Event(startDate, endDate);
        if (event.getEndDate().isAfter(event.getStartDate())) {
            this.timePeriod = event; //new Event(startDate, endDate);
        } else {
            throw new DateTimeParseException("End date: " + endDate + " must be after start date: " + startDate, endDate, 0);
        }
	}
	
	public void setTimePeriod(Event event) {
        if (event.getEndDate().isAfter(event.getStartDate())) {
            this.timePeriod = event; //new Event(startDate, endDate);
        } else {
            throw new DateTimeParseException("End date: " + event.getEndDate() + " must be after start date: " + event.getStartDate(), event.getEndDate().toString(), 0);
        }
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void addActivity(Activity activity){
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.activities.remove(activity);
    }
}
