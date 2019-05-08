package dtu.project.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import dtu.project.entities.Event;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;

public class InMemoryRepository implements UserRepository, ProjectRepository {

    private Map<User, List<Event>> userMap;
    private List<Project> projectList;
    private final Scanner reader;

    public InMemoryRepository() throws FileNotFoundException {
        super();
        userMap = new LinkedHashMap<>();
        projectList = new ArrayList<>();
        reader = new Scanner(new File("csvfiles/developers.csv"));
        while (reader.hasNextLine()) {
            this.userMap.put(new User(reader.nextLine()), new ArrayList<>());
        }
    }

    @Override
    public List<Project> getProjectList() {
        return this.projectList;
    }

    @Override
    public Map<User, List<Event>> getUserMap() {
        return this.userMap;
    }
}
