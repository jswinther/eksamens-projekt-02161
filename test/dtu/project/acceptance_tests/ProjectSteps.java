package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Event;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

public class ProjectSteps {

    ProjectApp PA;

    public ProjectSteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
    }

    @When("the user adds a project with name {string} and project type INTERNAL.")
    public void theUserAddsAProjectWithNameAndProjectTypeINTERNAL(String string) throws PatternSyntaxException, ArrayIndexOutOfBoundsException, Exception {
        PA.addProject(new Project.Builder()
                .setProjectName(string)
                .setProjectType(ProjectType.INTERNAL)
                .build());
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

    // Fail test

    @When("user adds a project with name {string}, project type INTERNAL.")
    public void user_creates_illogical_time_plan(String string) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDateTime startDate = null;
    	LocalDateTime endDate = null;
//    	startDate = LocalDateTime.parse(startDate, formatter);
//    	endDate = LocalDateTime.parse(endDate, formatter);
//    	startDate = "2021-05-05 13:12"
//    	endDate = "2019-05-05 13:12";
    	
    	
    }

    @Then("exception is thrown")
    public void exception_is_thrown(String startDate, String endDate) {
    	throw new cucumber.api.PendingException();
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
}
