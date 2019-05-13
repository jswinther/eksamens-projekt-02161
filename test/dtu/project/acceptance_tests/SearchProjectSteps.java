package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dtu.project.repo.InMemoryRepository;

public class SearchProjectSteps extends StepsTemplate {

	public SearchProjectSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}

	@Given("a user want to find a project with the name {string}")
	public void aUserWantToFindAProjectWithTheName(String string) {
		PA.searchProjects(string);
	}
	
	@Then("the user finds project with the name {string}")
	public void theUserFindsProjectWithTheName(String string) {
		assertTrue(PA.searchProjects(string) != null);
	}
}
