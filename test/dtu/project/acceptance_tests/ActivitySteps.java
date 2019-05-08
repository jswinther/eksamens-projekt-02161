package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

public class ActivitySteps {

    ProjectApp PA;
    Project project;
    Activity activity;
    User user;

    public ActivitySteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
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
    
    // User is added to activity
    @When("user named {string} is added to activity")
    public void userNamedIsAddedToActivity(String string) {
    	user = PA.getUser(string);
    	activity.getUsers().add(user);
    }

    @Then("user named {string} exists in list")
    public void userNamedExistsInList(String string) {
    	assertTrue(activity.getUsers().contains(user));
    }
    
    // User is removed from activity
    @When("user named {string} is removed from activity")
    public void userNamedIsRemovedFromActivity(String string) {
    	activity.getUsers().remove(user);
    }

    @Then("user named {string} no longer exists in list")
    public void userNamedNoLongerExistsInList(String string) {
    	assertTrue((!activity.getUsers().contains(user)));
    }
    
    // Test for estimated hours working
    @When("user sets estimated hours {int} to an activity then the activity estimated hours should be {int}")
    public void userSetsEstimatedHoursToAnActivityThenTheActivityEstimatedHoursShouldBe(Integer int1, Integer int2) {
        try {
        	activity = new Activity.Builder(activity).setEstimatedHours(int1).build();
		} catch (NumberFormatException e) {
			assertTrue(e.toString().equals("java.lang.NumberFormatException: For input string: " + int1));
		}
        assertTrue(activity.getEstimatedHours() == int2);
    }
    
    // Test for invalid activity name
    @When("user creates activity named {string} then exception is thrown")
    public void userCreatesActivityNamedThenExceptionIsThrown(String string) {
    	boolean flag;
    	try {
			PA.addActivity(project, new Activity.Builder().setActivityName(string).build());
			flag = false;
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateActivityName e) {
			flag = true;
		}
    	assertTrue(flag);
    }
    
    // Test for invalid activity time period
    @When("user creates activity with time period {string} to {string}, then exception is thrown")
    public void userCreatesActivityWithTimePeriodToThenExceptionIsThrown(String string, String string2) throws DateTimeParseException, DuplicateActivityName {
        boolean flag;
    	try {
        	PA.addActivity(project, new Activity.Builder().setTimePeriod(string, string2).build());
        	flag = false;
		} catch (Exception e) {
			flag = true;
		}
    	assertTrue(flag);
    	
        
    }
    // string is a, test for invalid estimated hours.
    @When("user sets estimated hours to {string} then throw exception")
    public void userSetsEstimatedHoursToThenThrowException(String string) {
    	try {
    		activity = new Activity.Builder(activity).setEstimatedHours(new Integer(string).intValue()).build();
		} catch (Exception e) {
			assertTrue(true);
		}
    }
    
    // Test for duplicate project name
    @When("user creates activity named {string} then throw exception")
    public void userCreatesActivityNamedThenThrowException(String string) throws DuplicateActivityName {
    	Activity a1 = new Activity.Builder().setActivityName(string).build();
    	try {
    		
    		PA.addActivity(PA.getProject(0), a1);
    		PA.addActivity(PA.getProject(0), a1);
		} catch (Exception e) {
			assertTrue(e.getClass().equals(DuplicateActivityName.class));
		}
    }
    
    
}
