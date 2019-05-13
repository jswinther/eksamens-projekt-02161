package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class EditActivitySteps extends StepsTemplate {

	private Activity a1;
	private Activity a2;
	private Project project;
	private Activity activity;

	public EditActivitySteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}

	@Given("a project with an activity named {string} exists with time period {string} to {string}.")
	public void aProjectWithAnActivityNamedExistsWithTimePeriodTo(String string, String string2, String string3) throws DuplicateProjectName, DuplicateActivityName {
		project = new Project.Builder().build();
		activity = new Activity.Builder().setActivityName(string).setTimePeriod(string2, string3).build();
		try {
			PA.addProject(project);
		} catch (DuplicateProjectName e1) {
			throw new DuplicateProjectName();
		}
		try {
			PA.addActivity(project, activity);
		} catch (DuplicateActivityName e) {
			throw new DuplicateActivityName();
		}

	}
	
	// Edit activity
	@When("an activity exists named {string} and user changes activity name to {string}")
	public void an_activity_exists_named_and_user_changes_activity_name_to(String string, String string2) throws DuplicateActivityName {
		a1 = new Activity.Builder().setActivityName(string).build();
		a2 = new Activity.Builder().setActivityName(string2).build();
		project = new Project.Builder().build();
		project.addActivity(a1);
		try {
			PA.editActivity(project, a1, a2);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateActivityName.class);
		}
	}
	
	@Then("activity is named {string}")
	public void activityIsNamed(String string) {
	    assertTrue(project.getActivities().get(0).getActivityName().equals(string));
	}


	// Duplicate activity name edit
	@When("an activity exists that is named {string} and a user changes the name to {string}")
	public void an_activity_exists_that_is_named_and_a_user_changes_the_name_to(String string, String string2) throws DuplicateActivityName {
		activity = new Activity.Builder().setActivityName(string).build();
		Activity a4 = new Activity.Builder().setActivityName("Something Else").build();
		project = new Project.Builder().setProjectName("123").build();
		try {
			PA.addProject(project);
			PA.addActivity(project, activity);
			PA.addActivity(project, a4);
		} catch (DuplicateProjectName e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PA.editActivity(project, PA.getActivity(project, 0), a4);
		} catch (Exception e) {
			assertEquals(e.toString(), "Activity name already taken");
		}
		
		try {
			PA.editActivity(project, PA.getActivity(project, 0), activity);
		} catch (Exception e) {
			assertEquals(e.toString(), "Activity name already taken");
		}
	}
	
	@Then("activity name is still {string}")
	public void activityNameIsStill(String string) {
		assertTrue(activity.getActivityName().equals(string));
	}
	
	// Test for estimated hours working
	@When("user sets estimated hours {int} to an activity")
	public void userSetsEstimatedHoursToAnActivity(Integer int1) {
		activity = new Activity.Builder().setEstimatedHours(int1).build();
	}

	@Then("the activity estimated hours should be {int}")
	public void theActivityEstimatedHoursShouldBe(Integer int1) {
	    assertTrue(activity.getEstimatedHours() == int1);
	}
	
	// string is a, test for invalid estimated hours.
	@When("user sets estimated hours to {string}")
	public void userSetsEstimatedHoursTo(String string) throws NumberFormatException{
		try {
			activity = new Activity.Builder().setEstimatedHours(new Integer(string).intValue()).build();
		} catch (Exception e) {
			assertTrue(e.getClass().equals(NumberFormatException.class));
		}
	}

	@Then("activity is not added")
	public void activityIsNotAdded() {
		assertEquals(project.getActivities().get(0), activity);
	}
	
	// Test for duplicate activity name
	@When("user creates activity named {string}")
	public void userCreatesActivityNamed(String string) throws DuplicateActivityName, DuplicateProjectName{
		Activity a1 = new Activity.Builder().setActivityName(string).build();
		PA.addProject(project);
		try {
			PA.addActivity(project, a1);
		} catch (Exception e) {
			assertTrue(e.getClass().equals(DuplicateActivityName.class));
		}
	}

	@Then("activity named {string} is not added to project")
	public void activityNamedIsNotAddedToProject(String string) {
		assertTrue(project.getActivities().size() == 1);
	}
	
}
