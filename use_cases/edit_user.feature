Feature: Developer edits user details
	As a developer
	I want to change my name
	Because it was incorrectly spelled

Scenario: Update user details for a given user with a name longer than four characters.
	Given a user.
	When user changes name to "Firstname Lastname"
	Then name is changed to "Firstname Lastname"

Scenario: Update user details for a given user with a name equal to four characters.
	Given a user.
	When user changes name to "Mark"
	Then name is changed to "Mark"
	
Scenario: Update user details for a given user with a name less than four characters.
	Given a user.
	When user changes name to "Bo"
	Then name is changed to "Bo"