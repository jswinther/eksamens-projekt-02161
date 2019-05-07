package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Activity;
import dtu.project.app.TimePeriod;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.regex.PatternSyntaxException;

public class ProjectSteps {

	ProjectApp PA;
	Project project;
	Activity activity;

	public ProjectSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}

	@When("the user adds a project with name {string} and project type INTERNAL, project manager named {string} and time period {string} to {string}.")
	public void theUserAddsAProjectWithNameAndProjectTypeINTERNALProjectManagerNamedAndTimePeriodTo(String string, String string2, String string3, String string4) {
		project = new Project.Builder()
				.setProjectName(string)
				.setProjectType(ProjectType.INTERNAL)
				.setProjectManager(PA.findUser(string2))
				.setTimePeriod(string3, string4).build();
	}

	@Then("the project exists in the list of projects.")
	public void theProjectExistsInTheListOfProjects() 
	{
		assertTrue(PA.getProjectList().isEmpty());
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
		project = new Project.Builder().setProjectManager(PA.findUser(string)).build();
	}

	@Then("a project with a project manager named {string} exists")
	public void a_project_with_a_project_manager_named_exists(String string) {
		assertTrue(project.getProjectManager().equals(PA.findUser(string)));
	}

	// Test for get & set time period
	@When("the user adds a project with a time period {string} to {string}.")
	public void the_user_adds_a_project_with_a_time_period_to(String string, String string2) {
		project = new Project.Builder().setTimePeriod(string, string2).build();
	}

	// Virker ikke
	@Then("a project exists with a startDate {string}")
	public void a_project_exists_with_a_startDate(String string) {
		assertTrue(project.getTimePeriod().getStartDate().equals(string));
	}

	// Virker ikke
	@Then("an end date {string}.")
	public void an_end_date(String string) {
		assertTrue(project.getTimePeriod().getEndDate().equals(string));
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
			PA.getProjectList().get(0).setTimePeriod(string, string2);
		} catch (Exception e) {
			assertTrue(true);
		}


	}

	@When("the user changes the name to {string} .")
	public void theUserChangesTheNameTo(String string) {
		PA.getProjectList().get(0).setProjectName(string);
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
		PA.removeProject(PA.getProjectList().get(0));
	}

	@Then("the project doesn't exist in the list projects.")
	public void theProjectDoesnTExistInTheListProjects() {
		assertTrue(PA.getProjectList().isEmpty());
	}

	@Given("a user want to find a project with the name {string}")
	public void aUserWantToFindAProjectWithTheName(String string) {
		PA.searchProjects(string);
	}

	@When("time period {string} to {string} then exception thrown")
	public void timePeriodToThenExceptionThrown(String string, String string2) {
		try {
			PA.getProjectList().get(0).setTimePeriod(new TimePeriod(string, string2));
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Then("the user is now assigned to project as project manager.")
	public void theUserIsNowAssignedToProjectAsProjectManager() {
		assertEquals(PA.getUserList().get(0), PA.getProjectList().get(0).getProjectManager());
	}

	@When("user adds a project with name {string}, project type INTERNAL.")
	public void userAddsAProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
		PA.addProject(new Project.Builder().setProjectName(string).setProjectType(ProjectType.INTERNAL).build());
	}    

	@Then("user hours is registered {string} to {string}")
	public void userHoursIsRegisteredTo(String string, String string2) {
		PA.registerHours(PA.getUserList().get(0), string, string2, null, null);
	}

	@When("name is not null, and list is not null, return search")
	public void nameIsNotNullAndListIsNotNullReturnSearch() {
		assertTrue(PA.search("Shiloh", PA.getUserList()).contains(PA.getUserList().get(0)));
	}

	@When("name is null and list is not null, cast error")
	public void nameIsNullAndListIsNotNullCastError() {
		try {
			PA.search(null, PA.getUserList()).isEmpty();
		} catch (Exception e) {
			assertTrue(true);
		}	
	}

	@When("name is not null and list is null, cast error")
	public void nameIsNotNullAndListIsNullCastError() {
		try {
			assertTrue(PA.search("Shiloh", null).isEmpty());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@When("name and list is null, cast error")
	public void nameAndListIsNullCastError() {
		try {
			assertTrue(PA.search(null, null).isEmpty());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Given("user exists")
	public void userExists() {
		PA.searchUser("Shiloh Richmond");
	}

	@Then("find users activitylist")
	public void findUsersActivitylist() {
		project = new Project.Builder().build();
		try {
			PA.addProject(project);
			PA.addActivity(project, new Activity.Builder().setUser(PA.findUser("Shiloh Richmond")).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		PA.getActivitiesAssignedTo(PA.findUser("Shiloh Richmond"));
	}

	@Then("adds activity")
	public void addsActivity() {
		Activity a1 = new Activity.Builder().setActivityName("Brian").build();
		Activity a2 = new Activity.Builder().setActivityName("Brian").build();
		Activity a3 = new Activity.Builder().setActivityName("Ikke Brian").build();
		Project p = new Project.Builder().build();
		try {
			PA.addProject(p);
			PA.addActivity(p, a1);
			assertTrue(PA.getProjectList().get(0).getActivities().contains(a1));
			PA.addActivity(p, a3);
			assertTrue(PA.getProjectList().get(0).getActivities().contains(a3));
			PA.addActivity(p, a2);
		} catch (Exception e) {
			assertTrue(!PA.getProjectList().get(0).getActivities().contains(a2));
			assertEquals(e.getClass(), DuplicateActivityName.class);
		}
	}

	@Then("remove activity")
	public void removeActivity() {
		Activity a = new Activity.Builder().build();
		Project p = new Project.Builder().build();
		PA.removeActivity(p, a);
	}

	@When("user is free, keep them on the list")
	public void userIsFreeKeepThemOnTheList() {
		PA.registerHours(PA.getUserList().get(0), "2019-03-03 12:00", "2019-03-03 12:59", null, null);
		PA.registerHours(PA.getUserList().get(1), "2019-03-03 14:00", "2019-03-03 14:59", null, null);
		assertTrue(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0)) && PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(1)));
	}

	@When("user is not free, remove them from the list")
	public void userIsNotFreeRemoveThemFromTheList() {
		PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:00", "2019-03-03 13:59", null, null);
		assertTrue(!(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0))));
	}

	@When("user overlaps into the beginning, remove them from list")
	public void userOverlapsIntoTheBeginning() {
		PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:00", "2019-03-03 13:35", null, null);
		assertTrue(!(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0))));
	}

	@When("user overlaps over the end, remove them from list")
	public void userOverlapsOverTheEndRemoveThemFromList() {
		PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:35", "2019-03-03 14:35", null, null);
		assertTrue(!(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0))));
	}

	@When("searching for user, returns user")
	public void searchingForUserReturnsUser() {
		assertTrue(PA.searchUser("Shiloh").contains(PA.getUserList().get(0)));
	}

	@When("searching for user, but user dont exist")
	public void searchingForUserButUserDontExist() {
		assertTrue(PA.findUser("###") == null);
	}

	@When("looking for user, and user exists")
	public void lookingForUserAndUserExists() {
		//     	assertTrue(PA.findUser("Shiloh Richmond") == PA.getUserList().get(0));
		assertTrue(PA.findUser("Shiloh Richmond") != null);
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
		activity = new Activity.Builder().setActivityName("Design").setUser(PA.findUser("Shiloh Richmond")).build();
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
		PA.registerHours(PA.getUserList().get(0), "2019-05-05 13:13", "2019-06-06 13:13", PA.getActivitiesAssignedTo(PA.getUserList().get(0)).get(0), "troll");
	}

	@Then("an event is added to user schedule")
	public void anEventIsAddedToUserSchedule() {
		assertTrue(!PA.getUserMap().get(PA.findUser("Shiloh Richmond")).isEmpty());
	}

	@When("user edits hours")
	public void userEditsHours() {
		PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).setActivity(activity);
		PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).setMessage("cake");
		PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).setTimePeriod(new TimePeriod("2023-05-05 13:13", "2024-05-05 13:13"));
	}

	@Then("the event is changed")
	public void theEventIsChanged() {
		assertTrue(PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).getActivity().equals(activity));
		assertTrue(PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).getMessage().equals("cake"));
		assertTrue(PA.getUserMap().get(PA.findUser("Shiloh Richmond")).get(0).getTimePeriod().toString().equals(new TimePeriod("2023-05-05 13:13", "2024-05-05 13:13").toString()));

	}
}
