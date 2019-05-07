Feature: Project Manipulation

Scenario: User adds a project with name and project type.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    Then the project exists in the list of projects.

Scenario: User adds a project with name, project type and time period.
    When the user adds a project with name "TestProject" and project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    Then the project exists in the list of projects.
		
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

		