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
	public void userNamedIsAddedToActivity(String string) throws DuplicateUser {
		user = PA.getUser(string);
		activity.getUsers().add(user);

	}

	@Then("user named {string} exists in list")
	public void userNamedExistsInList(String string) {
		assertTrue(activity.getUsers().contains(user));
	}
	// Duplicate user
	@When("another user named {string} is added to activity")
	public void another_user_named_is_added_to_activity(String string) throws DuplicateUser {
		user = PA.getUser(string);
		Activity a2 = new Activity.Builder().build();
		PA.addUserToActivity(a2, user);
		try {
			PA.addUserToActivity(a2, user);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateUser.class);
		}
	}

	@Then("throw exception")
	public void throw_exception() throws DuplicateUser{
		
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

	// Test for duplicate activity name
	@When("user creates activity named {string} then throw exception")
	public void userCreatesActivityNamedThenThrowException(String string) throws DuplicateActivityName {
		Activity a1 = new Activity.Builder().setActivityName(string).build();
		try {
			PA.addActivity(PA.getProject(0), a1);
			PA.addActivity(PA.getProject(0), a1);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateActivityName.class);
		}
    }
    
    
    
    // Test for om brugeren er tilf�jet til aktiviteten
    @When("user is added to an activity")
    public void userIsAddedToAnActivity() {
        try {
			PA.addActivity(new Project.Builder().build(), new Activity.Builder().setUser(user).build());
		} catch (DuplicateActivityName e) {
			System.err.println(e);
		}
        try {
			PA.addUserToActivity(PA.getActivity(PA.getProject(0), 0), PA.getUser(2));
		} catch (DuplicateUser e) {
			System.err.println(e);
		}
        assertTrue(PA.getActivity(PA.getProject(0), 0).getUsers().contains(PA.getUser(2)));
        
    }


    // Test for edit activity
    @Given("an activity exists named {string}")
    public void anActivityExistsNamed(String string) {
    	PA.getProjectList().clear();
    	project = new Project.Builder().setProjectName("test").setTimePeriod("2019-05-05 13:13", "2019-05-05 13:14").build();
    	try {
			PA.addProject(project);
		} catch (DuplicateProjectName e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	
			PA.addActivity(project, new Activity.Builder().setActivityName(string).build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateActivityName e) {
			System.err.println(e);
		}
        assertTrue(PA.getActivity(PA.getProject(0), 0) != null);
    }

    @When("a user changes activity name to {string}")
    public void aUserChangesActivityNameTo(String string) {
    	Activity a = new Activity.Builder().setActivityName(string).build();
    	System.out.println(a.getActivityName());
    	System.out.println(PA.getActivity(PA.getProject(0), 0).getActivityName());
        try {
			PA.editActivity(PA.getProject(0), PA.getActivity(PA.getProject(0), 0), a);
			System.out.println(PA.getActivity(PA.getProject(0), 0).getActivityName());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateActivityName e) {
			System.err.println(e);
		}
        System.out.println(PA.getActivity(PA.getProject(0), 0).getActivityName());
    }

    // Edit activity
	@When("an activity exists named {string} and user changes activity name to {string}")
	public void an_activity_exists_named_and_user_changes_activity_name_to(String string, String string2) throws DuplicateActivityName {
		Activity a1 = new Activity.Builder().setActivityName(string).build();
		Activity a2 = new Activity.Builder().setActivityName(string2).build();
		try {
			PA.editActivity(project, a1, a2);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateActivityName.class);
		}
	}


	// Duplicate activity name edit
	@When("an activity exists that is named {string} and a user changes the name to {string}")
	public void an_activity_exists_that_is_named_and_a_user_changes_the_name_to(String string, String string2) throws DuplicateActivityName {
		Activity a3 = new Activity.Builder().setActivityName(string).build();
		Activity a4 = new Activity.Builder().setActivityName(string2).build();
		try {
			PA.editActivity(project, a3, a4);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateActivityName.class);
		}
	}

	@Then("send error message")
	public void send_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}



}
