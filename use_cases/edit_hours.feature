Feature: Developer edits registered hours
	As a developer
	I want to edit an event
	So that it is correctly registered
	Because it was incorrectly registered
	
Scenario: A User wants to edit hours
	Given a project that has at least one activity which has at least one user
	When user registers hours
	Then an event is added to user schedule
	When user edits hours
	Then the event is changed