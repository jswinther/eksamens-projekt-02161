#Sebastian
Feature: Developer adds user to assist on activity
	As a developer
	I want to add another user to my activity
	So that we can work on it together
	
Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".

Scenario: User is added to an activity
	When user named "Shiloh Richmond" is added to activity
	Then user named "Shiloh RIchmond" exists in list
	
Scenario: duplicate user is added to activity
	When another user named "Shiloh Richmond" is added to activity
	Then throw exception
	