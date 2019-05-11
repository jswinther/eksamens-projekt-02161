/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.gui;

import dtu.project.controllers.ProjectApp;
import dtu.project.controllers.ProjectController;
import dtu.project.controllers.UserController;
import dtu.project.entities.Activity;
import dtu.project.entities.Event;
import dtu.project.entities.Project;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;
import dtu.project.entities.Activity.Builder;
import dtu.project.exceptions.DuplicateActivityName;
import dtu.project.exceptions.DuplicateProjectName;
import dtu.project.exceptions.DuplicateUser;
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;

/**
 *
 * @author Jonat
 */
public class ProjectGUI {
	
	private ProjectApp PA;
	
	public ProjectGUI(UserRepository userRepository, ProjectRepository projectRepository) {
		PA = new ProjectApp(userRepository, projectRepository);
	}

	
    
    


    /**
	 * @param <E>
	 * @param string
	 * @param list
	 * @return
	 * @see dtu.project.controllers.ProjectApp#get(java.lang.String, java.util.List)
	 */
	public <E> E get(String string, List<E> list) {
		return PA.get(string, list);
	}






	/**
	 * @param <E>
	 * @param index
	 * @param list
	 * @return
	 * @see dtu.project.controllers.ProjectApp#get(int, java.util.List)
	 */
	public <E> E get(int index, List<E> list) {
		return PA.get(index, list);
	}






	/**
	 * @param projectName
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getProject(java.lang.String)
	 */
	public Project getProject(String projectName) {
		return PA.getProject(projectName);
	}






	/**
	 * @param index
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getProject(int)
	 */
	public Project getProject(int index) {
		return PA.getProject(index);
	}






	/**
	 * @param project
	 * @param activityName
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getActivity(dtu.project.entities.Project, java.lang.String)
	 */
	public Activity getActivity(Project project, String activityName) {
		return PA.getActivity(project, activityName);
	}






	/**
	 * @param project
	 * @param index
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getActivity(dtu.project.entities.Project, int)
	 */
	public Activity getActivity(Project project, int index) {
		return PA.getActivity(project, index);
	}






	/**
	 * @param userName
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUser(java.lang.String)
	 */
	public User getUser(String userName) {
		return PA.getUser(userName);
	}






	/**
	 * @param index
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUser(int)
	 */
	public User getUser(int index) {
		return PA.getUser(index);
	}






	/**
	 * @param user
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getActivitiesAssignedTo(dtu.project.entities.User)
	 */
	public List<Activity> getActivitiesAssignedTo(User user) {
		return PA.getActivitiesAssignedTo(user);
	}






	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUserListWithAcitivites(java.lang.String, java.lang.String)
	 */
	public HashMap<User, Integer> getUserListWithAcitivites(String startDate, String endDate) {
		return PA.getUserListWithAcitivites(startDate, endDate);
	}






	/**
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUserList()
	 */
	public List<User> getUserList() {
		return PA.getUserList();
	}






	/**
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUserMap()
	 */
	public Map<User, List<Event>> getUserMap() {
		return PA.getUserMap();
	}






	/**
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getProjectList()
	 */
	public List<Project> getProjectList() {
		return PA.getProjectList();
	}






	/**
	 * @param project
	 * @throws DuplicateProjectName
	 * @see dtu.project.controllers.ProjectApp#addProject(dtu.project.entities.Project)
	 */
	public void addProject(Project project) throws DuplicateProjectName {
		PA.addProject(project);
	}






	/**
	 * @param project
	 * @param activity
	 * @throws DuplicateActivityName
	 * @see dtu.project.controllers.ProjectApp#addActivity(dtu.project.entities.Project, dtu.project.entities.Activity)
	 */
	public void addActivity(Project project, Activity activity) throws DuplicateActivityName {
		PA.addActivity(project, activity);
	}






	/**
	 * @param project
	 * @param currentActivity
	 * @param newActivity
	 * @throws DuplicateActivityName
	 * @see dtu.project.controllers.ProjectApp#editActivity(dtu.project.entities.Project, dtu.project.entities.Activity, dtu.project.entities.Activity)
	 */
	public void editActivity(Project project, Activity currentActivity, Activity newActivity)
			throws DuplicateActivityName {
		PA.editActivity(project, currentActivity, newActivity);
	}






	/**
	 * @param activity
	 * @param user
	 * @throws DuplicateUser
	 * @see dtu.project.controllers.ProjectApp#addUserToActivity(dtu.project.entities.Activity, dtu.project.entities.User)
	 */
	public void addUserToActivity(Activity activity, User user) throws DuplicateUser {
		PA.addUserToActivity(activity, user);
	}






	/**
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @param activity
	 * @param message
	 * @see dtu.project.controllers.ProjectApp#addHours(dtu.project.entities.User, java.lang.String, java.lang.String, dtu.project.entities.Activity, java.lang.String)
	 */
	public void addHours(User user, String startDate, String endDate, Activity activity, String message) {
		PA.addHours(user, startDate, endDate, activity, message);
	}






