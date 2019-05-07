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
    
    @Given("a project with an activity exists.")
    public void aProjectWithAnActivityExists() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("user named {string} is added to activity")
    public void userNamedIsAddedToActivity(String string) {
     	activity.addUser(PA.findUser(string));
    }

    @Then("user named {string} exists in list")
    public void userNamedExistsInList(String string) {
    	assertTrue(project.getActivities().get(0).equals(PA.findUser(string)));
    }

    @Given("user named {string} is part of an activity")
    public void userNamedIsPartOfAnActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("user named {string} is removed from activity")
    public void userNamedIsRemovedFromActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("user named {string} no longer exists in list")
    public void userNamedNoLongerExistsInList(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
    
//    // Add a user to an activity
//    @When("user named {string} is added to activity")
//    public void userNamedIsAddedToActivity(String string) {
//    	activity.addUser(PA.findUser(string));
//    }
//    
//    @Then("user exists in list")
//    public void userExistsInList(String string) {
//    	assertTrue(project.getActivities().get(0).equals(PA.findUser(string)));
//    }
//    
//    // User is removed from an activity
//    @When("user named {string} is removed from activity")
//    public void userNamedIsRemovedFromActivity(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
//    }
//
//    @Then("user no longer exists in list")
//    public void userNoLongerExistsInList() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
//    }
}
