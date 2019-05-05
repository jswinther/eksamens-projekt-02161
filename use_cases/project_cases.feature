Feature: Project Manipulation

Scenario: User adds a project with name and project type.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    Then the project exists in the list of projects.

Scenario: User adds a project with name, project type and time period.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    Then the project exists in the list of projects.
    
Scenario: User adds illegal project time period
		When the user adds a project with name "TestProject" and project type INTERNAL.
		And time period "2021-05-05 08:00" to "2019-05-05 08:00".
		Then exception is thrown.
		
Scenario: User creates duplicate project
		Given a project with name "TestProject", project type INTERNAL.
		And time period "2019-05-05 08:00" to "2019-08-05 08:00".
		When user adds a project with name "TestProject", project type INTERNAL.
		And time period "2019-05-05 08:00" to "2019-08-05 08:00".
		
Scenario: User edits a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user changes the name to "Test".
    Then the name of the project is test.

Scenario: Removes a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user removes the project.
    Then the project doesn't exist in the list projects.
    
Scenario: search for a project
	Given a user want to find a project with the name "projectname"
	