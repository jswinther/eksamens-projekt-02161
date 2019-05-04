package dtu.project.app;

import dtu.project.enums.FindType;
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;
import java.io.Console;
import static java.lang.System.console;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;

public class ProjectApp {

    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
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
    private <E> List<E> search(String searchText, List<E> searchList) {
        if (searchText == null || searchList == null) {
            throw new NullPointerException();
        }
        return searchList.stream().filter(e -> e.toString().contains(searchText)).collect(Collectors.toList());

    }

    /**
     * Jonathan Converts a list of elements in to a default list model which is
     * used for the GUI. otherwise this code will be repeated and we want to
     * follow the DRY principle.
     *
     * @param <E>
     * @param list
     * @return
     */
    private <E> DefaultListModel<String> listToDefaultListModel(List<E> list) {
        return new DefaultListModel<String>() {
            {
                list.forEach((e) -> {
                    addElement(e.toString());
                });
            }
        };
    }

    public DefaultListModel<String> getUserDefaultListModel() {
        return listToDefaultListModel(getUserList());
    }

    public DefaultListModel<String> getProjectDefaultListModel() {
        return listToDefaultListModel(getProjectList());
    }

    public DefaultListModel<String> getUserDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(searchUser(searchText));
    }

    public DefaultListModel<String> getProjectDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(searchProjects(searchText));
    }

    public DefaultListModel<String> getUserActivitiesDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(search(searchText, getActivitiesAssignedTo(searchUser(userName).get(0))));
    }

    /**
     * Jonathan Returns all projects that contain the search text.
     *
     * @param searchText
     * @return
     */
    public List<Project> searchProjects(String searchText) {
        return search(searchText, getProjectList());
    }

    /**
     * Jonathan Returns all users that contains the search text.
     *
     * @param searchText
     * @return
     */
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

    /**
     * Jonathan Returns the first user found who contains the search string
     *
     * @param name
     * @return
     */
    public User findUser(String name) {
        Optional<?> r = getUserList().stream().filter(user -> user.toString().contains(name)).findFirst();
        return r.isPresent()
                ? getUserList().stream().filter(user -> user.toString().contains(name)).findFirst().get() : null;
    }

    /**
     * Jonathan
     *
     * @param findType
     * @param event
     * @return
     */
    public List<User> findUser(FindType findType, Event event) {
        List<User> users = null;
        switch (findType) {
            case FREE:
                users = new ArrayList<User>() {
                    {
                        addAll(getUserList());
                    }
                };
                for (User user : getUserList()) {
                    for (Activity key : user.getSchedule().keySet()) {
                        for (Event e : user.getSchedule().get(key)) {
                            if (!(event.getEndDate().isBefore(e.getStartDate()) || event.getStartDate().isAfter(e.getEndDate()))) {
                                users.remove(user);
                            }
                        }
                    }
                }
                break;
            case UNAVAILABLE:
                users = new ArrayList<>();
                for (User user : getUserList()) {
                    for (Activity key : user.getSchedule().keySet()) {
                        for (Event e : user.getSchedule().get(key)) {
                            if (!(event.getEndDate().isBefore(e.getStartDate()) || event.getStartDate().isAfter(e.getEndDate()))) {
                                users.add(user);
                            }
                        }
                    }
                }
                break;
        }
        return users;
    }

    /**
     *
     * @param project
     * @param activity
     */
    public void addActivity(Project project, Activity activity) {
        try {
            project.addActivity(activity);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param project
     * @param activity
     */
    public void removeActivity(Project project, Activity activity) {
        project.removeActivity(activity);
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProjectRepository getProjectRepository() {
        return this.projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<User> getUserList() {
        return userRepository.getUserList();
    }

    public void setUserList(List<User> userList) {
        userRepository.setUserList(userList);
    }

    public List<Project> getProjectList() {
        return projectRepository.getProjectList();
    }

    public void setProjectList(List<Project> projectList) {
        projectRepository.setProjectList(projectList);
    }

    /**
     * add project
     *
     * @param project
     */
    public void addProject(Project project) throws ArrayIndexOutOfBoundsException, PatternSyntaxException{
        try {
            getProjectList().add(project);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * delete project
     *
     * @param project
     */
    public void removeProject(Project project) {
        getProjectList().remove(project);
    }
    
    private void scheduleWork(User user, Activity activity, String startDate, String endDate) {
    	Event event = new Event(startDate, endDate);
		if(!user.getSchedule().containsKey(activity)) {
			user.getSchedule().put(activity, new ArrayList<Event>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				add(event);
			}});
			activity.setRegisteredHours(activity.getRegisteredHours() + (event.getEndDate().getHour() - event.getStartDate().getHour()));
		} else {
			user.getSchedule().get(activity).add(event);
			activity.setRegisteredHours(activity.getRegisteredHours() + (event.getEndDate().getHour() - event.getStartDate().getHour()));
		}
	}
    
    private void scheduleHoliday(User user, Activity activity, String startDate, String endDate) {
    	Event event = new Event(startDate, endDate);
		if(!user.getSchedule().containsKey(activity)) {
			user.getSchedule().put(activity, new ArrayList<Event>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				add(event);
			}});
		} else {
			user.getSchedule().get(activity).add(event);
		}
	}
    
    private void scheduleSick(User user, Activity activity, String startDate, String endDate) {
    	Event event = new Event(startDate, endDate);
		if(!user.getSchedule().containsKey(activity)) {
			user.getSchedule().put(activity, new ArrayList<Event>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				add(event);
			}});
		} else {
			user.getSchedule().get(activity).add(event);
		}
	}
    
    public void scheduleHours(User user, Activity activity, String startDate, String endDate) {
    	switch(activity.getActivityType()) {
		case HOLIDAY:
			scheduleHoliday(user, activity, startDate, endDate);
			break;
		case UNPAID:
			scheduleSick(user, activity, startDate, endDate);
			break;
		case WORK:
			scheduleWork(user, activity, startDate, endDate);
			break;
		default:
			break;
    	
    	}
    }
    
    public User getProjectManager(Project project) {
    	for (Activity activity : project.getActivities()) {
			if(activity.toString().toLowerCase().contains("project manager") || activity.toString().toLowerCase().contains("projectmanager"))
				return activity.getUsers().get(0);
		}
		return null;
    }

}
