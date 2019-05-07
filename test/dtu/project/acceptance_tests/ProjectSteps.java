package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Activity;
import dtu.project.app.Event;
import dtu.project.app.Period;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.app.User;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;
import dtu.project.repo.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class ProjectSteps {

    private static final int User = 0;
	private static final int List = 0;
	ProjectApp PA;
	Project project;
	Activity activity;
    

    public ProjectSteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
    }

    @When("the user adds a project with name {string} and project type INTERNAL.")
    public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
        Project project = new Project.Builder()
                .setProjectName(string)
                .setProjectType(ProjectType.INTERNAL)
                .build();
    	PA.addProject(project);
    }

    @Then("the project exists in the list of projects.")
    public void theProjectExistsInTheListOfProjects() {
        assertTrue(!PA.getProjectList().isEmpty());
    }

    @When("time period {string} to {string}.")
    public void timePeriodTo(String string, String string2) {
        PA.getProjectList().get(0).setTimePeriod(new Event(string, string2));
    }

    @Given("a project with name {string}, project type INTERNAL.")
    public void aProjectWithNameProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
        PA.addProject(new Project.Builder()
                .setProjectName(string)
                .setProjectType(ProjectType.INTERNAL)
                .build());
    }

    @When("the user changes the name to {string}.")
    public void theUserChangesTheNameTo(String string) {
        PA.searchProjects("TestProject").get(0).setProjectName(string);
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
    		PA.getProjectList().get(0).setTimePeriod(new Event(string, string2));
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
		assertTrue(PA.search(null, PA.getUserList()).isEmpty());
	}

	@When("name is not null and list is null, cast error")
	public void nameIsNotNullAndListIsNullCastError() {
		assertTrue(PA.search("Shiloh", null).isEmpty());
	}

	@When("name and list is null, cast error")
	public void nameAndListIsNullCastError() {
		assertTrue(PA.search(null, null).isEmpty());
	}
    
    @Given("user exists")
    public void userExists() {
        PA.findUser("Shiloh Richmond");
    }

    @Then("find users activitylist")
    public void findUsersActivitylist() {
    	project = new Project.Builder().build();
    	try {
			project.addActivity(new Activity.Builder().setUser(PA.findUser("Shiloh Richmond")).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
        PA.getActivitiesAssignedTo(PA.findUser("Shiloh Richmond"));
    }
    
    @Then("adds activity")
    public void addsActivity() {
    	Activity a = new Activity.Builder().build();
    	Project p = new Project.Builder().build();
    	try {
			PA.addActivity(p, a);
		} catch (Exception e) {
			e.printStackTrace();
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
    	assertTrue(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0)));
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
}
