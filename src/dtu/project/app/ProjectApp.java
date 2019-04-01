package dtu.project.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
	private List<User> users = new ArrayList<>();
	private List<Plan> plads = new ArrayList<>();
	public ProjectApp() {
		users.add(new User("username", "password"));
	}
}
