Feature: Project Manipulation
	
Scenario: searchtext or searchlist is null
	When name is not null, and list is not null, return search
	When name is null and list is not null, cast error
	When name is not null and list is null, cast error
	When name and list is null, cast error
		

		


		

	
	

	

	