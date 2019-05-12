package jUnit_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

public class TestAddActivity {

	ProjectApp PA;
	Activity activity;
	InMemoryRepository M;
	Project p;
	Activity a1;

	public TestAddActivity() throws FileNotFoundException {
		M = new InMemoryRepository();
		this.PA = new ProjectApp(M, M);
	}

	@Before
	public void setup() throws DuplicateProjectName
	{
		p = new Project.Builder().build();
		PA.addProject(p);
		a1 = new Activity.Builder().build();
	}
	@Test // A 
	public void noInputTest() throws PatternSyntaxException{
		a1.setActivityName("");
		try {
			PA.addActivity(p, a1);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}

	@Test // B
	public void spaceInputTest() throws PatternSyntaxException{
		a1.setActivityName(" ");
		try {
			PA.addActivity(p, a1);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}

	@Test // C
	public void stringInputTest() {
		a1.setActivityName("Activity1");
		try {
			PA.addActivity(p, a1);
		} catch (DuplicateActivityName ex) {
			Logger.getLogger(TestAddActivity.class.getName()).log(Level.SEVERE, null, ex);
		}
		assertTrue(PA.getActivity(p, 0).equals(a1));   
	}

	@Test // D
	public void specialInputTest() {
		a1.setActivityName("#");
		try {
			PA.addActivity(p, a1);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}
	
	@After
	public void postCond()
	{
		PA.getProjectList().clear();
		p.getActivities().clear();
	}
}

