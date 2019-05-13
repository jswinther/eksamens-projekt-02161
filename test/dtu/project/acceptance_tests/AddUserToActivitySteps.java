package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.InMemoryRepository;

public class AddUserToActivitySteps extends StepsTemplate {

	private User user;
	private Activity activity;
	private Project project;
	public AddUserToActivitySteps(InMemoryRepository MP) {
		super(MP);
		activity = new Activity.Builder().build();
		
		// TODO Auto-generated constructor stub
	}


	// User is added to activity and another user
	@When("user named {string} is added to activity")
	public void userNamedIsAddedToActivity(String string) throws DuplicateUser {
		user = PA.getUser(string);
		PA.addUserToActivity(activity, user);
		PA.addUserToActivity(activity, PA.getUser(21));

	}

	@Then("user named {string} exists in list")
	public void userNamedExistsInList(String string) {
		assertTrue(activity.getUsers().contains(user));
	}
	// Duplicate user
	@When("another user named {string} is added to activity")
	public void another_user_named_is_added_to_activity(String string) throws DuplicateUser, DuplicateProjectName, DuplicateActivityName {
		user = PA.getUser(string);
		activity = new Activity.Builder().build();
		Project project = new Project.Builder().build();
		PA.addProject(project);
		PA.addActivity(project, activity);
		PA.addUserToActivity(activity, user);
		try {
			PA.addUserToActivity(activity, user);
		} catch (Exception e) {
			assertEquals(e.toString(), "User already exists in the selected activity");
		}
	}

	@Then("activity still only contains one user")
	public void throw_exception() throws DuplicateUser{
		assertTrue(activity.getUsers().size() == 1);
	}
}
