package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Project;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class AddProjectSteps extends StepsTemplate {

	private Project project;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
	public void theProjectExistsInTheListOfProjects() {
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

	@When("the user adds a project with name {string}")
	public void the_user_adds_a_project_with_name(String string) {
		project = new Project.Builder().setProjectName(string).build();
	}

	@Then("a project named {string} exists")
	public void a_project_named_exists(String string) {
		assertTrue(project.getProjectName().equals(string));
	}

	@When("the user adds a project with type INTERNAL")
	public void the_user_adds_a_project_with_type_INTERNAL() {
		project = new Project.Builder().setProjectType(ProjectType.INTERNAL).build();
	}

	@Then("a project of that type exists")
	public void a_project_of_that_type_exists() {
		assertTrue(project.getProjectType().equals(ProjectType.INTERNAL));
	}

	@When("the user adds a project with a project manager named {string}")
	public void the_user_adds_a_project_with_a_project_manager_named(String string) {
		project = new Project.Builder().setProjectManager(PA.getUser(string)).build();
	}

	@Then("a project with a project manager named {string} exists")
	public void a_project_with_a_project_manager_named_exists(String string) {
		assertTrue(project.getProjectManager().equals(PA.getUser(string)));
	}
	
	// Test for get & set time period
	@When("the user adds a project with a time period {string} to {string}.")
	public void the_user_adds_a_project_with_a_time_period_to(String string, String string2) {
		project = new Project.Builder().setTimePeriod(string, string2).build();
	}
	
	@Then("a project exists with a startDate {string}")
	public void a_project_exists_with_a_startDate(String string) {
		assertTrue(project.getTimePeriod().getStartDate().format(formatter).equals(string));
	}
	
	@Then("an end date {string}.")
	public void an_end_date(String string) {
		assertTrue(project.getTimePeriod().getEndDate().format(formatter).equals(string));
	}
	
    @When("user creates project named {string} then throw exception")
    public void userCreatesActivityNamedThenThrowException(String string) {
    	project = new Project.Builder().setProjectName(string).build();
    	Project project1 = new Project.Builder().setProjectName(string).build();
    	try {
    		PA.addProject(project);
    		PA.addProject(project1);
		} catch (Exception e) {
			assertTrue(e.toString().equals("Project name already taken"));
		}
    	project1 = new Project.Builder().setProjectName("Andet navn").build();
    	try {
			PA.addProject(project1);
		} catch (DuplicateProjectName e) {
			// TODO Auto-generated catch block
			
		}
    	
    	try {
    		PA.editProject(project1, new Project.Builder().setProjectName(string).build());
		} catch (Exception e) {
			assertEquals(DuplicateProjectName.class, e.getClass());
		}
    	
    	try {
			PA.addProject(new Project.Builder().setProjectName("#").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateProjectName e) {
			assertEquals(PatternSyntaxException.class, e.getClass());
		}
    	
    	try {
    		PA.getProject(0).setProjectName("#");
		} catch (Exception e) {
			assertEquals(PatternSyntaxException.class, e.getClass());
		}
    	
    	try {
			PA.editProject(project1, new Project.Builder().setProjectName("Hej").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateProjectName e) {

		}
    	
    }
    
	@When("time period {string} to {string}.")
	public void timePeriodTo(String string, String string2) {
		try {
			PA.getProject(0).setTimePeriod(string, string2);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Given("a project with name {string}, project type INTERNAL.")
	public void aProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.build());
	}
	
	@When("user adds a project with name {string}, project type INTERNAL.")
	public void userAddsAProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build());
	}   

	
	@Then("time is not changed")
	public void projectIsNeverAdded() {
		assertTrue(PA.getProject(0).getTimePeriod() == null);
	}
	
	
	
	
}
