Feature: Developer edits existing projects.
	As a developer
	I want to edit an existing project
	Because I have new information about the project
	
Scenario: User edits a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user changes the name to "Test" .
    Then the name of the project is test.
   
Scenario: User wants to get a project
		When user searches for project with name "testProject" project is found
		
Scenario: User want to set a project
		When user set project with index 0 and project
		Then the project is set
		When user set project with name "testProject1" and project
		Then project is set with name "testProject1"
Scenario: user wants to set or get an activity
		When user want to set an activity with name "testactivity1" to a project "projectTest1"
		Then the activity is set with the name "testactivity1" to project "projectTest1"
		When user wants to set an activity with index 0 to project of name "test2"
		Then activity is set to index 0 to project of name "test2"

Scenario: User wants to see if activity list is empty for project
		When user wants to see if activitylist is empty for project with name "testProject2"