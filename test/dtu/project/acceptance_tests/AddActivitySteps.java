package dtu.project.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class AddActivitySteps extends StepsTemplate {

	private Project project;
	private Activity activity;
	private Activity a1;
	private Activity a2;

	public AddActivitySteps(InMemoryRepository MP) {
		super(MP);
		// TODO Auto-generated constructor stub
	}
	




}
