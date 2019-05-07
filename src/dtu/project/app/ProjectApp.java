package dtu.project.app;

import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
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

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void registerHours(User user, String startDate, String endDate, Activity activity, String message) {
        getUserMap().get(user).add(new Event(startDate, endDate, activity, message));
    }

    public List<User> usersWhoAreFreeAt(String startDate, String endDate) {
        TimePeriod event = new TimePeriod(startDate, endDate);
        List<User> users = getUserList();

        for (Map.Entry<User, List<Event>> entry : getUserMap().entrySet()) {
            User key = entry.getKey();
            List<Event> value = entry.getValue();
            for (Event period : value) {
                if (overlaps(event, period)) {
                    users.remove(key);
                }
            }
        }
        return users;
    }

    private boolean overlaps(TimePeriod event, Event period) {
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

    public List<User> getUserList() {
        return new ArrayList<>(userRepository.getUserMap().keySet());
    }

    public Map<User, List<Event>> getUserMap() {
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

    public void editActivity(Project project, Activity currentActivity, Activity newActivity) throws DuplicateActivityName {
        for (Activity a : project.getActivities()) {
            if(a.getActivityName().equals(newActivity.getActivityName()))
                throw new DuplicateActivityName();
        }
        currentActivity = newActivity;
    }
}
