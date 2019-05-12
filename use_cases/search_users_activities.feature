#Jonathan
Feature: Developer searches through all users to see how many activities they are assigned in a given period
	As a developer
	I want to see how many activities are assigned to each user In a given period
	So that I can pick those who have the least to do

Scenario: find user activitylist
	Given user exists
	Then find users activitylist
			
Scenario: find free user
	When user is free, keep them on the list