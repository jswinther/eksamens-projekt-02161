package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

public class UserSteps {

	ProjectApp PA;

	public UserSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}
	
	@Given("a project named {string} with {int} active activities.")
	public void aProjectNamedWithActiveActivities(String string, Integer int1) {
	    PA.addProject(new Project.Builder()
	    		.setProjectName(string)
	    		.setProjectType(ProjectType.EXTERNAL)
	    		.setTimePeriod("2019-05-05 13:00", "2020-05-05 13:00")
	    		.build());
	}

	@When("user is assigned to project as project manager")
	public void userIsAssignedToProjectAsProjectManager() {
	    PA.getProjectList().set(0, new Project.Builder(PA.getProjectList().get(0)).setProjectManager(PA.getUserList().get(0)).build());
	}

	@Then("the user is now assigned to project as project manager")
	public void theUserIsNowAssignedToProjectAsProjectManager() {
	    assertTrue(PA.getUserList().get(0).equals(PA.getProjectList().get(0).getProjectManager()));
	}
	

}

