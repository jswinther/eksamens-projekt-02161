package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.enums.ProjectType;
import dtu.project.repo.InMemoryRepository;

public class UserSteps {

	ProjectApp PA;
	User user;

	public UserSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
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

