Feature: Activity Manipulation

Background:
    Given a project with an activity exists.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    
Scenario:
	Given projectmanager wants to find free user in time period "2019-05-05 08:00" to "2019-08-05 23:59"
	
Scenario:
	Given projectmanager wants to find unavailable user in time period "2019-05-05 08:00" to "2019-08-05 23:59"

Scenario:
	Given user wants to find free user "name"
	
Scenario:
	Given user named "Shiloh Richmond" is added to activity
	Then user exists in list
	
Scenario:
	Given user named "Shiloh Richmond" is removed from activity
	Then user no longer exists in list
	

