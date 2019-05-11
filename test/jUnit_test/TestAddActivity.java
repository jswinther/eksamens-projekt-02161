package jUnit_test;

import static org.junit.Assert.*;

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

	public TestAddActivity() throws FileNotFoundException {
		M = new InMemoryRepository();
		this.PA = new ProjectApp(M, M);
	}

	@Test // A
	public void noInputTest() throws PatternSyntaxException{
		try {
			Activity a1 = new Activity.Builder().setActivityName("").build();
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}

	@Test // B
	public void spaceInputTest() throws PatternSyntaxException{
		try {
			Activity a1 = new Activity.Builder().setActivityName(" ").build();
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}

	@Test // C
	public void stringInputTest() {
		Activity a1 = new Activity.Builder().setActivityName("Activity1").build();
		Project p = new Project.Builder().setProjectName("project").build();
		try {
			PA.addProject(p);
			PA.addActivity(p, a1);
		} catch (DuplicateProjectName | DuplicateActivityName ex) {
			Logger.getLogger(TestAddActivity.class.getName()).log(Level.SEVERE, null, ex);
		}
		assertTrue(PA.getActivity(p, 0).equals(a1));   
	}

	@Test // D
	public void specialInputTest() {
		try {
			Activity a1 = new Activity.Builder().setActivityName("#").build();
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}
}

