/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.gui;

import dtu.project.app.Activity;
import dtu.project.app.TimePeriod;
import dtu.project.app.Event;
import dtu.project.app.Project;
import dtu.project.app.ProjectApp;
import dtu.project.app.User;
import dtu.project.app.Activity.Builder;
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
public class ProjectGUI {

    private final ProjectApp PA;

    public ProjectGUI(UserRepository userRepository, ProjectRepository projectRepository) {
        this.PA = new ProjectApp(userRepository, projectRepository);
    }

    public void addActivityButton(
            JComboBox<String> projectComboBox, 
            JTextField activityNameTextField,
            JTextField estimatedHoursTextField,
            JTextField activityStartDateTextField,
            JTextField activityEndDateTextField,
            JComboBox<String> activityUserComboBox) 
            throws DuplicateActivityName {
        PA.addActivity(PA.getProjectList().get(projectComboBox.getSelectedIndex()), 
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
        
        PA.editActivity(
                PA.getProjectList().get(projectListComboBox.getSelectedIndex()),
                PA.getProjectList().get(projectListComboBox.getSelectedIndex())
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
        PA.removeActivity(PA.getProjectList().get(projectListComboBox.getSelectedIndex()), 
                PA.getProjectList().get(projectListComboBox.getSelectedIndex())
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
            {
                list.forEach((e) -> {
                    addElement(e.toString());
                });
            }
        };
    }

    public DefaultListModel<String> getUserDefaultListModel() {
        return listToDefaultListModel(PA.getUserList());
    }

    public DefaultListModel<String> getProjectDefaultListModel() {
        return listToDefaultListModel(PA.getProjectList());
    }

    public DefaultListModel<String> getUserDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(PA.searchUser(searchText));
    }

    public DefaultListModel<String> getProjectDefaultListModelContaining(String searchText) {
        return listToDefaultListModel(PA.searchProjects(searchText));
    }

    public DefaultListModel<String> getUserActivitiesDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(PA.search(searchText, PA.getActivitiesAssignedTo(PA.searchUser(userName).get(0))));
    }

    public DefaultListModel<String> getUserScheduleDefaultListModelContaining(String userName, String searchText) {
        return listToDefaultListModel(PA.search(searchText, new ArrayList<>(PA.getUserMap().get(PA.searchUser(userName).get(0)))));
    }

    public void registerHours(User user, String startDate, String endDate, Activity activity, String message) {
        PA.registerHours(user, startDate, endDate, activity, message);
    }

    public List<User> usersWhoAreFreeAt(String startDate, String endDate) {
        return PA.usersWhoAreFreeAt(startDate, endDate);
    }

    public <E> List<E> search(String searchText, List<E> searchList) {
        return PA.search(searchText, searchList);
    }

    public List<Project> searchProjects(String searchText) {
        return PA.searchProjects(searchText);
    }

    public List<User> searchUser(String searchText) {
        return PA.searchUser(searchText);
    }

    public List<Activity> getActivitiesAssignedTo(User user) {
        return PA.getActivitiesAssignedTo(user);
    }

    public User findUser(String name) {
        return PA.findUser(name);
    }

    public void addActivity(Project project, Activity activity) throws DuplicateActivityName {
        PA.addActivity(project, activity);
    }

    public void removeActivity(Project project, Activity activity) {
        PA.removeActivity(project, activity);
    }

    public List<User> getUserList() {
        return PA.getUserList();
    }

    public Map<User, List<Event>> getUserMap() {
        return PA.getUserMap();
    }

    public List<Project> getProjectList() {
        return PA.getProjectList();
    }

    public void addProject(Project project) throws DuplicateProjectName {
        PA.addProject(project);
    }

    public void removeProject(Project project) {
        PA.removeProject(project);
    }

    public void editActivity(Project project, Activity currentActivity, Activity newActivity) throws DuplicateActivityName {
        PA.editActivity(project, currentActivity, newActivity);
    }
    
    

}
