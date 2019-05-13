#Sebastian
Feature: Developer edits an activity
	As a developer
	I want to edit an activity
	Because there is new information

Background:
   	Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".

Scenario: set invalid estimated hours for activity
	When user sets estimated hours to "a" 
	Then activity is not added
	
Scenario: user edits an activity
	When an activity exists named "testActivity" and user changes activity name to "activityTest"
	Then activity is named "activityTest"
		
Scenario: user incorrectly edits an activity
	When an activity exists that is named "testActivity" and a user changes the name to "testActivity"
	Then activity name is still "testActivity"
	
Scenario: set estimated hours for activity
	When user sets estimated hours 30 to an activity
	Then the activity estimated hours should be 30