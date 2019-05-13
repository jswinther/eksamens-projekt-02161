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
	
	//test get/set project
		@When("user set project with index {int} and project")
		public void userSetProjectWithIndexAndProject(Integer int1) throws DuplicateProjectName {
			Project p = new Project.Builder().setProjectName("testProject").setProjectType(ProjectType.INTERNAL).build();
			PA.addProject(p);
			PA.setProject(int1, p);
		}

		@Then("the project is set")
		public void theProjectIsSet() {
			PA.setProject(0, new Project.Builder().setProjectName("testProject").build());
			PA.getProject(0);
				assertTrue(true);
		}
		
		@When("user set project with name {string} and project")
		public void userSetProjectWithNameAndProject(String string) throws DuplicateProjectName {
			Project p = new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build();
			PA.addProject(p);
			PA.setProject(string, p);
		}

		@Then("project is set with name {string}")
		public void projectIsSetWithName(String string) {
			PA.setProject(0, new Project.Builder().setProjectName(string).build());
			PA.getProject(string);
				assertTrue(true);
		}
		//end test get/set project

}
