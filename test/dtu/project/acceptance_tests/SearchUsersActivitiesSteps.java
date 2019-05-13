package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class SearchUsersActivitiesSteps extends StepsTemplate {

	public SearchUsersActivitiesSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
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

}
