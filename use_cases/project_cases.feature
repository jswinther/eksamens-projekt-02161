Feature: Project Manipulation

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

Scenario: User edits a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user changes the name to "Test" .
    Then the name of the project is test.

Scenario: Removes a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user removes the project.
    Then the project doesn't exist in the list projects.
    
Scenario: search for a project
	Given a user want to find a project with the name "projectname"
	
Scenario: User adds illegal project time period
	When the user adds a project with name "TestProject" and project type INTERNAL.
	And time period "2021-05-05 08:00" to "2019-05-05 08:00".
		
Scenario: User creates duplicate project
	Given a project with name "TestProject1", project type INTERNAL.
	And time period "2019-05-05 08:00" to "2019-08-05 08:00".
	When user adds a project with name "TestProject", project type INTERNAL.
	And time period "2019-05-05 08:00" to "2019-08-05 08:00".
		
Scenario: registers hours
	Then user hours is registered "2019-03-03 13:30" to "2019-03-03 13:40"
		
Scenario: searchtext or searchlist is null
	When name is not null, and list is not null, return search
	When name is null and list is not null, cast error
	When name is not null and list is null, cast error
	When name and list is null, cast error
		
Scenario: find user activitylist
	Given user exists
	Then find users activitylist
		
Scenario: adds activity
	Then adds activity

Scenario: remove activity
	Then remove activity
		
Scenario: find free user
	When user is free, keep them on the list
	When user is not free, remove them from the list
	When user overlaps into the beginning, remove them from list
	When user overlaps over the end, remove them from list
		
Scenario: search user
	When searching for user, returns user
	When searching for user, but user dont exist
	When looking for user, and user exists

		
Scenario: A User wants to register hours
	Given a project that has at least one activity which has at least one user
	When user registers hours
	Then an event is added to user schedule
	
	
Scenario: A User wants to edit hours
	Given a project that has at least one activity which has at least one user
	When user registers hours
	Then an event is added to user schedule
	When user edits hours
	Then the event is changed
	
Scenario: Duplicate project name
	When user creates project named "testProject" then throw exception
	