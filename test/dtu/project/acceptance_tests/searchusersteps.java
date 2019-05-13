package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.repo.InMemoryRepository;

public class searchusersteps extends StepsTemplate {
	
	public searchusersteps(InMemoryRepository MP) {
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

}
