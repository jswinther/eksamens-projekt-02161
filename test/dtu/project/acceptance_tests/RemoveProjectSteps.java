package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.repo.InMemoryRepository;

public class RemoveProjectSteps extends StepsTemplate {

	public RemoveProjectSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}
	
	@When("the user removes the project.")
	public void theUserRemovesTheProject() {
		PA.removeProject(PA.getProject(0));
	}

	@Then("the project doesn't exist in the list projects.")
	public void theProjectDoesnTExistInTheListProjects() {
		assertTrue(PA.isProjectListEmpty());
	}

}
