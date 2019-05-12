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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * This Class is a controller, which uses methods from ProjectController and UserController
 * In addition to this it has some generic methods which are then called by other methods.
 * Designed with the intention of repeating the same code as little as possible.
 * @author Jonathan
 *
 */
public class ProjectApp {

	/**
	 * Controllers used to manipulate the data in the repository.
	 */
    private final ProjectController PC;
    private final UserController UC;

    /**
     * InMemoryRepository implements ProjectRepository and UserRepository.
     * @param userRepository a repository implementing UserRepository interface
     * @param projectRepository a repository implementing ProjectRepository interface
     */
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
     * Returns the first element of type E which matches string, which appears
     * in the list of elements.
     * @param <E> the object type stored in the list and object type to be returned.
     * @param string the text one wishes to search for.
     * @param list the list containing the elements.
     * @return if it exists then E otherwise null
     */
    public <E> E get(String string, List<E> list) {
        for (E e : list) {
            if (string.contains(e.toString())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the element at the specified index in the list.
     * @param <E> object type
     * @param index index of value to get
     * @param list the list to get from
     * @return returns E
     */
    public <E> E get(int index, List<E> list) {
        return list.get(index);
    }

    /**
     * Sets the first element of type E which matches string, which appears in
     * the list of elements.
     * @param <E> object type
     * @param string text to match
     * @param element the new element to be in place
     * @param list the list where element containing string exists.
     */
    public <E> void set(String string, E element, List<E> list) {
        for (E e : list) {
            if (e.toString().contains(string)) {
                list.set(list.indexOf(e), element);
                break;
            }
        }
    }

    /**
     * Sets the element at the specified index to new element
     *
     * @param <E> object type
     * @param index index of the value to set
     * @param element the new element to take its place
     * @param list the list where the element to be set exists.
     */
    public <E> void set(int index, E element, List<E> list) {
        list.set(index, element);
    }

    /**
     * Generic method which converts a list of elements of type E into
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
        return searchList.stream().filter(e -> e.toString().contains(searchText)).collect(Collectors.toList());
    }

    /**
     * Returns project with name containing projectName.
     * @param projectName
     * @return
     */
    public Project getProject(String projectName) {
        return get(projectName, getProjectList());
    }

    /**
     * Returns project at the specified index in projectList.
     * @param index
     * @return
     */
    public Project getProject(int index) {
        return get(index, getProjectList());
    }

    /**
     * Sets the project at the specified index to project.
     * @param index
     * @param project
     */
    public void setProject(int index, Project project) {
        set(index, project, getProjectList());
    }

    /**
     * Sets the project with name projectName to project.
     * @param projectName
     * @param project
     */
    public void setProject(String projectName, Project project) {
        set(projectName, project, getProjectList());
    }

    /**
     * Returns a list of projects containing searchText
     * @param searchText
     * @return
     */
    public List<Project> searchProjects(String searchText) {
        return search(searchText, getProjectList());
    }
    
    /**
     * Sets the activity with name activityName to activity
     * @param project
     * @param activityName
     * @param activity
     */
    public void setActivity(Project project, String activityName, Activity activity) {
    	set(activityName, activity, project.getActivities());
    }
    
    /**
     * Sets the activity at the specified index to activity
     * @param project
     * @param index
     * @param activity
     */
    public void setActivity(Project project, int index, Activity activity) {
    	set(index, activity, project.getActivities());
    }
    
    /**
     * Returns activity with name activityName
     * @param project
     * @param activityName
     * @return
     */
    public Activity getActivity(Project project, String activityName) {
        return get(activityName, project.getActivities());
    }

    /**
     * Returns activity at the specified index
     * @param project
     * @param index
     * @return
     */
    public Activity getActivity(Project project, int index) {
        return get(index, project.getActivities());
    }
    
    /**
     * Returns user with userName
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        return get(userName, getUserList());
    }

    /**
     * Returns user at the specified index.
     * @param index
     * @return
     */
    public User getUser(int index) {
        return get(index, getUserList());
    }

    /**
     * Returns the schedule for user with name userName
     * @param userName
     * @return
     */
    public List<Event> getUserSchedule(String userName) {
        return getUserMap().get(get(userName, getUserList()));
    }

    /**
     * Returns the schedule for user at index
     * @param index
     * @return
     */
    public List<Event> getUserSchedule(int index) {
        return getUserMap().get(get(index, getUserList()));
    }

    /**
     * Returns a list of users containing the searchText
     * @param searchText
     * @return
     */
    public List<User> searchUser(String searchText) {
        return search(searchText, getUserList());
    }

    /**
     * Returns the list of activities assigned a specified user.
     * Loops through all project, then in all project, loop through
     * activities and then loop through users and add the activity
     * if the user is in the list of users inside the Activity.
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

    /** 
     * Returns a map with keys user and value the amount of activities
     * that they are assigned in the given timeperiod startDate to endDate.
     * Before it is returned the map is sorted by value, so the users with the least
     * amount of activities are put first in the list.
     * 
     * The function was previously based on the link below, but turned out it didnt work, but
     * the link is kept as documentation so there are no suspicion about copying without linking
     * the code.
     * no longer based on this link -> https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html
     * Sort Map by Values.
     * @param startDate
     * @param endDate
     * @return
     */
    public HashMap<User, Integer> getUserListWithAcitivites(String startDate, String endDate) {
        TimePeriod event = new TimePeriod(startDate, endDate);
        HashMap<User, Integer> userList = new HashMap<>();
        int numOfActivites = 0;
        for (User user : getUserList()) {
            for (Activity activity : getActivitiesAssignedTo(user)) {
                if (UC.overlaps(event, activity.getTimePeriod())) {
                    numOfActivites++;
                }
            }
            userList.put(user, numOfActivites);
        }
        
     
        
        return sortByValue(userList);
    }
    
    /**
     * This function is taken 100% from 
     * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     * 
     * It used in the function getUserListWithActivities.
     * 
     * @param hm
     * @return
     */
    public HashMap<User, Integer> sortByValue(HashMap<User, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<User, Integer> > list = 
               new LinkedList<Map.Entry<User, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<User, Integer> >() { 
            public int compare(Map.Entry<User, Integer> o1,  
                               Map.Entry<User, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<User, Integer> temp = new LinkedHashMap<User, Integer>(); 
        for (Map.Entry<User, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
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
     * @see
     * dtu.project.controllers.ProjectController#addProject(dtu.project.entities.Project)
     */
    public void addProject(Project project) throws DuplicateProjectName {
        PC.addProject(project);
    }

    /**
     * @param project
     * @see
     * dtu.project.controllers.ProjectController#removeProject(dtu.project.entities.Project)
     */
    public void removeProject(Project project) {
        PC.removeProject(project);
    }

    /**
     * @param project
     * @param activity
     * @throws DuplicateActivityName
     * @see
     * dtu.project.controllers.ProjectController#addActivity(dtu.project.entities.Project,
     * dtu.project.entities.Activity)
     */
    public void addActivity(Project project, Activity activity) throws DuplicateActivityName {
        PC.addActivity(project, activity);
    }

    /**
     * @param project
     * @param currentActivity
     * @param newActivity
     * @throws DuplicateActivityName
     * @see
     * dtu.project.controllers.ProjectController#editActivity(dtu.project.entities.Project,
     * dtu.project.entities.Activity, dtu.project.entities.Activity)
     */
    public void editActivity(Project project, Activity currentActivity, Activity newActivity) throws DuplicateActivityName {
        PC.editActivity(project, currentActivity, newActivity);
    }

    /**
     * @param project
     * @param activity
     * @see
     * dtu.project.controllers.ProjectController#removeActivity(dtu.project.entities.Project,
     * dtu.project.entities.Activity)
     */
    public void removeActivity(Project project, Activity activity) {
        PC.removeActivity(project, activity);
    }

    /**
     * @param activity
     * @param user
     * @throws dtu.project.exceptions.DuplicateUser
     * @see
     * dtu.project.controllers.ProjectController#addUserToActivity(dtu.project.entities.Activity,
     * dtu.project.entities.User)
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
     * @see
     * dtu.project.controllers.UserController#addHours(dtu.project.entities.User,
     * java.lang.String, java.lang.String, dtu.project.entities.Activity,
     * java.lang.String)
     */
    public void addHours(User user, String startDate, String endDate, Activity activity, String message) {
        UC.addHours(user, startDate, endDate, activity, message);
    }

    /**
     * @return @see
     * dtu.project.controllers.ProjectController#isProjectListEmpty()
     */
    public boolean isProjectListEmpty() {
        return PC.isProjectListEmpty();
    }

    /**
     * @param project
     * @return
     * @see
     * dtu.project.controllers.ProjectController#isActivityListEmpty(dtu.project.entities.Project)
     */
    public boolean isActivityListEmpty(Project project) {
        return PC.isActivityListEmpty(project);
    }
    

}
