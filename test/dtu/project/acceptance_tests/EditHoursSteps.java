package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.TimePeriod;
import dtu.project.repo.InMemoryRepository;

public class EditHoursSteps extends StepsTemplate {

	private Activity activity;

	public EditHoursSteps(InMemoryRepository MP) {
		super(MP);
		activity = new Activity.Builder().build();
	}
	
	@When("user edits hours")
	public void userEditsHours() {
		PA.getUserSchedule("Shiloh Richmond").get(0).setActivity(activity);
		PA.getUserSchedule("Shiloh Richmond").get(0).setMessage("cake");
		PA.getUserSchedule("Shiloh Richmond").get(0).setTimePeriod(new TimePeriod("2023-05-05 13:13", "2024-05-05 13:13"));
	}

	@Then("the event is changed")
	public void theEventIsChanged() {
		assertTrue(PA.getUserSchedule("Shiloh Richmond").get(0).getActivity().equals(activity));
		assertTrue(PA.getUserSchedule("Shiloh Richmond").get(0).getMessage().equals("cake"));
		assertTrue(PA.getUserSchedule("Shiloh Richmond").get(0).getTimePeriod().toString().equals(new TimePeriod("2023-05-05 13:13", "2024-05-05 13:13").toString()));
	}

}
