#Nicholas
Feature: Developer wants to search for a project
	As a developer
	I want to search through the list of projects
	So that I can find the one I need to look at

Scenario: search for a project
	Given a user want to find a project with the name "projectname"
	Then the user finds project with the name "projectname"