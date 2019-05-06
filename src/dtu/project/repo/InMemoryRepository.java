package dtu.project.repo;

import dtu.project.app.Event;
import dtu.project.app.Period;
import dtu.project.app.Project;
import dtu.project.app.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class InMemoryRepository implements UserRepository, ProjectRepository {

    private Map<User, List<Period>> userMap;
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
    public Map<User, List<Period>> getUserMap() {
        return this.userMap;
    }
}
