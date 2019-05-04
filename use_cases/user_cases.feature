Feature: User manipulation

Background: A Project with acitvities exists.
	Given a project named "Airplane Navigation Software" with 25 active activities.

Scenario: Add a project manager to project
	When user is assigned to project as project manager
	Then the user is now assigned to project as project manager.

	