package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class RegisterHoursSteps {
	
	ProjectApp PA;
	
	public RegisterHoursSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}
	
	
	@Given("there exists a project in the list of projects")
	public void thereExistsAProjectInTheListOfProjects() {
	    try {
			PA.addProject(new Project.Builder().setProjectName("Test Project").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateProjectName e) {
			System.out.println(e);
		}
	}
	
	@Given("that project has an activity")
	public void thatProjectHasAnActivity() {
	    try {
			PA.addActivity(PA.getProject(0), new Activity.Builder().setActivityName("Test Activity").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateActivityName e) {
			System.out.println(e);
		}
	}
	
	@When("developer registers work from {string} to {string}")
	public void developerRegistersWorkFromTo(String string, String string2) {
	    PA.addHours(PA.getUser(0), string, string2, PA.getActivity(PA.getProject(0), 0), null);
	}
	
	@Then("the event exists in the activity registered hours")
	public void theEventExistsInTheActivityRegisteredHours() {
	    assertTrue(!PA.getActivity(PA.getProject(0), 0).getRegisteredHours().isEmpty());
	}
	
	@Then("the event exists in the schedule of the developer")
	public void theEventExistsInTheScheduleOfTheDeveloper() {
		assertTrue(!PA.getUserSchedule(0).isEmpty());
	}
}

