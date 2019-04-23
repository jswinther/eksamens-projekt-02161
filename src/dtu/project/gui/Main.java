package dtu.project.gui;

import java.io.FileNotFoundException;

import dtu.project.app.*;
import dtu.project.enums.ActivityType;
import dtu.project.enums.FindType;
import dtu.project.repo.InMemoryRepository;
import dtu.project.enums.ProjectType;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		InMemoryRepository MR = new InMemoryRepository();
		ProjectApp PA = new ProjectApp(MR, MR);

		for (User u : PA.getUserList()) {
			System.out.println(u.toString());
		}
		
		PA.addProject(new Project("Et projekt navn", ProjectType.INTERNAL));
		System.out.println(PA.searchProjects("Et projekt navn").get(0));
		PA.getProjectList().get(0).addActivity(new Activity("Project Manager", ActivityType.WORK, 10, new Event("2019-04-14 13:13", "2020-05-10 14:14"), PA.getUserList().get(0)));
		System.out.println(PA.searchProjects("Et projekt navn").get(0));
		System.out.println(PA.getActivitiesAssignedTo(PA.getUserList().get(0)));
		PA.getUserList().get(0).addHours(PA.getActivitiesAssignedTo(PA.getUserList().get(0)).get(0), new Event("2019-10-10 08:00", "2019-10-10 17:00"));
		System.out.println(PA.getUserList().get(0));
		System.out.println(PA.getUserList().get(0).getSchedule());
		System.out.println("Free Users" + PA.findUser(FindType.FREE, new Event("2019-10-10 08:00", "2019-10-11 17:00")));
		System.out.println("Free Users" + PA.findUser(FindType.FREE, new Event("2019-10-11 08:00", "2019-10-11 17:00")));
		System.out.println("Busy Users" + PA.findUser(FindType.UNAVAILABLE, new Event("2019-10-10 08:00", "2019-10-11 17:00")));
		System.out.println("Busy Users" + PA.findUser(FindType.UNAVAILABLE, new Event("2019-10-11 08:00", "2019-10-11 17:00")));
		System.out.println(PA.getProjectList().get(0).getActivities().get(0).getRegisteredHours());

		System.out.println(PA.getActivitiesAssignedTo(PA.getUserList().get(0)));

	}
}
