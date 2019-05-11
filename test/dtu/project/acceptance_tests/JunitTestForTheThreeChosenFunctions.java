package dtu.project.acceptance_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;

public class JunitTestForTheThreeChosenFunctions {
	ProjectApp PA;
	Project project;
	Activity activity;

    @Test
    public void testAddHours() {
    	String startDate = "2019-05-05 08:00";
    	String endDate = "2019-08-05 23:59";
    	Activity a = new Activity.Builder().build();
    	String message = "message given";
    	//PA.addHours(PA.getUser(0), startDate, endDate, activity, message);
    	if( testAddHours1(PA.getUser(0), startDate, endDate, a,    message) && 
    		testAddHours2(PA.getUser(0), startDate, endDate, a,    message) &&
    		testAddHours3(null,          startDate, endDate, a,    message) &&
    		testAddHours4(PA.getUser(0), null,      endDate, a,    message) &&
    		testAddHours5(PA.getUser(0), startDate, null,    a,    message) &&
    		testAddHours6(PA.getUser(0), startDate, endDate, null, message) &&
    		testAddHours7(PA.getUser(0), startDate, endDate, a,    null)
    	)
    		assertTrue(true);
    	else
    		assertEquals(1,2);
    }
    public boolean testAddHours1(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserListWithAcitivites(start, end) != null) {
    		return true;
    	}
    	else {
    		return false;	
    	}
    }
    @SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours2(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
	@SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours3(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours4(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours5(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours6(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean testAddHours7(User u, String start, String end, Activity a, String m) {
    	PA.addHours(u, start, end, a, m);
    	if(PA.getUserSchedule(0).contains(a)) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }    

}
