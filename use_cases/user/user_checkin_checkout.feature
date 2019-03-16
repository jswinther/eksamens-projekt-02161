Feature: Check in and Check out.
	Description: User check in and out.
	Actor: User
	
Scenario: Checks in successfully
	Given User is not checked in
	When User checks in
	Then User is checked in
	
Scenario: Checks out successfully
	Given User is checked in
	When User checks out
	Then User is checked out
	
