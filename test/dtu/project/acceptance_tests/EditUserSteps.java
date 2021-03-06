package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.User;
import dtu.project.repo.InMemoryRepository;

public class EditUserSteps extends StepsTemplate {

	private User user;

	public EditUserSteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}

	@Given("a user.")
	public void aUser() {
	    user = PA.getUser(0);
	    assertTrue(user.equals(PA.getUser(0)));
	}

	@When("user changes name to {string}")
	public void userChangesNameTo(String string) {
	    user.setName(string);
	    assertEquals(user.getName(), string);
	    assertEquals(user.getIdName(), new User(string).getIdName());
	}

	@Then("name is changed to {string}")
	public void nameIsChangedTo(String string) {
	    assertTrue(user.getName().equals(string));
	}
}
