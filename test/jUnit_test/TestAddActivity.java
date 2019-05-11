package jUnit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;

public class TestAddActivity {

	ProjectApp PA;
	Activity activity;
	
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
	}
	
	@Test
	public void specialInputTest() {
		Activity a1 = new Activity.Builder().setActivityName("#").build();
	}

}
