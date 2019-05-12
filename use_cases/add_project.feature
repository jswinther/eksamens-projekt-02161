Feature: Developer creates and adds a projects.
	As a developer
	I want to create a project for my company

Scenario: User adds a project with name, project type, project manager and timeperiod.
    When the user adds a project with name "TestProject" and project type INTERNAL, project manager named "Shiloh Richmond" and time period "2019-01-01 11:11" to "2019-02-02 11:11".
    Then the project exists in the list of projects.
    
Scenario: User adds project with name
	When the user adds a project with name "TestProject"
	Then a project named "TestProject" exists
		
Scenario: User adds project with project type
	When the user adds a project with type INTERNAL
	Then a project of that type exists
		
Scenario: User adds project with project manager
	When the user adds a project with a project manager named "Shiloh Richmond" 
	Then a project with a project manager named "Shiloh Richmond" exists
		
Scenario: User adds a project with a time period
	When the user adds a project with a time period "2019-05-05 08:00" to "2019-08-05 23:59".
	Then a project exists with a startDate "2019-05-05 08:00" 
	And an end date "2019-08-05 23:59".
	
Scenario: Duplicate project name
	When user creates project named "testProject" then throw exception
	
Scenario: User adds illegal project time period
	When the user adds a project with name "TestProject" and project type INTERNAL.
	And time period "2021-05-05 08:00" to "2019-05-05 08:00".
		
Scenario: User creates duplicate project
	Given a project with name "TestProject1", project type INTERNAL.
	And time period "2019-05-05 08:00" to "2019-08-05 08:00".
	When user adds a project with name "TestProject", project type INTERNAL.
	And time period "2019-05-05 08:00" to "2019-08-05 08:00".