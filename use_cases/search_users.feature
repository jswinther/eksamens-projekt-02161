#Nicholas
Feature: Developer searches through the list of users
	As as developer
	I want to search through the list of users
	So that I can find the one I need

Scenario: search user
	When searching for user "Shiloh Richmond"
	Then user "Shiloh Richmond" is found
	When searching for user invalid user "blabla"
	Then no user is found by the name "blabla"
	When looking for user "Shiloh Richmond" and user exists
	Then User "Shiloh Richmond" is found in the userlist