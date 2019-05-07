Feature: Activity Manipulation

Background:
    Given a project with an activity exists.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
	
Scenario:
	When user named "Shiloh Richmond" is added to activity
	Then user named "Shiloh RIchmond" exists in list
	
Scenario:
	Given user named "Shiloh Richmond" is part of an activity
	When user named "Shiloh Richmond" is removed from activity
	Then user named "Shiloh RIchmond" no longer exists in list
	

