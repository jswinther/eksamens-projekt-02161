package jUnit_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

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
	public void test() {
		PA.addHours(PA.getUser(0), "1993-03-03 15:30" , "1993-08-08 13:30", new Activity.Builder().build(), "test");
		assertTrue(!PA.getUserSchedule(0).isEmpty());
	}
}
