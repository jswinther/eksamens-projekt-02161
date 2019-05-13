package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class AddActivitySteps extends StepsTemplate {

	private Project project;
	private Activity activity;
	private Activity a1;
	private Activity a2;

	public AddActivitySteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}
	
	// Test for invalid activity name
	@When("user creates activity named {string} to project")
	public void userCreatesActivityNamedToProject(String string) throws PatternSyntaxException, DuplicateProjectName {
		project = new Project.Builder().build();
		PA.addProject(project);	
		try {
			a1 = new Activity.Builder().setActivityName(string).build();
			PA.addActivity(project, a1);
		} catch (Exception e) {
			assertTrue(e.getClass().equals(PatternSyntaxException.class));
		}
	}

	@Then("activity is not added to project")
	public void activityIsNotAddedToProject() {
		assertTrue(PA.getProject(0).getActivities().isEmpty());
	}
	
	// Test for invalid activity time period
		@When("user creates activity with time period {string} to {string}")
		public void userCreatesActivityWithTimePeriodTo(String string, String string2) throws DuplicateProjectName, DateTimeParseException {
			PA.addProject(project);
			try {
				Activity a1 = new Activity.Builder().setTimePeriod(string, string2).build();
				PA.addActivity(project, a1);
			} catch (Exception e) {
				assertTrue(e.getClass().equals(DateTimeParseException.class));
			}
		}
		
		@When("an activity named {string} is added to a project")
		public void anActivityNamedIsAddedToAProject(String string) {
			activity = new Activity.Builder().setActivityName(string).build();
			project = new Project.Builder().build();
			project.addActivity(activity);
		}

		@Then("an activity named {string} exists in project")
		public void anActivityNamedExistsInProject(String string) {
			assertTrue(project.getActivities().get(0).getActivityName().equals(string));
		}




}
