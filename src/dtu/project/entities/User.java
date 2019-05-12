package dtu.project.entities;

/**
 * 
 * @author Sebastian
 *
 */
public class User {

    private String name;
    private String idName;

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
