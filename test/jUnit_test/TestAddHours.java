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
	Activity A;
	Project P;
	User U;
	InMemoryRepository M;
	Activity a = new Activity.Builder().build();
	String message = "test";
	String start = "1993-03-03 15:30";
	String end = "1993-08-03 15:30";
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
	public void setup() {
		assertTrue(a == null || message == null || start == null || end == null);
	}
	@Test
	public void test() {
		PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		assertTrue(!PA.getUserSchedule(0).isEmpty());
	}
	
	@Test
	public void test1() {
		try {
			PA.addHours(null, "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}
		
		}
	@Test
	public void test2() {
		try {
			PA.addHours(PA.getUser(0), null , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}
		
		}
	@Test
	public void test3() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , null, new Activity.Builder().build(), "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}
		
		}
	@Test
	public void test4() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", null, "test");
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}
		
		}
	@Test
	public void test5() {
		try {
			PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), null);
		} catch (Exception e) {
			assertTrue(PA.getUserSchedule(0).isEmpty());
		}
		
		}
	  @After
	  public void tearDown() throws Exception {
	    assertTrue(!(PA.getUserSchedule(0).isEmpty()));
	  }
	
	}