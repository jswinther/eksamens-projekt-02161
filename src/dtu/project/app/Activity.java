package dtu.project.app;

import dtu.project.enums.ActivityType;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private String activityName;
    private ActivityType activityType;
    private List<User> users;
    private int estimatedHours;
    private int registeredHours;
    private Event timePeriod;

    /**
     * Work related
     *
     * @param activityName
     * @param activityType
     * @param estimatedHours
     * @param timePeriod
     * @param user
     */
    public Activity(String activityName, ActivityType activityType, int estimatedHours, Event timePeriod, User user) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.users = new ArrayList<User>();
        this.users.add(user);
        this.estimatedHours = estimatedHours;
        this.registeredHours = 0;
        this.timePeriod = timePeriod;
    }

    /**
     * Not work related
     *
     * @param activityName
     * @param activityType
     * @param timePeriod
     * @param user
     */
    public Activity(String activityName, ActivityType activityType, Event timePeriod, User user) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.users = new ArrayList<User>();
        this.users.add(user);
        this.estimatedHours = 0;
        this.registeredHours = 0;
        this.timePeriod = timePeriod;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getEstimatedHours() {
        return this.estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getRegisteredHours() {
        return this.registeredHours;
    }

    public void setRegisteredHours(int registeredHours) {
        this.registeredHours = registeredHours;
    }

    public Event getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Event timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    @Override
    public String toString() {
        return "Activity{"
                + "activityName='" + activityName + '\''
                + ", activityType=" + activityType
                + ", users=" + users
                + ", estimatedHours=" + estimatedHours
                + ", registeredHours=" + registeredHours
                + ", timePeriod=" + timePeriod
                + '}';
    }
}
