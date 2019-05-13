package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.InMemoryRepository;

public class RemoveUserFromActivitySteps extends StepsTemplate {

	Activity activity;
	User user;
	
	public RemoveUserFromActivitySteps(InMemoryRepository MP) {
		super(MP);
		activity = new Activity.Builder().build();
		try {
			PA.addUserToActivity(activity, PA.getUser("Shiloh Richmond"));
		} catch (DuplicateUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	// User is removed from activity
		@When("user named {string} is removed from activity")
		public void userNamedIsRemovedFromActivity(String string) {
			activity.getUsers().remove(user);
		}

		@Then("user named {string} no longer exists in list")
		public void userNamedNoLongerExistsInList(String string) {
			assertTrue((!activity.getUsers().contains(user)));
		}
}
