package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.lt.Duota;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.InMemoryRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

public class ActivitySteps extends StepsTemplate {

	public ActivitySteps(InMemoryRepository MP) {
		super(MP);
	}


	Project project;
	Activity activity;
	User user;
	private Activity a1;
	private Activity a2;
	
	

	
	
	
	



	// User is removed from activity
	@When("user named {string} is removed from activity")
	public void userNamedIsRemovedFromActivity(String string) {
		activity.getUsers().remove(user);
	}

	@Then("user named {string} no longer exists in list")
	public void userNamedNoLongerExistsInList(String string) {
		assertTrue((!activity.getUsers().contains(user)));
	}





	



    




}