	/**
	 * @param arg0
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		return PA.equals(arg0);
	}






	/**
	 * @param <E>
	 * @param index
	 * @param element
	 * @param list
	 * @see dtu.project.controllers.ProjectApp#set(int, java.lang.Object, java.util.List)
	 */
	public <E> void set(int index, E element, List<E> list) {
		PA.set(index, element, list);
	}






	/**
	 * @param <E>
	 * @param searchText
	 * @param searchList
	 * @return
	 * @see dtu.project.controllers.ProjectApp#search(java.lang.String, java.util.List)
	 */
	public <E> List<E> search(String searchText, List<E> searchList) {
		return PA.search(searchText, searchList);
	}






	/**
	 * @param index
	 * @param project
	 * @see dtu.project.controllers.ProjectApp#setProject(int, dtu.project.entities.Project)
	 */
	public void setProject(int index, Project project) {
		PA.setProject(index, project);
	}






	/**
	 * @param searchText
	 * @return
	 * @see dtu.project.controllers.ProjectApp#searchProjects(java.lang.String)
	 */
	public List<Project> searchProjects(String searchText) {
		return PA.searchProjects(searchText);
	}






	/**
	 * @param userName
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUserSchedule(java.lang.String)
	 */
	public List<Event> getUserSchedule(String userName) {
		return PA.getUserSchedule(userName);
	}






