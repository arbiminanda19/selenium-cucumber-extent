@All
Feature: Search in SkillAcademy Web
	
	@Test1
	Scenario: User search spesific keyword
		Given User is on skillacademy homepage
		When User fill search keyword
		And User click search button
		Then User see the search results that match with keyword