package dtu.project.entities;

import dtu.project.enums.ProjectType;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * 
 * @author Nicholas
 *
 */
public class Project {

    private String projectName;
    private ProjectType projectType;
    private User projectManager;
    private TimePeriod timePeriod;
    private List<Activity> activities = new ArrayList<>();;
    

    private Project(String projectName, ProjectType projectType, User projectManager, TimePeriod timePeriod) {
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectManager = projectManager;
        this.timePeriod = timePeriod;      
    }

    /**
     * https://medium.com/@ajinkyabadve/builder-design-patterns-in-java-1ffb12648850
     * Using builder pattern allows for easier creation objects and simplifies
     * huge constructors.
     */
    public static class Builder {

        private String projectName;
        private ProjectType projectType;
        private TimePeriod timePeriod;
        private User projectManager;

        public Builder() {

        }

        public Builder setProjectName(String projectName) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
            if (projectName.matches("[a-zA-Z1-9]+[ a-zA-Z1-9]*")) {
                this.projectName = projectName;
            } else {
            	// mangler
                throw new PatternSyntaxException(projectName + "Project name can neither be empty, nor only be a space. It must match regex: '[a-zA-Z1-9]+[ a-zA-Z1-9]*'", projectName, 0);
            }
            return this;
        }

        public Builder setProjectType(ProjectType projectType) {
            this.projectType = projectType;
            return this;
        }

        public Builder setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
        	this.timePeriod = new TimePeriod(startDate, endDate);
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

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
		this.timePeriod = new TimePeriod(startDate, endDate);
	}
	public void setTimePeriod(TimePeriod event) throws DateTimeParseException {
		this.timePeriod = event;
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

	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", projectType=" + projectType + ", projectManager="
				+ projectManager + ", timePeriod=" + timePeriod + ", activities=" + activities + "]";
	}
    
    
}
