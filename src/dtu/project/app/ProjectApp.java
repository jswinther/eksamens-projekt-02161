package dtu.project.app;

import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
	private List<User> users = new ArrayList<>();
	private List<Plan> plans = new ArrayList<>();
        private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
	public ProjectApp() {
		users.add(new User("Jonathan", "username", "password"));
		plans.add(new Plan("plan"));
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
            for (E ele : list) if(ele.toString().equals(element.toString())) return false;
            list.add(element); return true;
	}
        
        public <E> List<E> search(E element, List<E> list, String searchText) {
            List<E> returnList = new ArrayList<>();
            for (E ele : list) if(ele.toString().equals(element.toString())) returnList.add(ele);
            return returnList;
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
