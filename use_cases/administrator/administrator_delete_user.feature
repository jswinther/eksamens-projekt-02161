Feature: Administrator deletes user.
	Description: Administrator logs in with their account details and registers a user with the users own
	account details which includes full name, username, password and student email.
	Actor: User

Scenario: Registers user successfully.
	Given Administrator is logged in with username "admin" and password "admin"
	When Administrator chooses user with username "username" and deletes that user
	Then Administrator user with username "username" no longer exists
	
