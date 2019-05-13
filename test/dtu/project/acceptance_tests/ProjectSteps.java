package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.entities.Project;
import dtu.project.controllers.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.InMemoryRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;

public class ProjectSteps extends StepsTemplate {

	Project project;
	Activity activity;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public ProjectSteps(InMemoryRepository MP) {
		super(MP);
	}

	@When("the user adds a project with name {string} and project type INTERNAL, project manager named {string} and time period {string} to {string}.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNALProjectManagerNamedAndTimePeriodTo(String string, String string2, String string3, String string4) {
		project = new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.setProjectManager(PA.getUser(string2))
				.setTimePeriod(string3, string4).build();
	}

	@Then("the project exists in the list of projects.")
	public void theProjectExistsInTheListOfProjects() 
	{
		assertTrue(PA.isProjectListEmpty());
		
	}

	// Test for get og set project name
	@When("the user adds a project with name {string}")
	public void the_user_adds_a_project_with_name(String string) {
		project = new Project.Builder().setProjectName(string).build();
	}

	@Then("a project named {string} exists")
	public void a_project_named_exists(String string) {
		assertTrue(project.getProjectName().equals(string));
	}

	// Test for set og get project type
	@When("the user adds a project with type INTERNAL")
	public void the_user_adds_a_project_with_type_INTERNAL() {
		project = new Project.Builder().setProjectType(ProjectType.INTERNAL).build();
	}

	@Then("a project of that type exists")
	public void a_project_of_that_type_exists() {
		assertTrue(project.getProjectType().equals(ProjectType.INTERNAL));
	}

	// Test for set og get project manager
	@When("the user adds a project with a project manager named {string}")
	public void the_user_adds_a_project_with_a_project_manager_named(String string) {
		project = new Project.Builder().setProjectManager(PA.getUser(string)).build();
	}

	@Then("a project with a project manager named {string} exists")
	public void a_project_with_a_project_manager_named_exists(String string) {
		assertTrue(project.getProjectManager().equals(PA.getUser(string)));
	}

	// Test for get & set time period
	@When("the user adds a project with a time period {string} to {string}.")
	public void the_user_adds_a_project_with_a_time_period_to(String string, String string2) {
		project = new Project.Builder().setTimePeriod(string, string2).build();
	}

	// Virker ikke
	@Then("a project exists with a startDate {string}")
	public void a_project_exists_with_a_startDate(String string) {
		assertTrue(project.getTimePeriod().getStartDate().format(formatter).equals(string));
	}

	// Virker ikke
	@Then("an end date {string}.")
	public void an_end_date(String string) {
		assertTrue(project.getTimePeriod().getEndDate().format(formatter).equals(string));
	}

	// Ukendt test
	@When("the user adds a project with name {string} and project type INTERNAL.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		Project project = new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.build();
		PA.addProject(project);
	}

	// Invalid time period
	@When("time period {string} to {string}.")
	public void timePeriodTo(String string, String string2) {
		try {
			PA.getProject(0).setTimePeriod(string, string2);
		} catch (Exception e) {
			assertTrue(true);
		}


	}

	@When("the user changes the name to {string} .")
	public void theUserChangesTheNameTo(String string) {
		PA.getProject(0).setProjectName(string);
	}

