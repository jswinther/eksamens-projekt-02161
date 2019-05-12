package dtu.project.controllers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.ProjectRepository;

/**
 * Controller for ProjectRepository.
 * @author Jonathan
 *
 */
public class ProjectController {

    private final ProjectRepository projectRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ProjectController(ProjectRepository projectRepository) {
        super();
        this.projectRepository = projectRepository;
    }

    /**
     * Adds the project to project repository,
     * if the name is taken it throws an exception
     * @param project
     * @throws dtu.project.exceptions.DuplicateProjectName
     */
    public void addProject(Project project) throws DuplicateProjectName {
    	if(isProjectListEmpty()) {
    		getProjectList().add(project);
    	}
    		
    	else {
    		for (Project p : getProjectList()) {
    			if(p.getProjectName() != null) {
    				if (p.getProjectName().equals(project.getProjectName())) {
    	                	throw new DuplicateProjectName();
    	    		}
    			}
    			
    		}
        	getProjectList().add(project);
    	}
    		

    }
    
    /**
     * Sets the values of project1 equal to project2.
     * @param project1
     * @param project2
     * @throws DuplicateProjectName
     */
    public void editProject(Project project1, Project project2) throws DuplicateProjectName {
    	for (Project p : getProjectList()) {
			if(p.getProjectName().equals(project2.getProjectName()))
				throw new DuplicateProjectName();
		}
    	project1.setProjectManager(project2.getProjectManager());
    	project1.setProjectName(project2.getProjectName());
    	project1.setProjectType(project2.getProjectType());
    	project1.setTimePeriod(project2.getTimePeriod());
    }
    

    /**
     * Removes project from list of projects.
     * @param project
     */
    public void removeProject(Project project) {
        getProjectList().remove(project);
    }

    /**
     * Adds an activity to the project.
     * @param project
     * @param activity
     * @throws dtu.project.exceptions.DuplicateActivityName
     */
    public void addActivity(Project project, Activity activity) throws DuplicateActivityName {
        for (Activity a : project.getActivities()) {
            if (a.getActivityName().equals(activity.getActivityName())) {
                throw new DuplicateActivityName();
            }
        }
        project.addActivity(activity);
    }

    /**
     * Sets the values of currentActivity equal to newActivity.
     * Registered hours are not overwritten.
     * @param project
     * @param currentActivity
     * @param newActivity
     * @throws DuplicateActivityName
     */
    public void editActivity(Project project, Activity currentActivity, Activity newActivity) throws DuplicateActivityName {
        for (Activity a : project.getActivities()) {
            if (a.getActivityName().equals(newActivity.getActivityName())) {
                throw new DuplicateActivityName();
            }
        }
        currentActivity.setUser(newActivity.getUsers());		
        currentActivity.setActivityName(newActivity.getActivityName());
        currentActivity.setEstimatedHours(newActivity.getEstimatedHours());
        currentActivity.setTimePeriod(newActivity.getTimePeriod());
        
    }

    /**
     * Removes activity in project.
     * @param project
     * @param activity
     */
    public void removeActivity(Project project, Activity activity) {
        project.removeActivity(activity);
    }

    /**
     * Adds user to an activity, if they aren't added already.
     * @param activity
     * @param user
     * @throws DuplicateUser
     */
    public void addUserToActivity(Activity activity, User user) throws DuplicateUser {
        for (User user1 : activity.getUsers()) {
            if (user1.toString().equals(user.toString())) {
                throw new DuplicateUser();
            }
        } activity.getUsers().add(user);

    }

    public boolean isProjectListEmpty() {
        return getProjectList().isEmpty();
    }

    public boolean isActivityListEmpty(Project project) {
        return project.getActivities().isEmpty();
    }

    public List<Project> getProjectList() {
        return projectRepository.getProjectList();
    }
}
