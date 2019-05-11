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

public class TestAddActivity {

	ProjectApp PA;
	Activity activity;
        InMemoryRepository M;
        
        public TestAddActivity() {
            try {
			M = new InMemoryRepository();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PA = new ProjectApp(M, M);
        }
	
	@Test
	public void noInputTest() {
		Activity a1 = new Activity.Builder().setActivityName("").build();
	}
	
	@Test
	public void spaceInputTest() {
		try {
			Activity a1 = new Activity.Builder().setActivityName(" ").build();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
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
	
	@Test
	public void specialInputTest() {
		Activity a1 = new Activity.Builder().setActivityName("#").build();
	}

}
