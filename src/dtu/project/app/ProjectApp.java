package dtu.project.app;

import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;

/**
 * This class functions as a connector for the GUI to use all the other classes.
 *
 * @author Jonathan
 */
public class ProjectApp {

    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void registerHours(User user, String startDate, String endDate, Activity activity, String message) {
        getUserMap().get(user).add(new Period(startDate, endDate, activity, message));
    }

    public List<User> usersWhoAreFreeAt(String startDate, String endDate) {
        Event event = new Event(startDate, endDate);
        List<User> users = getUserList();

        for (Map.Entry<User, List<Period>> entry : getUserMap().entrySet()) {
            User key = entry.getKey();
            List<Period> value = entry.getValue();
            for (Period period : value) {
                if (overlaps(event, period)) {
                    users.remove(key);
                }
            }
        }
        return users;
    }

    private boolean overlaps(Event event, Period period) {
        return !(event.getEndDate().isBefore(period.getTimePeriod().getStartDate()) || event.getStartDate().isAfter(period.getTimePeriod().getEndDate()));
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

    public DefaultListModel<String> getUserScheduleDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(search(searchText, new ArrayList<>(getUserMap().get(searchUser(userName).get(0)))));
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
    /*
    public List<User> findUser(FindType findType, Event event) {
        List<User> users = null;
        switch (findType) {
            case FREE:
                users = getUserList();
                for (User user : users) {
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
                users = getUserList();
                for (User user : users) {
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
    }*/
    public void generateReport(Project project) {
        int counter = 0;
        System.out.println(""
                + "\nProject Name:\t\t" + project.getProjectName()
                + "\nProject Type:\t\t" + project.getProjectType()
                + "\nManager:\t\t" + (project.getProjectManager() == null ? "not decided" : project.getProjectManager())
                + "\nTime Period:\t\t" + project.getTimePeriod().getStartDate() + " to " + project.getTimePeriod().getEndDate()
                + "\n\nActivity Status\n___________________________\n");
        for (Activity activity : project.getActivities()) {
            System.out.println("Activity Number:\t" + counter
                    + "\nActivity Name:\t\t" + activity.getActivityName()
                    + "\nResponsible:\t\t" + activity.getUsers().get(0)
                    + "\nTime Period:\t\t" + activity.getTimePeriod().getStartDate() + " to " + activity.getTimePeriod().getEndDate()
                    + "\nEstimated Hours:\t" + activity.getEstimatedHours());
            counter++;
            for (Map.Entry<User, List<Event>> entry : activity.getRegisteredHours().entrySet()) {
                User key = entry.getKey();
                System.out.println(key + " registered these activites");
                List<Event> value = entry.getValue();
                for (Event event : value) {
                    System.out.println(event);
                }
            }
            System.out.println("\n");
        }
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
        return new ArrayList<>(userRepository.getUserMap().keySet());
    }

    public Map<User, List<Period>> getUserMap() {
        return userRepository.getUserMap();
    }

    public List<Project> getProjectList() {
        return projectRepository.getProjectList();
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
}
