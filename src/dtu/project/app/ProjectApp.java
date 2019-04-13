package dtu.project.app;

import java.util.List;

public class ProjectApp {
	
	private UserRepository userRepository;
	private ProjectRepository projectRepository;

	public ProjectApp(UserRepository userRepository, ProjectRepository projectRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}
	
	/**
	 * 
	 * @param project
	 * @param activity
	 * @param user
	 * @param hours
	 */
	public void registerHours(Project project, Activity activity, User user, int hours) {
		// TODO - implement ProjectApp.registerHours
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param findtType
	 */
	public List<User> findUser(FindType findtType) {
		// TODO - implement ProjectApp.findUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param project
	 * @param activity
	 */
	public void addActivity(Project project, Activity activity) {
		// TODO - implement ProjectApp.addActivity
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param project
	 * @param activity
	 */
	public void editActivity(Project project, Activity activity) {
		// TODO - implement ProjectApp.editActivity
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param project
	 * @param activity
	 */
	public void deleteActivity(Project project, Activity activity) {
		// TODO - implement ProjectApp.deleteActivity
		throw new UnsupportedOperationException();
	}



	public UserRepository getUserRepository() {return this.userRepository;}
	public void setUserRepository(UserRepository userRepository) {this.userRepository = userRepository;}
	public ProjectRepository getProjectRepository() {return this.projectRepository;}
	public void setProjectRepository(ProjectRepository projectRepository) {this.projectRepository = projectRepository;}
}