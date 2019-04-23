Feature: Activity Manipulation

Background:
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    
Scenario:
	Given projectmanager wants to find free user in time period "2019-05-05 08:00" to "2019-08-05 23:59"
	
Scenario:
Given projectmanager wants to find unavailable user in time period "2019-05-05 08:00" to "2019-08-05 23:59"

Scenario:
	Given user wants to find free user "name"
