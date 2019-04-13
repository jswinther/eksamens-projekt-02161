package dtu.project.app;

import java.util.List;

public class InMemoryRepository implements UserRepository, ProjectRepository {

	private List<User> userList;
	private List<Project> projectList;
	
	public List<Project> getProjectList() {return this.projectList;}
	public void setProjectList(List<Project> projectList) {this.projectList = projectList;}
	public List<User> getUserList() {return this.userList;}
	public void setUserList(List<User> userList) {this.userList = userList;}
}