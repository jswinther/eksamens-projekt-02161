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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class ProjectSteps {

    ProjectApp PA;
    Project project;

    public ProjectSteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
    }

    @When("the user adds a project with name {string} and project type INTERNAL.")
    public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
        project = new Project.Builder()
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


    /*
     * Test der skal sikre sig mod duplicate
    @Then("exception is thrown.")
    public void exceptionIsThrown() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
	*/
//    @When("user adds a project with name {string}, project type INTERNAL.")
//    public void userAddsAProjectWithNameProjectTypeINTERNAL(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
//    }
//
//    @Then("the user is now assigned to project as project manager.")
//    public void theUserIsNowAssignedToProjectAsProjectManager() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
//    }
    

	@Then("user hours is registered")
	public void userHoursIsRegistered() {
		PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:30", "2019-03-03 13:40", null, null);
	   // throw new cucumber.api.PendingException();
	}


    @Then("remove from list of free users")
    public void removeFromListOfFreeUsers() {
//    	PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:33", "2019-03-03 13:39", null, null);
    	PA.registerHours(PA.getUserList().get(0), "2019-03-03 13:25", "2019-03-03 13:35", null, null);
    	PA.registerHours(PA.getUserList().get(1), "2019-03-03 13:25", "2019-03-03 13:29", null, null);
    	PA.registerHours(PA.getUserList().get(2), "2019-03-03 13:35", "2019-03-03 13:45", null, null);
    	if(PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(0))) {
    		assertTrue(false);
    	}
    	else assertTrue(true);
    	PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(1));
    	PA.usersWhoAreFreeAt("2019-03-03 13:30", "2019-03-03 13:40").contains(PA.getUserList().get(2));
    }

    @Given("searchtext is null")
    public void searchtextIsNull() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("throw null pointer exception")
    public void throwNullPointerException() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("searchlist is null")
    public void searchlistIsNull() {
        // Write code here that turns the phrase above into concrete actions

    }
}


