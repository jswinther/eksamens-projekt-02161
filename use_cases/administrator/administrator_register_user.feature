Feature: Administrator registers user.
	Description: Administrator logs in with their account details and registers a user with the users own
	account details which includes full name, username, password and student email.
	Actor: User

Background: Administrator is logged in.
 	Given I exist in the list of administrators
 	And I navigate to the login page
 	When I submit username and password
 	Then I should be logged in 

Scenario: Registers user successfully.
	Given Administrator is logged in with username "admin" and password "admin"
	When Administrator enters the users account details and registers the user
	Then Administrator has registered the user
	
Scenario: Registers user unsuccessfully.
	Given Administrator is logged in with username "admin" and password "admin"
	And Administrator has previously registered a user with the same account details.
	When Administrator enters the users account details and registers the user
	Then Administrator gets the error message "user is already registered"