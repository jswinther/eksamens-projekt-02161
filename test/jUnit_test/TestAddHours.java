package jUnit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;

public class TestAddHours {
	ProjectApp PA;
	Activity A;
	Project P;
	User U;
	
	@Test
	public void test() {
		PA.addHours(PA.getUser(0), "1993-03-03 13:30 15:30" , "1993-08-03 13:30 15:30", new Activity.Builder().build(), "test");
		assertTrue(!PA.getUserListWithAcitivites("1993-03-03 13:30 15:30" , "1993-08-03 13:30 15:30").isEmpty());
	}
}
