package dtu.project.entities;

/**
 * 
 * @author Sebastian
 *
 */
public class User {

    private String name;
    private String idName;

    // Constructor that gives all users a unique ID, consisting of the 4 first letters in their name
    // If the person has less than 4 characters in their name, the last characters will be filled with "x"
    public User(String name) {
        super();
        this.name = name;
        if (name.length() > 4) {
            this.idName = name.substring(0, 4).toLowerCase();
        } else {
            this.idName = name.toLowerCase();
            while (idName.length() < 4) {
                idName += "x";
            }
        }
    }

    public String getName() {
        return this.name;
    }
    
    // Only created for testing purposes
    public void setName(String name) {
    	this.name = name;
        if (name.length() > 4) {
            this.idName = name.substring(0, 4).toLowerCase();
        } else {
            this.idName = name.toLowerCase();
            while (idName.length() < 4) {
                idName += "x";
            }
        }
    }

    public String getIdName() {
        return this.idName;
    }

    @Override
    public String toString() {
        return name;
    }
}
