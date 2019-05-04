package dtu.project.app;

import dtu.project.enums.ActivityType;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Activity {

    private String activityName;
    private ActivityType activityType;
    private List<User> users;
    private int estimatedHours;
    private int registeredHours;
    private Event timePeriod;

    private Activity(String activityName, ActivityType activityType, List<User> users, int estimatedHours, int registeredHours, Event timePeriod) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.users = users;
        this.estimatedHours = estimatedHours;
        this.registeredHours = registeredHours;
        this.timePeriod = timePeriod;
    }

    public static class Builder {

        private String activityName;
        private ActivityType activityType;
        private final List<User> users = new ArrayList<>();
        private int estimatedHours;
        private int registeredHours;
        private Event timePeriod;
        
        public Builder setActivityName(String activityName) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
            if (!activityName.matches("^[a-zA-Z0-9_.-][ a-zA-Z0-9_.-]*$")) {
            if(activityName.length() == 0) throw new ArrayIndexOutOfBoundsException("Project names can't be empty");
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
            return this;
        }
        
        public Builder setActivityType(ActivityType activityType) {
            this.activityType = activityType;
            return this;
        }
        
        public Builder setUser(User user) {
            this.users.add(user);
            return this;
        }
        
        public Builder setEstimatedHours(int estimatedHours) throws NumberFormatException  {
            this.estimatedHours = estimatedHours;
            return this;
        }
        
        public Builder setRegisteredHours(int registeredHours) throws NumberFormatException {
            this.registeredHours = registeredHours;
            return this;
        }
        
        public Builder setTimePeriod(String startDate, String endDate) throws DateTimeParseException {
            this.timePeriod = new Event(startDate, endDate);
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
