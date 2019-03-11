Feature: Create, Edit and Delete Plan.
	Description: User Creates, Edits and Deletes Plan.
	Actor: User
	
Scenario: Creates plan successfully
	Given User is logged in
	When User creates plan with name "Eksamens Projekt 1", with start date "11-03-2019" 
		 and time "15:00" and end date "05-05-2019" and time "23:59", with participants 
		 "Jonathan Winther", "Nicholas Dyhre", "Marcus Kristensen" og "Sebastian Vestrup"
	Then Plan is created with the participants and they are notified.
	
Scenario: Edits plan successfully
	Given User is logged in
	And Plan exists with parameters name "Eksamens Projekt 1", with start date "11-03-2019" 
		 and time "15:00" and end date "05-05-2019" and time "23:59", with participants 
		 "Jonathan Winther", "Nicholas Dyhre", "Marcus Kristensen" og "Sebastian Vestrup"
	When User edits plan to end at "06-05-2019" 
	Then Plan is changed with end date "06-05-2019"
	
Scenario: Deletes plan successfully
	Given User is logged in
	And Plan exists with parameters name "Eksamens Projekt 1", with start date "11-03-2019" 
		 and time "15:00" and end date "05-05-2019" and time "23:59", with participants 
		 "Jonathan Winther", "Nicholas Dyhre", "Marcus Kristensen" og "Sebastian Vestrup"
	When User deletes plan
	Then Plan is deleted.
	
