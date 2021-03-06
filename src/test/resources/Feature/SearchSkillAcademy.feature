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
		
	@Test4
	Scenario: User search spesific keyword with implement sort result
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		When User choose spesific sort type
		Then User see the new search result page loaded with sort type chosen
		
	@Test5	
	Scenario: User search spesific keyword with implement duration filter
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		When User choose spesific duration filter
		Then User see the new search result page loaded with duration filter