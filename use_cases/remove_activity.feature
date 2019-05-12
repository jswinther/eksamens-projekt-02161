#Sebastian
Feature: Developer removes activity from project
	As a developer
	I want to remove an activity
	Because it was incorrectly added
	
Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".
    
Scenario: remove activity
	Then remove activity