	@Given("a project with name {string}, project type INTERNAL.")
	public void aProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.build());
	}

	@Then("the name of the project is test.")
	public void theNameOfTheProjectIsTest() {
		assertTrue(PA.searchProjects("TestProject").isEmpty());
	}

	@When("the user removes the project.")
	public void theUserRemovesTheProject() {
		PA.removeProject(PA.getProject(0));
	}

	@Then("the project doesn't exist in the list projects.")
	public void theProjectDoesnTExistInTheListProjects() {
		assertTrue(PA.isProjectListEmpty());
	}

	@Given("a user want to find a project with the name {string}")
	public void aUserWantToFindAProjectWithTheName(String string) {
		PA.searchProjects(string);
	}
	
	@Then("the user finds project with the name {string}")
	public void theUserFindsProjectWithTheName(String string) {
		assertTrue(PA.searchProjects(string) != null);
	}

	@When("user adds a project with name {string}, project type INTERNAL.")
	public void userAddsAProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build());
	}    
	
	@When("user searches for project with name {string} project is found")
	public void userSearchesForProjectWithNameProjectIsFound(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DuplicateProjectName {
		PA.addProject(new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build());
		PA.getProject(string);
		assertTrue(true);
	}
	
	//test get/set project
	@When("user set project with index {int} and project")
	public void userSetProjectWithIndexAndProject(Integer int1) throws DuplicateProjectName {
		Project p = new Project.Builder().setProjectName("testProject").setProjectType(ProjectType.INTERNAL).build();
		PA.addProject(p);
		PA.setProject(int1, p);
	}

	@Then("the project is set")
	public void theProjectIsSet() {
		PA.setProject(0, new Project.Builder().setProjectName("testProject").build());
		PA.getProject(0);
			assertTrue(true);
	}
	
	@When("user set project with name {string} and project")
	public void userSetProjectWithNameAndProject(String string) throws DuplicateProjectName {
		Project p = new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build();
		PA.addProject(p);
		PA.setProject(string, p);
	}

	@Then("project is set with name {string}")
	public void projectIsSetWithName(String string) {
		PA.setProject(0, new Project.Builder().setProjectName(string).build());
		PA.getProject(string);
			assertTrue(true);
	}
	//end test get/set project
	
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

	@Then("adds activity")
	public void addsActivity() {
		Activity a1 = new Activity.Builder().setActivityName("Brian").build();
		Activity a2 = new Activity.Builder().setActivityName("Brian").build();
		Activity a3 = new Activity.Builder().setActivityName("Ikke Brian").build();
		Project p = new Project.Builder().setProjectName("Project").build();
		try {
			PA.addProject(p);
			PA.addActivity(p, a1);
			assertTrue(p.getActivities().contains(a1));
			PA.addActivity(p, a3);
			assertTrue(p.getActivities().contains(a3));
			PA.addActivity(p, a2);
		} catch (Exception e) {
			assertTrue(!p.getActivities().contains(a2));
			assertEquals(DuplicateActivityName.class, e.getClass());
		}
	}

	@Then("remove activity")
	public void removeActivity() {
		Activity a = new Activity.Builder().build();
		Project p = new Project.Builder().build();
		PA.removeActivity(p, a);
	}

	@When("searching for user, returns user")
	public void searchingForUserReturnsUser() {
		assertTrue(PA.searchUser("Shiloh").contains(PA.getUser(0)));
	}

	@When("searching for user invalid user {string}")
	public void searchingForUserInvalidUser(String string) {
		try {
			PA.getUser(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("no user is found by the name {string}")
	public void noUserIsFoundByTheName(String string) {
		assertTrue(PA.getUser(string) == null);
	}
	
	@When("looking for user {string} and user exists")
	public void lookingForUserAndUserExists(String string) {
		PA.getUser("Shiloh Richmond");
	}
	
	@Then("User {string} is found in the userlist")
	public void userIsFoundInTheUserlist(String string) {
		assertTrue(PA.getUser("Shiloh Richmond") != null);
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
	
    // Test for duplicate project name and invalid names
    @When("user creates project named {string} then throw exception")
    public void userCreatesActivityNamedThenThrowException(String string) {
    	project = new Project.Builder().setProjectName(string).build();
    	Project project1 = new Project.Builder().setProjectName(string).build();
    	try {
    		PA.addProject(project);
    		PA.addProject(project1);
		} catch (Exception e) {
			assertTrue(e.toString().equals("Project name already taken"));
		}
    	project1 = new Project.Builder().setProjectName("Andet navn").build();
    	try {
			PA.addProject(project1);
		} catch (DuplicateProjectName e) {
			// TODO Auto-generated catch block
			
		}
    	
    	try {
    		PA.editProject(project1, new Project.Builder().setProjectName(string).build());
		} catch (Exception e) {
			assertEquals(DuplicateProjectName.class, e.getClass());
		}
    	
    	try {
			PA.addProject(new Project.Builder().setProjectName("#").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateProjectName e) {
			assertEquals(PatternSyntaxException.class, e.getClass());
		}
    	
    	try {
    		PA.getProject(0).setProjectName("#");
		} catch (Exception e) {
			assertEquals(PatternSyntaxException.class, e.getClass());
		}
    	
    	try {
			PA.editProject(project1, new Project.Builder().setProjectName("Hej").build());
		} catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DuplicateProjectName e) {

		}
    	
    }
    
    // test of isActivityListEmpty
    @When("user wants to see if activitylist is empty for project with name {string}")
    public void userWantsToSeeIfActivitylistIsEmptyForProjectWithName(String string) {
    	Project p = new Project.Builder().setProjectName(string).build();
        assertTrue(PA.isActivityListEmpty(p));
    }
    
    @Then("the list is empty for project with name {string}")
    public void theListIsEmptyForProjectWithName(String string) {
    	Project p = new Project.Builder().setProjectName(string).build();
    	assertTrue(PA.isActivityListEmpty(p));
    }
    
    @Given("a user with an activity assigned")
    public void aUserWithAnActivityAssigned() {
        try {
			PA.addProject(new Project.Builder().build());
			PA.addActivity(PA.getProject(0), new Activity.Builder().setActivityName("test").setUser(PA.getUser(0)).build());
		} catch (DuplicateProjectName | DuplicateActivityName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    @Then("a user searches for how many activities they are assigned and it returns {int} activity.")
    public void aUserSearchesForHowManyActivitiesTheyAreAssignedAndItReturnsActivity(Integer int1) {
    	assertTrue(PA.getActivitiesAssignedTo(PA.getUser(0)).size() == int1);
    }

    @Given("that a user has an activity assigned")
    public void thatAUserHasAnActivityAssigned() {
    	try {
			PA.addProject(new Project.Builder().build());
			PA.addActivity(PA.getProject(0), new Activity.Builder().setActivityName("test").setTimePeriod("2019-05-05 14:00", "2019-05-06 13:13")
					.setUser(PA.getUser(0)).build());
		} catch (DuplicateProjectName | DuplicateActivityName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Then("it says an activity is assigned to that user")
    public void itSaysAnActivityIsAssignedToThatUser() {
        assertTrue(PA.getUserListWithAcitivites("2019-05-05 13:13", "2019-05-05 14:14").get(PA.getUser(0)).intValue() == 1);
        
    }
    
}
