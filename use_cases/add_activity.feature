#Sebastian
Feature: Developer creates and adds activities to existing projects.
	As a developer
	I want to create and add activities
	So that people can know in advance and start scheduling
	
Background:
    Given a project with an activity named "testActivity" exists with time period "2019-05-05 08:00" to "2019-08-05 23:59".
	
Scenario: adds activity
	When an activity named "test" is added to a project
	Then an activity named "test" exists in project
	
Scenario: Duplicate activity name
	When user creates activity named "testActivity"
	Then activity named "testActivity" is not added to project

Scenario: create invalid activity name
	When user creates activity named " " to project
	Then activity is not added to project	
	
Scenario: user creates activity with invalid time period
	When user creates activity with time period "2021-05-05 08:00" to "2019-08-05 23:59"
	Then activity is not added to project