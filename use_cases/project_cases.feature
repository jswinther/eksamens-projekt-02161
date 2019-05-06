Feature: Project Manipulation

Scenario: User adds a project with name and project type.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    Then the project exists in the list of projects.

Scenario: User adds a project with name, project type and time period.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    Then the project exists in the list of projects.
		
Scenario: User edits a project
    Given a project with name "TestProject", project type INTERNAL.
    When the user changes the name to "Test".
    Then the name of the project is test.

Scenario: Removes a project
    Given a project with name "TestProject", project type INTERNAL.
    When the user removes the project.
    Then the project doesn't exist in the list projects.
    
Scenario: search for a project
	Given a user want to find a project with the name "projectname"
	
Scenario: User adds illegal project time period
	When the user adds a project with name "TestProject" and project type INTERNAL and time period "2021-05-05 08:00" to "2019-05-05 08:00" then exception thrown

Scenario: User creates duplicate project
	Given a project with name "TestProject", project type INTERNAL.
	When user adds a project with name "TestProject", project type INTERNAL then exception is thrown
	