package dtu.project.app;

import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
	private List<User> users = new ArrayList<>();
	private List<Plan> plans = new ArrayList<>();
        private User currentUser;
	public ProjectApp() {
		users.add(new User("username", "password"));
		plans.add(new Plan());
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	
	public <E> boolean add(E element, List<E> list) {
		return false;
		
	}
	public void login(String username, String passsword) {
            System.out.println("dtu.project.app.ProjectApp.login()");
		for (User user : users) {
                    if(user.getUsername().equals(username) && user.getPassword().equals(passsword)) {
                        this.currentUser = user;
                        System.out.println("Login Success");
                        break;
                    }
			
		}
		
	}
}
