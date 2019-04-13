package dtu.project.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.app.ProjectApp;

public class UserSteps {
	
	ProjectApp PA;
	
	public UserSteps(ProjectApp PA) {
		this.PA = PA;
	}
	
	
	
	@Given("Administrator is logged in with username {string} and password {string}")
	public void administratorIsLoggedInWithUsernameAndPassword(String username, String passsword) {
		// Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("Administrator chooses user with username {string} and deletes that user")
	public void administratorChoosesUserWithUsernameAndDeletesThatUser(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Administrator user with username {string} no longer exists")
	public void administratorUserWithUsernameNoLongerExists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("Administrator chooses an existing user with name username {string} and changes it to {string}")
	public void administratorChoosesAnExistingUserWithNameUsernameAndChangesItTo(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Administrator there is now a user with username {string}")
	public void administratorThereIsNowAUserWithUsername(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("I exist in the list of administrators")
	public void iExistInTheListOfAdministrators() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("I navigate to the login page")
	public void iNavigateToTheLoginPage() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("I submit username and password")
	public void iSubmitUsernameAndPassword() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("I should be logged in")
	public void iShouldBeLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("Administrator enters the users account details and registers the user")
	public void administratorEntersTheUsersAccountDetailsAndRegistersTheUser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Administrator has registered the user")
	public void administratorHasRegisteredTheUser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("Administrator has previously registered a user with the same account details.")
	public void administratorHasPreviouslyRegisteredAUserWithTheSameAccountDetails() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Administrator gets the error message {string}")
	public void administratorGetsTheErrorMessage(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("User is not checked in")
	public void userIsNotCheckedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User checks in")
	public void userChecksIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("User is now checked in")
	public void userIsNowCheckedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("User is checked in")
	public void userIsCheckedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User checks out")
	public void userChecksOut() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("User is now checked out")
	public void userIsNowCheckedOut() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("User is logged in")
	public void userIsLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User creates plan with name {string}, with start date {string} and time {string} and end date {string} and time {string}, with participants {string}, {string}, {string} og {string}")
	public void userCreatesPlanWithNameWithStartDateAndTimeAndEndDateAndTimeWithParticipantsOg(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Plan is created with the participants and they are notified.")
	public void planIsCreatedWithTheParticipantsAndTheyAreNotified() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("Plan exists with parameters name {string}, with start date {string} and time {string} and end date {string} and time {string}, with participants {string}, {string}, {string} og {string}")
	public void planExistsWithParametersNameWithStartDateAndTimeAndEndDateAndTimeWithParticipantsOg(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User edits plan to end at {string}")
	public void userEditsPlanToEndAt(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Plan is changed with end date {string}")
	public void planIsChangedWithEndDate(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User deletes plan")
	public void userDeletesPlan() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("Plan is deleted.")
	public void planIsDeleted() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("User is not logged in")
	public void userIsNotLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User logs in with username {string} and password {string}")
	public void userLogsInWithUsernameAndPassword(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("User is now logged in")
	public void userIsNowLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@When("User Logs out")
	public void userLogsOut() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Then("User is logged out")
	public void userIsLoggedOut() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}

