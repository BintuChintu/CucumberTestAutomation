#author : RaviKumar Mogulluru

Feature: Validate login 

#Background: Background test
#Given test the background keyword

Scenario Outline:: Validate login
	Given User is on swag home page
	When User enter <userName> and <password>
	Then Validate login
	Examples:
  |     userName   |   password    |
	|     student    | Password123  |

#@smoke
#Scenario: I want to test test1
#	Given some other precondition
#	When I complete action
#	Then some other action



#@regression
#	Scenario: Validate Parameterisation
#	Given User is swag home page
#	When User enter username and password
#	Then Validate login

#@regression
#	Scenario Outline: Validate Parameterisation
#	Given User is swag home page
#	When User enter <userName> and <password>
#	Then Validate login
#	Examples:
#	|     userName   |   password    |
#	| standard_user  | secret_sauce  |
	
#@regression
#	Scenario Outline: Validate Parameterisation
#	Given User is swag home page
#	When User enters login credentials
#	| username         | password      |
#	| standard_user    | secret_sauce  |
#	Then Validate login	