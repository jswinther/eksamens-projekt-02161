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
import dtu.project.repo.InMemoryRepository;

public class TestAddHours {
	ProjectApp PA;
	InMemoryRepository M;
	
	public TestAddHours() {

		try {
			M = new InMemoryRepository();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PA = new ProjectApp(M, M);
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


}