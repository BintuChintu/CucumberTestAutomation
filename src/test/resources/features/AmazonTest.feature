##
#
#author : RaviKumar Mogulluru
#
##

Feature: Amazon product search


Background:
 Given Launch the aplication

@sanity
Scenario: Validate google search with keyword
	Given User is on google home page
	When User enters keyword in search
  Then Print all the search results
  Then Click on the link which takes you to the amazon login page
  And Click on all buttons on search and select Electronics
  And Search for product
  Then Apply the filter of range Rs minprice to maxprice
  And Validate all the products shown in the range of Rs minprice to maxprice
  Then Print all the products whose rating is 5.0
  Then user tries to add item to the wishlist
