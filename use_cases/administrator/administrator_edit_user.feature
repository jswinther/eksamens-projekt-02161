Feature: Administrator edits user.
	Description: Administrator logs in with their account details and registers a user with the users own
	account details which includes full name, username, password and student email.
	Actor: User

Scenario: Edits user successfully.
	Given Administrator is logged in with username "admin" and password "admin"
	When Administrator chooses an existing user with name username "username" and changes it to "newUsername"
	Then Administrator there is now a user with username "newUsername"
	