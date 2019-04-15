package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Event;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;
import static org.junit.Assert.assertTrue;

public class ProjectSteps {
	
	ProjectApp PA;
	
	public ProjectSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}

	@When("the user adds a project with name {string} and project type INTERNAL.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) {
		PA.addProject(new Project(string, ProjectType.INTERNAL));
	}

	@Then("the project exists in the list of projects.")
	public void theProjectExistsInTheListOfProjects() {
		assertTrue(!PA.getProjectList().isEmpty());
	}

	@When("time period {string} to {string}.")
	public void timePeriodTo(String string, String string2) {
		PA.getProjectList().get(0).setTimePeriod(new Event(string, string2));
	}

	@Given("a project with name {string}, project type INTERNAL.")
	public void aProjectWithNameProjectTypeINTERNAL(String string) {
		PA.addProject(new Project(string, ProjectType.INTERNAL));
	}

	@When("the user changes the name to {string}.")
	public void theUserChangesTheNameTo(String string) {
		PA.searchProjects("TestProject").get(0).setProjectName(string);
	}

	@Then("the name of the project is test.")
	public void theNameOfTheProjectIsTest() {
		assertTrue(PA.searchProjects("TestProject").isEmpty());
	}

	@When("the user removes the project.")
	public void theUserRemovesTheProject() {
		PA.removeProject(PA.getProjectList().get(0));
	}

	@Then("the project doesn't exist in the list projects.")
	public void theProjectDoesnTExistInTheListProjects() {
		assertTrue(PA.getProjectList().isEmpty());
	}
}

