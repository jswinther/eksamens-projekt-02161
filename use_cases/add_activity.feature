#Sebastian
Feature: Developer creates and adds activities to existing projects.
	As a developer
	I want to create and add activities
	So that people can know in advance and start scheduling
	
Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".
	
Scenario: adds activity
	Then adds activity
	
Scenario: Duplicate activity name
	When user creates activity named "testActivity" then throw exception

Scenario: create invalid activity name
	When user creates activity named " " then exception is thrown
	
Scenario: user creates activity with invalid time period
	When user creates activity with time period "2021-05-05 08:00" to "2019-08-05 23:59", then exception is thrown
	
	