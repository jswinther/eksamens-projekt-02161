Feature: Developer edits event
	As a developer
	I want to edit an event
	Because it was incorrectly registered

Scenario: User wants to add Event with Activity and Message to Schedule
	When User adds event to Schedule with Activity and Message
	Then event is displayed with activity and message
	
Scenario: User wants to add Event with Activity to Schedule
	When User adds event to Schedule with Activity
	Then event is displayed with activity
	
Scenario: User wants to add Event with Message to Schedule
	When User adds event to Schedule with Message
	Then event is displayed with message
	
Scenario: User wants to add Event to Schedule
	When User adds event to Schedule
	Then event is displayed