	/**
	 * @param index
	 * @return
	 * @see dtu.project.controllers.ProjectApp#getUserSchedule(int)
	 */
	public List<Event> getUserSchedule(int index) {
		return PA.getUserSchedule(index);
	}






	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return PA.hashCode();
	}






	/**
	 * @param <E>
	 * @param string
	 * @param element
	 * @param list
	 * @see dtu.project.controllers.ProjectApp#set(java.lang.String, java.lang.Object, java.util.List)
	 */
	public <E> void set(String string, E element, List<E> list) {
		PA.set(string, element, list);
	}






	/**
	 * @param projectName
	 * @param project
	 * @see dtu.project.controllers.ProjectApp#setProject(java.lang.String, dtu.project.entities.Project)
	 */
	public void setProject(String projectName, Project project) {
		PA.setProject(projectName, project);
	}






	/**
	 * @param project
	 * @param activityName
	 * @param activity
	 * @see dtu.project.controllers.ProjectApp#setActivity(dtu.project.entities.Project, java.lang.String, dtu.project.entities.Activity)
	 */
	public void setActivity(Project project, String activityName, Activity activity) {
		PA.setActivity(project, activityName, activity);
	}






	/**
	 * @param project
	 * @param index
	 * @param activity
	 * @see dtu.project.controllers.ProjectApp#setActivity(dtu.project.entities.Project, int, dtu.project.entities.Activity)
	 */
	public void setActivity(Project project, int index, Activity activity) {
		PA.setActivity(project, index, activity);
	}






	/**
	 * @param searchText
	 * @return
	 * @see dtu.project.controllers.ProjectApp#searchUser(java.lang.String)
	 */
	public List<User> searchUser(String searchText) {
		return PA.searchUser(searchText);
	}






	/**
	 * @param hm
	 * @return
	 * @see dtu.project.controllers.ProjectApp#sortByValue(java.util.HashMap)
	 */
	public HashMap<User, Integer> sortByValue(HashMap<User, Integer> hm) {
		return PA.sortByValue(hm);
	}






	/**
	 * @param project
	 * @see dtu.project.controllers.ProjectApp#removeProject(dtu.project.entities.Project)
	 */
	public void removeProject(Project project) {
		PA.removeProject(project);
	}






	/**
	 * @param project
	 * @param activity
	 * @see dtu.project.controllers.ProjectApp#removeActivity(dtu.project.entities.Project, dtu.project.entities.Activity)
	 */
	public void removeActivity(Project project, Activity activity) {
		PA.removeActivity(project, activity);
	}






	/**
	 * @return
	 * @see dtu.project.controllers.ProjectApp#isProjectListEmpty()
	 */
	public boolean isProjectListEmpty() {
		return PA.isProjectListEmpty();
	}






	/**
	 * @param project
	 * @return
	 * @see dtu.project.controllers.ProjectApp#isActivityListEmpty(dtu.project.entities.Project)
	 */
	public boolean isActivityListEmpty(Project project) {
		return PA.isActivityListEmpty(project);
	}






	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return PA.toString();
	}






	public void addActivityButton(
            JComboBox<String> projectComboBox, 
            JTextField activityNameTextField,
            JTextField estimatedHoursTextField,
            JTextField activityStartDateTextField,
            JTextField activityEndDateTextField,
            JComboBox<String> activityUserComboBox) 
            throws DuplicateActivityName {
        addActivity(getProjectList().get(projectComboBox.getSelectedIndex()), 
               new Activity.Builder()
                        .setActivityName(activityNameTextField.getText())
                        .setEstimatedHours(Integer.valueOf(estimatedHoursTextField.getText()))
                        .setTimePeriod(activityStartDateTextField.getText(), 
                                activityEndDateTextField.getText())
                       .setUser(getUser(activityUserComboBox.getSelectedIndex()))
                .build());
    }

    public void editActivity(
            JComboBox<String> projectListComboBox, 
            JComboBox<String> activityListComboBox, 
            JTextField activityNameTextField,
            JTextField estimatedHoursTextField,
            JTextField activityStartDateTextField,
            JTextField activityEndDateTextField,
            JComboBox<String> activityUserComboBox) 
            throws DuplicateActivityName {
        
        editActivity(
                getProjectList().get(projectListComboBox.getSelectedIndex()),
                getProjectList().get(projectListComboBox.getSelectedIndex())
                .getActivities().get(activityListComboBox.getSelectedIndex()),
                new Activity.Builder()
                        .setActivityName(activityNameTextField.getText())
                        .setEstimatedHours(Integer.valueOf(estimatedHoursTextField.getText()))
                        .setTimePeriod(activityStartDateTextField.getText(), 
                                activityEndDateTextField.getText())
                .build()
                                
        
        );
                
    }
    
    public void removeActivity(
            JComboBox<String> projectListComboBox,
            JComboBox<String> activitySelectComboBox) {
        removeActivity(getProjectList().get(projectListComboBox.getSelectedIndex()), 
                getProjectList().get(projectListComboBox.getSelectedIndex())
                .getActivities().get(activitySelectComboBox.getSelectedIndex()));
    }
    
    public void generateReport(Project project) {
        int counter = 0;
        System.out.println(""
                + "\nProject Name:\t\t" + project.getProjectName()
                + "\nProject Type:\t\t" + project.getProjectType()
                + "\nManager:\t\t" + (project.getProjectManager() == null ? "not decided" : project.getProjectManager())
                + "\nTime Period:\t\t" + project.getTimePeriod().getStartDate() + " to " + project.getTimePeriod().getEndDate()
                + "\n\nActivity Status\n___________________________\n");
        for (Activity activity : project.getActivities()) {
            System.out.println("Activity Number:\t" + counter
                    + "\nActivity Name:\t\t" + activity.getActivityName()
                    + "\nResponsible:\t\t" + activity.getUsers().get(0)
                    + "\nTime Period:\t\t" + activity.getTimePeriod().getStartDate() + " to " + activity.getTimePeriod().getEndDate()
                    + "\nEstimated Hours:\t" + activity.getEstimatedHours());
            counter++;
            for (Map.Entry<User, List<TimePeriod>> entry : activity.getRegisteredHours().entrySet()) {
                User key = entry.getKey();
                System.out.println(key + " registered these activites");
                List<TimePeriod> value = entry.getValue();
                for (TimePeriod event : value) {
                    System.out.println(event);
                }
            }
            System.out.println("\n");
        }
    }
    

    /**
     * Jonathan Converts a list of elements in to a default list model which is
     * used for the GUI. otherwise this code will be repeated and we want to
     * follow the DRY principle.
     *
     * @param <E>
     * @param list
     * @return
     */
    private <E> DefaultListModel<String> listToDefaultListModel(List<E> list) {
        return new DefaultListModel<String>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                list.forEach((e) -> {
                    addElement(e.toString());
                });
            }
        };
    }
    
    /**
     * Jonathan Converts a list of elements in to a default list model which is
     * used for the GUI. otherwise this code will be repeated and we want to
     * follow the DRY principle.
     *
     * @param <E>
     * @param list
     * @return
     */
    private <E> DefaultComboBoxModel<String> listToDefaultComboBoxModel(List<E> list) {
        return new DefaultComboBoxModel<String>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
                list.forEach((e) -> {
                    addElement(e.toString());
                });
            }
        };
    }

    public DefaultListModel<String> getUserDefaultListModel() {
        return listToDefaultListModel(getUserList());
    }

    public DefaultListModel<String> getProjectDefaultListModel() {
        return listToDefaultListModel(getProjectList());
    }
    
    
    public DefaultListModel<String> getUserDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(searchUser(searchText));
    }

    public DefaultListModel<String> getProjectDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(searchProjects(searchText));
    }

    public DefaultListModel<String> getUserActivitiesDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(search(searchText, getActivitiesAssignedTo(searchUser(userName).get(0))));
    }

    public DefaultListModel<String> getUserScheduleDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(search(searchText, new ArrayList<>(getUserMap().get(searchUser(userName).get(0)))));
    }
    
    public DefaultComboBoxModel<String> getProjectDefaultComboBoxModel() {
    	return listToDefaultComboBoxModel(getProjectList());
    }
    
    public DefaultComboBoxModel<String> getUserDefaultComboBoxModel() {
    	return listToDefaultComboBoxModel(getUserList());
    }

    public DefaultComboBoxModel<String> getActivitytDefaultComboBoxModel(Project project) {
    	return listToDefaultComboBoxModel(project.getActivities());
    }


   
    
    
}
