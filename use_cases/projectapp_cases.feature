Feature: Project Manipulation
Scenario: User wants to get a project
		When user searches for project with name "testProject" project is found
Scenario: User want to set a project
		When user set project with index 0 and project
		Then the project is set
		When user set project with name "testProject1" and project
		Then project is set with name "testProject1"