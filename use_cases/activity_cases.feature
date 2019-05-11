Feature: Activity Manipulation

Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".
	
Scenario: User is added to an activity
	When user named "Shiloh Richmond" is added to activity
	Then user named "Shiloh RIchmond" exists in list
	
Scenario: duplicate user is added to activity
	When another user named "Shiloh Richmond" is added to activity
	Then throw exception
	
Scenario: User is removed from an activity
	When user named "Shiloh Richmond" is removed from activity
	Then user named "Shiloh RIchmond" no longer exists in list
	
Scenario: Duplicate activity name
	When user creates activity named "testActivity" then throw exception

Scenario: set estimated hours for activity
	When user sets estimated hours 30 to an activity then the activity estimated hours should be 30
	
Scenario: create invalid activity name
	When user creates activity named " " then exception is thrown
	
Scenario: user creates activity with invalid time period
	When user creates activity with time period "2021-05-05 08:00" to "2019-08-05 23:59", then exception is thrown
	
Scenario: set invalid estimated hours for activity
	When user sets estimated hours to "a" then throw exception
	
Scenario: user edits an activity
	When an activity exists named "testActivity" and user changes activity name to "activityTest"
	
Scenario: user incorrectly edits an activity
	When an activity exists that is named "testActivity" and a user changes the name to "testActivity"
	Then send error message