package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	// test get/set of activity
		@When("user want to set an activity with name {string} to a project {string}")
		public void userWantToSetAnActivityWithNameToAProject(String string, String string2) throws DuplicateProjectName, DuplicateActivityName {
			Project p = new Project.Builder().setProjectName(string2).setProjectType(ProjectType.INTERNAL).build();
			PA.addProject(p);
			PA.setProject(0, p);
			Activity a = new Activity.Builder().build();
			PA.addActivity(p, a);
		    PA.setActivity(p, string, a);
		}

		@Then("the activity is set with the name {string} to project {string}")
		public void theActivityIsSetWithTheNameToProject(String string, String string2) {
		    PA.getActivity(PA.getProject(0), string);
		}

		@When("user wants to set an activity with index {int} to project of name {string}")
		public void userWantsToSetAnActivityWithIndexToProjectOfName(Integer int1, String string) throws DuplicateProjectName, DuplicateActivityName {
			Project p = new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build();
			PA.addProject(p);
			PA.setProject(0, p);
			Activity a = new Activity.Builder().build();
			PA.addActivity(p, a);
		    PA.setActivity(p, int1, a);
		}

		@Then("activity is set to index {int} to project of name {string}")
		public void activityIsSetToIndexToProjectOfName(Integer int1, String string) {
		    PA.getActivity(PA.getProject(0), int1);
		}
	
}
