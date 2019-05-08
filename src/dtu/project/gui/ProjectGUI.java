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
import dtu.project.repo.ProjectRepository;
import dtu.project.repo.UserRepository;
import java.util.ArrayList;
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
public class ProjectGUI extends ProjectApp {

	public ProjectGUI(UserRepository userRepository, ProjectRepository projectRepository) {
		super(userRepository, projectRepository);
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
