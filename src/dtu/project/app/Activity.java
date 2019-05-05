package dtu.project.app;

import dtu.project.enums.ActivityType;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * 
 * @author Jonathan
 *
 */
public class Activity {

    private String activityName;
    private ActivityType activityType;
    private List<User> users;
    private int estimatedHours;
    private Map<User, List<Event>> registeredHours;
    private Event timePeriod;

    /**
     * Creates an object of type Activity with Builder Pattern.
     * To create an object of type Activity write the following
     * 
     * Activity activity = new Activity.Builder()
     * 
     * After the parenthesis append setter methods example
     * 
     * Activity activity = new Activity.Builder().setActivityName("Sequence Diagram").build();
     * 
     * The .build() returns an Activity with the appended setter methods as properties.
     * 
     * @param activityName the name of the activity.
     * @param activityType WORK, UNPAID or HOLIDAY.
     * @param users list of users assigned to do the task.
     * @param estimatedHours an estimation of how many hours the task takes to complete.
     * @param registeredHours a map of the users with a list of their registered hours
     * on the given activity.
     * @param timePeriod the period that this activity is running.
     */
    private Activity(String activityName, ActivityType activityType, List<User> users, int estimatedHours, Map<User, List<Event>> registeredHours, Event timePeriod) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.users = users;
        this.estimatedHours = estimatedHours;
        this.registeredHours = registeredHours;
        this.timePeriod = timePeriod;
    }

    /**
     * This class is the Builder Pattern for Activity.
     * It contains the same fields as Activity and can be used for easy
     * object creation.
     */
    public static class Builder {

        private String activityName;
        private ActivityType activityType;
        private List<User> users = new ArrayList<>();
        private int estimatedHours;
        private Map<User, List<Event>> registeredHours = new HashMap<>();
        private Event timePeriod;

        /**
         * Empty constructor, creates a clean Activity from scratch.
         */
        public Builder() {

        }
        
        /**
         * Takes the values from activity and assigns them to the current build
         * used for editing an activity.
         * @param activity 
         */
        public Builder(Activity activity) {
            this.activityName = activity.activityName;
            this.activityType = activity.activityType;
            this.users = activity.users;
            this.estimatedHours = activity.estimatedHours;
            this.registeredHours = activity.registeredHours;
            this.timePeriod = activity.timePeriod;
        }

        /**
         * Checks if activityName matches the regex "^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$"
         * @param activityName the name
         * @return Builder with activityName.
         * @throws PatternSyntaxException if it contains non-alphanumerical characters or starts with space.
         * @throws ArrayIndexOutOfBoundsException if the name is empty.
         */
        public Builder setActivityName(String activityName) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        	if(activityName.matches("[a-zA-Z1-9]+[ a-zA-Z1-9]*"))
				//check for duplicates too
				this.activityName = activityName;
			else
				throw new PatternSyntaxException(activityName + "Project name can neither be empty, nor only be a space. It must match regex: '[a-zA-Z1-9]+[ a-zA-Z1-9]*'", activityName, 0);
/*
        	if (!activityName.matches("^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$")) {
                if (activityName.length() == 0) {
                    throw new ArrayIndexOutOfBoundsException("Project names can't be empty");
                }
                char[] pn = activityName.toCharArray();

                if (pn[0] == ' ') {
                    throw new PatternSyntaxException(activityName
                            + "illegal project name for regex, no spaces at beginning: ^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$\nillegal character is \'"
                            + pn[0] + "\'", activityName, 0);
                } else {
                    for (int i = 0; i < pn.length; i++) {
                        if (!(Character.isAlphabetic(pn[i]) || Character.isDigit(pn[i]) || pn[i] == ' ')) {
                            throw new PatternSyntaxException(activityName
                                    + "illegal project name for regex: ^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$\nillegal character is \'" + pn[i]
                                    + "\'", activityName, i);

                        }
                    }
                }

            } else {
                this.activityName = activityName;
            }
*/
            return this;
        }

        public Builder setActivityType(ActivityType activityType) {
            this.activityType = activityType;
            return this;
        }

        public Builder setUser(User user) {
            this.users.clear();
            this.users.add(user);
            return this;
        }

        public Builder setEstimatedHours(int estimatedHours) throws NumberFormatException {
            this.estimatedHours = estimatedHours;
            return this;
        }

        public Builder setRegisteredHours(Map<User, List<Event>> registeredHours) throws NullPointerException {
            this.registeredHours = registeredHours;
            return this;
        }

        public Builder setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
        	Event event = new Event(startDate, endDate);
			if(event.getEndDate().isAfter(event.getStartDate()))
				this.timePeriod = event; //new Event(startDate, endDate);
			else throw new DateTimeParseException("End date: " + endDate + " must be after start date: " + startDate, endDate, 0);
            return this;
        }

        public Activity build() {
            return new Activity(activityName, activityType, users, estimatedHours, registeredHours, timePeriod);
        }

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

    public Map<User, List<Event>> getRegisteredHours() {
        return this.registeredHours;
    }

    public void setRegisteredHours(Map<User, List<Event>> registeredHours) {
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
