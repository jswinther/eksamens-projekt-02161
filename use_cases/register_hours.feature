Feature: Register hours
	As a developer
	I want to register hours
	So that others can see when I am working, sick and on holiday
	
Background: There already exists a project with an activity
	Given there exists a project in the list of projects
	And that project has an activity
	
Scenario: Developer registers hours
	When developer registers work from "2019-05-13 08:00" to "2019-05-13 16:00"
	Then the event exists in the activity registered hours
	And the event exists in the schedule of the developer
	
Scenario: Developer registers sick
	When developer registers sick from "2019-05-13 08:00" to "2019-05-13 16:00" with message "sick"
	Then the event exists in the schedule of the developer
	And the message says "sick"
	
Scenario: Developer registers holiday
	When developer registers sick from "2019-05-13 08:00" to "2019-05-13 16:00" with message "holiday"
	Then the event exists in the schedule of the developer
	And the message says "holiday"

Scenario: A User wants to register hours
	Given a project that has at least one activity which has at least one user
	When user registers hours
	Then an event is added to user schedule
	
Scenario: registers hours
	Then user hours is registered "2019-03-03 13:30" to "2019-03-03 13:40"