package dtu.project.controllers;

import java.util.List;

import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.ProjectRepository;

public class ProjectController {
	
	private final ProjectRepository projectRepository;

	public ProjectController(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	
	/**
     * add project
     *
     * @param project
     * @throws dtu.project.exceptions.DuplicateProjectName
     */
    public void addProject(Project project) throws DuplicateProjectName {
        for (Project p : getProjectList()) {
            if (p.getProjectName().equals(project.getProjectName())) {
                throw new DuplicateProjectName();
            }
        }
        getProjectList().add(project);

    }
    
    /**
     * delete project
     *
     * @param project
     */
    public void removeProject(Project project) {
        getProjectList().remove(project);
    }
	
	
    /**
    *
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
   
   public void editActivity(Project project, Activity currentActivity, Activity newActivity) throws DuplicateActivityName {
       for (Activity a : project.getActivities()) {
           if(a.getActivityName().equals(newActivity.getActivityName()))
               throw new DuplicateActivityName();
       }
       currentActivity = newActivity;
   }

   /**
    *
    * @param project
    * @param activity
    */
   public void removeActivity(Project project, Activity activity) {
       project.removeActivity(activity);
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
