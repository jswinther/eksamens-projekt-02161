package dtu.project.repo;

import dtu.project.app.Project;
import dtu.project.app.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InMemoryRepository implements UserRepository, ProjectRepository {

	private List<User> userList;
	private List<Project> projectList;
	private Scanner reader;
			
	
	public InMemoryRepository() throws FileNotFoundException {
		super();
		userList = new ArrayList<User>();
		projectList = new ArrayList<Project>();
		reader = new Scanner(new File("csvfiles/developers.csv"));
		while(reader.hasNextLine()) {
			this.userList.add(new User(reader.nextLine()));
		}
	}
	
	public List<Project> getProjectList() {return this.projectList;}
	public void setProjectList(List<Project> projectList) {this.projectList = projectList;}
	public List<User> getUserList() {return this.userList;}
	public void setUserList(List<User> userList) {this.userList = userList;}
}