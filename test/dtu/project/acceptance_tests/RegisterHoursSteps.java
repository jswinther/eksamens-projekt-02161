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

public class RegisterHoursSteps extends StepsTemplate {
	
	private Project project;
	private Activity activity;

	public RegisterHoursSteps(InMemoryRepository MP) {
		super(MP);
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
	
	@When("developer registers sick from {string} to {string} with message {string}")
	public void developerRegistersSickFromToWithMessage(String string, String string2, String string3) {
	    PA.addHours(PA.getUser(0), string, string2, null, string3);
	}

	//Register sick, then the message says "sick"
	@Then("the message says {string}")
	public void theMessageSays(String string) {
	    assertTrue(PA.getUserSchedule(0).get(0).getMessage().equals(string));
	}
	
	@Given("a project that has at least one activity which has at least one user")
	public void aProjectThatHasAtLeastOneActivityWhichHasAtLeastOneUser() {
		project = new Project.Builder()
				.setProjectName("Screen Recorder Software")
				.build();
		try {
			PA.addProject(project);
		} catch (DuplicateProjectName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(PA.getProjectList().contains(project));
		activity = new Activity.Builder().setActivityName("Design").setUser(PA.getUser("Shiloh Richmond")).build();
		try {
			PA.addActivity(project, activity);
		} catch (DuplicateActivityName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(project.getActivities().contains(activity));
	}

	@When("user registers hours")
	public void userRegistersHours() {
		PA.addHours(PA.getUser(0), "2019-05-05 13:13", "2019-06-06 13:13", PA.getActivitiesAssignedTo(PA.getUser(0)).get(0), "troll");
	}

	@Then("an event is added to user schedule")
	public void anEventIsAddedToUserSchedule() {
		assertTrue(!PA.getUserSchedule("Shiloh Richmond").isEmpty());
	}
}

