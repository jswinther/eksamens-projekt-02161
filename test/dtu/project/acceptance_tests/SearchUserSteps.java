package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.repo.InMemoryRepository;

public class SearchUserSteps extends StepsTemplate {
	
	public SearchUserSteps(InMemoryRepository MP) {
		super(MP);
	}
	
	@When("searching for user {string}")
	public void searchingForUser(String string) {
	    PA.getUser(string);
	}

	@Then("user {string} is found")
	public void userIsFound(String string) {
	    assertEquals(PA.getUser(string), PA.getUser(0));
	}
	
	@When("searching for user, returns user")
	public void searchingForUserReturnsUser() {
		assertTrue(PA.searchUser("Shiloh").contains(PA.getUser(0)));
	}

	@When("searching for user invalid user {string}")
	public void searchingForUserInvalidUser(String string) {
		try {
			PA.getUser(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("no user is found by the name {string}")
	public void noUserIsFoundByTheName(String string) {
		assertTrue(PA.getUser(string) == null);
	}
	
	@When("looking for user {string} and user exists")
	public void lookingForUserAndUserExists(String string) {
		PA.getUser("Shiloh Richmond");
	}
	
	@Then("User {string} is found in the userlist")
	public void userIsFoundInTheUserlist(String string) {
		assertTrue(PA.getUser("Shiloh Richmond") != null);
	}

}
