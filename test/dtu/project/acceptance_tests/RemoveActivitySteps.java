package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.repo.InMemoryRepository;

public class RemoveActivitySteps extends StepsTemplate {

	private Project project;
	private Activity activity;

	public RemoveActivitySteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}
	
	@When("user removes activity named {string}")
	public void userRemovesActivityNamed(String string) {
		project = new Project.Builder().build();
		activity = new Activity.Builder().setActivityName(string).build();
		project.addActivity(activity);
		assertTrue(project.getActivities().get(0).equals(activity));
		project.getActivities().remove(0);
	}

	@Then("activity named {string} no longer exists on list")
	public void activityNamedNoLongerExistsOnList(String string) {
		assertTrue(!(project.getActivities().contains(activity)));
	}

}
