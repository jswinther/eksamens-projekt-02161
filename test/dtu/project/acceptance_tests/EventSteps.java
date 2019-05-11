package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.repo.InMemoryRepository;

public class EventSteps {
	
	ProjectApp PA;
    Project project;
    Activity activity;
    User user;

    public EventSteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
        this.project = new Project.Builder().build();
        this.user = PA.getUser(0);
        this.activity = new Activity.Builder().setUser(user).build();
    }
    
	@When("User adds event to Schedule with Activity and Message")
	public void userAddsEventToScheduleWithActivityAndMessage() {
	    PA.addHours(user, "2019-05-05 13:13", "2019-12-12 13:13", activity, "Working from home");
	}

	@Then("event is displayed with activity and message")
	public void eventIsDisplayedWithActivityAndMessage() {
		TimePeriod t = new TimePeriod("2019-05-05 13:13", "2019-12-12 13:13");
	    String expectedString = "Working from home" + (activity != null ? activity.getActivityName() : "") + " " + t;
	    assertEquals(expectedString, PA.getUserSchedule(0).get(0).toString());
	}

	@When("User adds event to Schedule with Activity")
	public void userAddsEventToScheduleWithActivity() {
		PA.addHours(user, "2019-05-05 13:13", "2019-12-12 13:13", activity, null);
	}

	@Then("event is displayed with activity")
	public void eventIsDisplayedWithActivity() {
		TimePeriod t = new TimePeriod("2019-05-05 13:13", "2019-12-12 13:13");
	    String expectedString = (activity != null ? activity.getActivityName() : "") + " " + t;
	    assertEquals(expectedString, PA.getUserSchedule(0).get(0).toString());
	}

	@When("User adds event to Schedule with Message")
	public void userAddsEventToScheduleWithMessage() {
		PA.addHours(user, "2019-05-05 13:13", "2019-12-12 13:13", null, "Sick");
	}
	
	@Then("event is displayed with message")
	public void eventIsDisplayedWithMessage() {
		TimePeriod t = new TimePeriod("2019-05-05 13:13", "2019-12-12 13:13");
	    String expectedString = "Sick" + " " + t;
	    assertEquals(expectedString, PA.getUserSchedule(0).get(0).toString());
	}


	@When("User adds event to Schedule")
	public void userAddsEventToSchedule() {
		PA.addHours(user, "2019-05-05 13:13", "2019-12-12 13:13", null, null);
	}

	@Then("event is displayed")
	public void eventIsDisplayed() {
		TimePeriod t = new TimePeriod("2019-05-05 13:13", "2019-12-12 13:13");
	    String expectedString = " " + t;
	    assertEquals(expectedString, PA.getUserSchedule(0).get(0).toString());
	}
}
