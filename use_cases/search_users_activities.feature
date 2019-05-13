#Jonathan
Feature: Developer searches through all users to see how many activities they are assigned in a given period
	As a developer
	I want to see how many activities are assigned to each user In a given period
	So that I can pick those who have the least to do

Scenario: A user wants to see how many activities they are assigned
	Given a user with an activity assigned
	Then a user searches for how many activities they are assigned and it returns 1 activity.
			
Scenario: A user wants to see who has the least activities at a given point
	Given that a user has an activity assigned
	Then it says an activity is assigned to that user