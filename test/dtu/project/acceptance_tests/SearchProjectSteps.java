package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Project;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateProjectName;
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
	
	@When("user searches for project with name {string} project is found")
	public void userSearchesForProjectWithNameProjectIsFound(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DuplicateProjectName {
		PA.addProject(new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build());
		PA.getProject(string);
	}
	
	@Then("the user finds project with the name {string}")
	public void theUserFindsProjectWithTheName(String string) {
		assertTrue(PA.searchProjects(string) != null);
	}
	
	
}
