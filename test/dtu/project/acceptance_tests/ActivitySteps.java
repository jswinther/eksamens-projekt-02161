package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Event;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.FindType;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

import static org.junit.Assert.assertTrue;

public class ActivitySteps {

	ProjectApp PA;

	public ActivitySteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}
	
	@Given("projectmanager wants to find free user in time period {string} to {string}")
	public void projectmanagerWantsToFindFreeUserInTimePeriodTo(String string, String string2) {
		PA.findUser(FindType.FREE, new Event(string, string2));
	    // throw new cucumber.api.PendingException();
	}

	@Given("projectmanager wants to find unavailable user in time period {string} to {string}")
	public void projectmanagerWantsToFindUnavailableUserInTimePeriodTo(String string, String string2) {
		PA.findUser(FindType.UNAVAILABLE, new Event(string, string2));
	    // throw new cucumber.api.PendingException();
	}
	
	@Given("user wants to find free user {string}")
	public void userWantsToFindUser(String string) {
	    PA.findUser(string);
	    // throw new cucumber.api.PendingException();
	}
}
