package app;

public class Activity {

	private User assignedUser;
	private List<User> assistingUsers;

	public User getAssignedUser() {
		return this.assignedUser;
	}

	/**
	 * 
	 * @param assignedUser
	 */
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public List<User> getAssistingUsers() {
		return this.assistingUsers;
	}

	/**
	 * 
	 * @param assistingUsers
	 */
	public void setAssistingUsers(List<User> assistingUsers) {
		this.assistingUsers = assistingUsers;
	}

}