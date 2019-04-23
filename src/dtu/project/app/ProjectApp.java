package dtu.project.app;

import dtu.project.enums.FindType;
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectApp {
	
	private UserRepository userRepository;
	private ProjectRepository projectRepository;
	
	public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

	/** Jonathan
	 * Generic method which converts a list of elements of type E into
	 * a stream, which collects all elements that contain the searchText
	 * in their toString method. The toString methods, must be generated
	 * in the given class to assure that they print out all their info.
	 * @param searchText
	 * @param searchList
	 * @param <E>
	 * @return
	 */
	private <E> List<E> search(String searchText, List<E> searchList) {
		return searchList.stream().filter(e -> e.toString().contains(searchText)).collect(Collectors.toList());
	}

	/** Jonathan
	 * Returns all projects that contain the search text.
	 * @param searchText
	 * @return
	 */
	public List<Project> searchProjects(String searchText) {
		return search(searchText, getProjectList());
	}

	/** Jonathan
	 * Returns all users that contains the search text.
	 * @param searchText
	 * @return
	 */
	public List<User> searchUser(String searchText) {
		return search(searchText, getUserList());
	}
	

	/** Jonathan
	 * Get activities assigned to a specific user.
	 * @param user
	 * @return
	 */
	public List<Activity> getActivitiesAssignedTo(User user) {
		List<Activity> activities = new ArrayList<>();
		for (Project project:getProjectList()) {
			for (Activity a : project.getActivities()) {
				if (a.getUsers().contains(user))
					activities.add(a);
			}
		}
		return activities;
	}

	/** Jonathan
	 * Returns the first user found who contains the search string
	 * @param name
	 * @return
	 */
	public User findUser(String name) {
		Optional<?> r = getUserList().stream().filter(user -> user.toString().contains(name)).findFirst();
		return r.isPresent() ?
				getUserList().stream().filter(user -> user.toString().contains(name)).findFirst().get() : null;
	}

	/**
	 * 
	 * @param findType
	 */
	public List<User> findUser(FindType findType, Event event) {
		List<User> users = null;
		switch (findType) {
			case FREE:
				users = new ArrayList<User>() {{
					addAll(getUserList());
				}};
				for (User user : getUserList()) {
					for (Activity key: user.getSchedule().keySet()) {
						for (Event e: user.getSchedule().get(key)) {
							if(!(event.getEndDate().isBefore(e.getStartDate()) || event.getStartDate().isAfter(e.getEndDate()))) {
								users.remove(user);
							}								
						}
					}
				}
				break;
			case UNAVAILABLE:
				users = new ArrayList<User>();
				for (User user : getUserList()) {
					for (Activity key: user.getSchedule().keySet()) {
						for (Event e: user.getSchedule().get(key)) {
							if(!(event.getEndDate().isBefore(e.getStartDate()) || event.getStartDate().isAfter(e.getEndDate()))) {
								users.add(user);
							}
						}
					}
				}
				break;
		}
		return users;
	}

	/**
	 * 
	 * @param project
	 * @param activity
	 */
	public void addActivity(Project project, Activity activity) {
		project.addActivity(activity);
	}

	/**
	 * 
	 * @param project
	 * @param activity
	 */
	public void removeActivity(Project project, Activity activity) {
		project.removeActivity(activity);
	}



	public UserRepository getUserRepository() {return this.userRepository;}
	public void setUserRepository(UserRepository userRepository) {this.userRepository = userRepository;}
	public ProjectRepository getProjectRepository() {return this.projectRepository;}
	public void setProjectRepository(ProjectRepository projectRepository) {this.projectRepository = projectRepository;}
	public List<User> getUserList() {return userRepository.getUserList();}
	public void setUserList(List<User> userList) {userRepository.setUserList(userList);}
	public List<Project> getProjectList() {return projectRepository.getProjectList();}
	public void setProjectList(List<Project> projectList) {projectRepository.setProjectList(projectList);}
	/**
	 * add project
	 * @param project
	 */
	public void addProject(Project project) {
		getProjectList().add(project);
	}

	/**
	 * delete project
	 * @param project
	 */
	public void removeProject(Project project) {
		getProjectList().remove(project);
	}
}