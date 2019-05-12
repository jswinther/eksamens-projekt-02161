Feature: Developer edits existing projects.
	As a developer
	I want to edit an existing project
	Because I have new information about the project
	
Scenario: User edits a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user changes the name to "Test" .
    Then the name of the project is test.