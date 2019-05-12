#Nicholas
Feature: Developer removes existing projects.
	As a developer
	I want to remove a project
	Because it was mistakenly added

Scenario: Removes a project
    Given a project with name "TestProject", project type INTERNAL.
    And time period "2019-05-05 08:00" to "2019-08-05 23:59".
    When the user removes the project.
    Then the project doesn't exist in the list projects.