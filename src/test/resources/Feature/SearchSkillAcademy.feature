@All
Feature: Search in SkillAcademy Web
	
	@Test1
	Scenario: User search spesific keyword
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		Then User see the search results that match with keyword
		
	@Test2	
	Scenario: User search spesific keyword and change amount of searching result in one page
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		When User choose amount of result
		Then User see amount of search result match with amount that has been chosen
		
	@Test3
	Scenario: User search spesific keyword with implement price filter
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		When User choose spesific price filter
		Then User see the search results that match the price filter