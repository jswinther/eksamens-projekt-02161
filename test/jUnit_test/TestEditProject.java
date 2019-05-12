package jUnit_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.DateTimeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class TestEditProject {

	ProjectApp PA;
	Activity activity;
	InMemoryRepository M;
	User user1 = PA.getUser("Shiloh Richmond");
	User user2 = PA.getUser("Joe");
	Project p;

	public TestEditProject() throws FileNotFoundException {
		M = new InMemoryRepository();
		this.PA = new ProjectApp(M, M);
	}

	@Before
	public void setup() throws DuplicateProjectName
	{
		p = new Project.Builder().setProjectName("Test").setTimePeriod("11-05-2019 13:00", "12-05-2019 13:00").setProjectManager(user1).build();
		PA.addProject(p);
	}

	@Test
	public void validEditTest()
	{
		Project p2 = new Project.Builder().setProjectName("UpdateTest").setTimePeriod("11-05-2019 13:00", "15-05-2019 13:00").setProjectManager(user2).build();
		try {
			PA.editProject(p, p2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void invalidTimeEditTest() throws DateTimeException
	{
		Project p2 = new Project.Builder().setProjectName("UpdateTest").setTimePeriod("15-05-2019 13:00", "11-05-2019 13:00").setProjectManager(user2).build();
		try {
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), DateTimeException.class);
		}
	}

	@After
	public void postCond()
	{
		PA.getProjectList().clear();
	}

}
