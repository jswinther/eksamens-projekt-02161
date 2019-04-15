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

public class ActivitySteps {

	ProjectApp PA;

	public ActivitySteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}


}

