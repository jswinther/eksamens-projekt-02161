#Sebastian
Feature: Developer removes user from activity
	As a developer
	I want to remove a user from my activity
	Because they were incorrectly added

Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".
	
Scenario: User is removed from an activity
	When user named "Shiloh Richmond" is removed from activity
	Then user named "Shiloh RIchmond" no longer exists in list