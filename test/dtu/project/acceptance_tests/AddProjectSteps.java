package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Project;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

public class AddProjectSteps extends StepsTemplate {

	private Project project;

	public AddProjectSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}
	
	@When("the user adds a project with name {string} and project type INTERNAL, project manager named {string} and time period {string} to {string}.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNALProjectManagerNamedAndTimePeriodTo(String string, String string2, String string3, String string4) {
		project = new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.setProjectManager(PA.getUser(string2))
				.setTimePeriod(string3, string4).build();
	}

	@Then("the project exists in the list of projects.")
	public void theProjectExistsInTheListOfProjects() 
	{
		assertTrue(PA.isProjectListEmpty());
		
	}
	@When("the user adds a project with name {string} and project type INTERNAL.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		Project project = new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.build();
		PA.addProject(project);
	}

	// Test for get og set project name
	@When("the user adds a project with name {string}")
	public void the_user_adds_a_project_with_name(String string) {
		project = new Project.Builder().setProjectName(string).build();
	}

	@Then("a project named {string} exists")
	public void a_project_named_exists(String string) {
		assertTrue(project.getProjectName().equals(string));
	}

	// Test for set og get project type
	@When("the user adds a project with type INTERNAL")
	public void the_user_adds_a_project_with_type_INTERNAL() {
		project = new Project.Builder().setProjectType(ProjectType.INTERNAL).build();
	}

	@Then("a project of that type exists")
	public void a_project_of_that_type_exists() {
		assertTrue(project.getProjectType().equals(ProjectType.INTERNAL));
	}

	// Test for set og get project manager
	@When("the user adds a project with a project manager named {string}")
	public void the_user_adds_a_project_with_a_project_manager_named(String string) {
		project = new Project.Builder().setProjectManager(PA.getUser(string)).build();
	}

	@Then("a project with a project manager named {string} exists")
	public void a_project_with_a_project_manager_named_exists(String string) {
		assertTrue(project.getProjectManager().equals(PA.getUser(string)));
	}

}
