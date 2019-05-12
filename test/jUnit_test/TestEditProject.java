package jUnit_test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dtu.project.controllers.ProjectApp;
import dtu.project.entities.Activity;
import dtu.project.entities.Project;
import dtu.project.entities.User;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.repo.InMemoryRepository;

public class TestEditProject {

	ProjectApp PA;
	Activity activity;
	InMemoryRepository M;
	Project p;

	public TestEditProject() throws FileNotFoundException {
		M = new InMemoryRepository();
		this.PA = new ProjectApp(M, M);
	}

	@Before
	public void setup() throws DuplicateProjectName
	{
		p = new Project.Builder().setProjectName("Test").setTimePeriod("2019-10-19 10:15", "2019-12-19 10:15").build();
		PA.addProject(p);
		assertTrue(!PA.getProjectList().isEmpty() & PA.getProject(0).equals(p));
	}

	@Test // A
	public void validEditTest()
	{
		Project p2 = new Project.Builder().setProjectName("Test2").setTimePeriod("2017-09-19 10:15", "2019-12-19 10:15").build();
		try {
			PA.editProject(p, p2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(PA.getProject(0).getProjectName().equals(p2.getProjectName()));
		assertTrue(PA.getProject(0).getTimePeriod().equals(p2.getTimePeriod()));
	}
	
	@Test // B
	public void invalidTimeEditTest() throws DateTimeParseException
	{
		Project p2 = new Project.Builder().setProjectName("Test2").setTimePeriod("2019-09-19 10:15", "2019-12-19 10:15").build();
		try {
			p2.setTimePeriod("2020-09-19 10:15", "2019-09-19 10:15");
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), DateTimeParseException.class);
		}
	}
	
	@Test // C
	public void duplicateNameEditTest() throws DuplicateProjectName
	{
		Project p2 = new Project.Builder().setProjectName("Test").setTimePeriod("2019-09-19 10:15", "2019-12-19 10:15").build();
		try {
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), DuplicateProjectName.class);
		}
	}
	
	@Test // D
	public void invalidSpecialNameEditTest() throws PatternSyntaxException
	{
		Project p2 = new Project.Builder().setProjectName("Test").setTimePeriod("2019-09-19 10:15", "2019-12-19 10:15").build();
		try {
			p2.setProjectName("@");
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}
	
	@Test // E
	public void invalidEmptyNameEditTest() throws PatternSyntaxException
	{
		Project p2 = new Project.Builder().setProjectName("Test").setTimePeriod("2019-09-19 10:15", "2019-12-19 10:15").build();
		try {
			p2.setProjectName("");
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}
	
	@Test // F
	public void invalidSpaceNameEditTest() throws PatternSyntaxException
	{
		Project p2 = new Project.Builder().setProjectName("Test").setTimePeriod("2019-09-19 10:15", "2019-12-19 10:15").build();
		try {
			p2.setProjectName(" ");
			PA.editProject(p, p2);
		} catch (Exception e) {
			assertEquals(e.getClass(), PatternSyntaxException.class);
		}
	}

	@After
	public void postCond()
	{
		PA.getProjectList().clear();
		assertTrue(PA.getProjectList().isEmpty());
	}

}
