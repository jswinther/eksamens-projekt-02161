package jUnit_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

/**
 * Whitebox Test for the addHours Method in ProjectApp and from UserController.
 * @author Nicholas
 *
 */

public class TestAddHours {
	ProjectApp PA;
	InMemoryRepository M;
	
	User u;
	String startDate;
	String endDate;
	Activity a;
	String m;
	Project p;
	
	public TestAddHours() {

		try {
			M = new InMemoryRepository();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PA = new ProjectApp(M, M);
	}
	
	@Before
	public void setup() throws DuplicateProjectName, DuplicateActivityName
	{
		p = new Project.Builder().build();
		PA.addProject(p);
		u = PA.getUser(0);
		startDate = "1993-03-03 15:30";
		endDate = "1993-08-08 13:30";
		a = new Activity.Builder().build();
		PA.addActivity(p, a);
		m = "test";
		assertTrue(!PA.getProjectList().isEmpty());
	}
	
	@Test
	public void addHoursWithValidInputs() {
		PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		assertTrue(!PA.getUserSchedule(0).isEmpty());
	}

	@Test
	public void addHoursWithInvalidUser() {
		try {
			PA.addHours(null, "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}

	}
	@Test
	public void addHoursWithInvalidStartDate() {
		try {
			PA.addHours(PA.getUser(0), null , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}

	}
	@Test
	public void addHoursWithInvalidEndDate() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , null, new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}

	}
	@Test
	public void addHoursWithoutActivity() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", null, "test");
			assertTrue(!PA.getUserSchedule(0).isEmpty());
		} catch (Exception e) {
			
		}

	}
	@Test
	public void addHoursWithoutMessage() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), null);
			assertTrue(!PA.getUserSchedule(0).isEmpty());
		} catch (Exception e) {
			
		}

	}
	
	@After
	public void postCond()
	{
		PA.getProjectList().clear();
		p.getActivities().clear();
		assertTrue(p.getActivities().isEmpty() & PA.getProjectList().isEmpty());
	}


}