package dtu.project.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.enums.ProjectType;
import dtu.project.exceptions.DuplicateProjectName;

public class ProjectAppSteps {
	
	ProjectApp PA;
	Project P;
}
