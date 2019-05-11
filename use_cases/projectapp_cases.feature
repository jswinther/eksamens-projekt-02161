Feature: Project Manipulation
Scenario: User wants to get a project
		When user searches for project with name "testProject" project is found
Scenario: User want to set a project
		When user set project with index 0 and project
		Then the project is set
		When user set project with name "testProject1" and project
		Then project is set with name "testProject1"
Scenario: user wants to set or get an activity
		When user want to set an activity with name "testactivity1" to a project "projectTest1"
		Then the activity is set with the name "testactivity1" to project "projectTest1"
		When user wants to set an activity with index 0 to project of name "test2"
		Then activity is set to index 0 to project of name "test2"

