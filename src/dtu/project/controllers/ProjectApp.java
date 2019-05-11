package dtu.project.controllers;

import dtu.project.entities.Activity;
import dtu.project.entities.Event;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
 
/**
 * This class functions as a connector for the GUI to use all the other classes.
 *
 * @author Jonathan
 */
public class ProjectApp {

	private final ProjectController PC;
	private final UserController UC;

    public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
        this.UC = new UserController(userRepository);
        this.PC = new ProjectController(projectRepository);
    }
    
    /**
     * ***********************************************************
     * Generic methods used to make controlling information easier
     * ***********************************************************
     */
    
    /**
     * Returns the first element of type E which matches string, which appears in the list of elements.
     * @param <E>
     * @param string
     * @param list
     * @return if it exists then E otherwise null
     */
    public <E> E get(String string, List<E> list) {
    	for (E e : list) {
			if(string.contains(e.toString())) {
				return e;
			}
		}
    	return null;
    }
    
    /**
     * Returns the element at the specified index in the list, if it exists.
     * @param <E>
     * @param index
     * @param list
     * @return if it exists then E otherwise null
     */
    public <E> E get(int index, List<E> list) {
    	return list.get(index) == null ? null : list.get(index);
    }
    
    /**
     * Sets the first element of type E which matches string, which appears in the list of elements.
     * @param <E>
     * @param string
     * @param element
     * @param list
     */
    public <E> void set(String string, E element, List<E> list) {
    	for (E e : list) {
			if(string.contains(e.toString())) {
				e = element;
				break;
			}
		}
    }
    
    /**
     * Sets the element at the specified index to new element
     * @param <E>
     * @param index
     * @param element
     * @param list
     */
    public <E> void set(int index, E element, List<E> list) {
    	list.set(index, element);
    }
    
    
    
    /**
     * Jonathan Generic method which converts a list of elements of type E into
     * a stream, which collects all elements that contain the searchText in
     * their toString method. The toString methods, must be generated in the
     * given class to assure that they print out all their info.
     *
     * @param searchText
     * @param searchList
     * @param <E>
     * @return
     */
    public <E> List<E> search(String searchText, List<E> searchList) {
        if (searchText == null || searchList == null) {
            throw new NullPointerException();
        }
        return searchList.stream().filter(e -> e.toString().contains(searchText)).collect(Collectors.toList());
    }
    
    
    
    
    
    public Project getProject(String projectName) {
    	return get(projectName, getProjectList());
    }
    
    public Project getProject(int index) {
    	return get(index, getProjectList());
    }
    
    public void setProject(int index, Project project) {
    	set(index, project, getProjectList());
    }
    
    public void setProject(String projectName, Project project) {
    	set(projectName, project, getProjectList());
    }
    
    public List<Project> searchProjects(String searchText) {
        return search(searchText, getProjectList());
    }
    
    public Activity getActivity(Project project, String activityName) {
    	return get(activityName, project.getActivities());
    }
    
    public Activity getActivity(Project project, int index) {
    	return get(index, project.getActivities());
    }
    
    public void setActivity(Project project, String activityName, Activity activity) {
    	set(activityName, activity, project.getActivities());
    }
    
    public void setActivity(Project project, int index, Activity activity) {
    	set(index, activity, project.getActivities());
    }
    
    public User getUser(String userName) {
    	return get(userName, getUserList());
    }
    
    public User getUser(int index) {
    	return get(index, getUserList());
    }
    
    public List<Event> getUserSchedule(String userName) {
    	return getUserMap().get(get(userName, getUserList()));
    }
    
    public List<Event>  getUserSchedule(int index) {
    	return getUserMap().get(get(index, getUserList()));
    }
    
    public List<User> searchUser(String searchText) {
        return search(searchText, getUserList());
    }
    
    /**
     * Jonathan Get activities assigned to a specific user.
     *
     * @param user
     * @return
     */
    public List<Activity> getActivitiesAssignedTo(User user) {
        List<Activity> activities = new ArrayList<>();
        getProjectList().forEach((Project project) -> {
            project.getActivities().stream().filter((a) -> (a.getUsers().contains(user))).forEachOrdered((a) -> {
                activities.add(a);
            });
        });
        return activities;
    }

    public List<User> getUserList() {
    	return UC.getUserList();
    }

    public Map<User, List<Event>> getUserMap() {
        return UC.getUserMap();
    }

    public List<Project> getProjectList() {
        return PC.getProjectList();
    }
    
    
    
    
    /**
	 * @param project
	 * @throws DuplicateProjectName
	 * @see dtu.project.controllers.ProjectController#addProject(dtu.project.entities.Project)
	 */
	public void addProject(Project project) throws DuplicateProjectName {
		PC.addProject(project);
	}

	/**
	 * @param project
	 * @see dtu.project.controllers.ProjectController#removeProject(dtu.project.entities.Project)
	 */
	public void removeProject(Project project) {
		PC.removeProject(project);
	}

	/**
	 * @param project
	 * @param activity
	 * @throws DuplicateActivityName
	 * @see dtu.project.controllers.ProjectController#addActivity(dtu.project.entities.Project, dtu.project.entities.Activity)
	 */
	public void addActivity(Project project, Activity activity) throws DuplicateActivityName {
		PC.addActivity(project, activity);
	}

	/**
	 * @param project
	 * @param currentActivity
	 * @param newActivity
	 * @throws DuplicateActivityName
	 * @see dtu.project.controllers.ProjectController#editActivity(dtu.project.entities.Project, dtu.project.entities.Activity, dtu.project.entities.Activity)
	 */
	public void editActivity(Project project, Activity currentActivity, Activity newActivity)
			throws DuplicateActivityName {
		PC.editActivity(project, currentActivity, newActivity);
	}

	/**
	 * @param project
	 * @param activity
	 * @see dtu.project.controllers.ProjectController#removeActivity(dtu.project.entities.Project, dtu.project.entities.Activity)
	 */
	public void removeActivity(Project project, Activity activity) {
		PC.removeActivity(project, activity);
	}
	
	

	/**
	 * @param activity
	 * @param user
	 * @see dtu.project.controllers.ProjectController#addUserToActivity(dtu.project.entities.Activity, dtu.project.entities.User)
	 */
	public void addUserToActivity(Activity activity, User user) throws DuplicateUser {
		PC.addUserToActivity(activity, user);
	}

	/**
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @param activity
	 * @param message
	 * @see dtu.project.controllers.UserController#addHours(dtu.project.entities.User, java.lang.String, java.lang.String, dtu.project.entities.Activity, java.lang.String)
	 */
	public void addHours(User user, String startDate, String endDate, Activity activity, String message) {
		UC.addHours(user, startDate, endDate, activity, message);
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see dtu.project.controllers.UserController#getFreeUsers(java.lang.String, java.lang.String)
	 */
	public List<User> getFreeUsers(String startDate, String endDate) {
		return UC.getFreeUsers(startDate, endDate);
	}

	/**
	 * @return
	 * @see dtu.project.controllers.ProjectController#isProjectListEmpty()
	 */
	public boolean isProjectListEmpty() {
		return PC.isProjectListEmpty();
	}

	/**
	 * @param project
	 * @return
	 * @see dtu.project.controllers.ProjectController#isActivityListEmpty(dtu.project.entities.Project)
	 */
	public boolean isActivityListEmpty(Project project) {
		return PC.isActivityListEmpty(project);
	}
    
    
    

    
    

    

    



    

    



    
}
