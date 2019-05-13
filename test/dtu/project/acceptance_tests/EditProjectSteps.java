package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Project;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

public class EditProjectSteps extends StepsTemplate {

	public EditProjectSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}

	@When("the user changes the name to {string} .")
	public void theUserChangesTheNameTo(String string) {
		PA.getProject(0).setProjectName(string);
	}

	@Given("a project with name {string}, project type INTERNAL.")
	public void aProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.build());
	}

	@Then("the name of the project is test.")
	public void theNameOfTheProjectIsTest() {
		assertTrue(PA.searchProjects("TestProject").isEmpty());
	}

}
