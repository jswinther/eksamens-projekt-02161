package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.Activity;
import dtu.project.app.Event;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

import static org.junit.Assert.assertTrue;

public class ActivitySteps {

    ProjectApp PA;
    Project project;
    Activity activity;

    public ActivitySteps(InMemoryRepository MP) {
        this.PA = new ProjectApp(MP, MP);
    }
    

    @Given("projectmanager wants to find free user in time period {string} to {string}")
    public void projectmanagerWantsToFindFreeUserInTimePeriodTo(String string, String string2) {
        PA.usersWhoAreFreeAt(string2, string2);
    }

    @Given("projectmanager wants to find unavailable user in time period {string} to {string}")
    public void projectmanagerWantsToFindUnavailableUserInTimePeriodTo(String string, String string2) {
        
    }
    
    @Given("user wants to find free user {string}")
    public void userWantsToFindUser(String string) {
        PA.findUser(string);
    }
    
    // Add a user to an activity
    @Given("user named {string} is added to activity")
    public void userNamedIsAddedToActivity(String string) {
      
    }
    
    @Then("user exists in list")
    public void userExistsInList() {
    	assertTrue(project.getActivities().get(0).equals(PA.findUser("Shiloh Richmond")));
    }
    
    // User is removed from an activity
    @Given("user named {string} is removed from activity")
    public void userNamedIsRemovedFromActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("user no longer exists in list")
    public void userNoLongerExistsInList() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    // Needs implementation
    @When("the project has time period {string} to {string}.")
    public void theProjectHasTimePeriodTo(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the user adds a project with name {string} and project type INTERNAL and time period {string} to {string} then exception thrown")
    public void theUserAddsAProjectWithNameAndProjectTypeINTERNALAndTimePeriodToThenExceptionThrown(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("exception is thrown")
    public void exceptionIsThrown() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("a user is not free")
    public void aUserIsNotFree() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("user adds a project with name {string}, project type INTERNAL then exception is thrown")
    public void userAddsAProjectWithNameProjectTypeINTERNALThenExceptionIsThrown(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
    
}
