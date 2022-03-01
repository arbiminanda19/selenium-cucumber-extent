# selenium-cucumber-extent

Tools Used: Maven, Java, Selenium, Cucumber, Gherkin, JUnit, Extent Report

This is automation test for [this url](https://skillacademy.com/)

# Setting up and running tests

* Open project as Maven Project in Eclipse, Intellij, or other IDE 
* In this repository, the chrome driver for Google Chrome version 97 is already available. 
	* In case your Google Chrome version is different, you can download chrome driver from [this url](https://chromedriver.chromium.org/downloads) according to your version. Place chrome driver in the following folder of directory
```
{YourProjectPath}/src/test/resources/drivers/chromedriver.exe
```
* In this repository, there is a SearchSkillAcademy.feature file that consists of:
	* Test Case 1: User search spesific keyword
	* Test Case 2: User search spesific keyword and change amount of searching result in one page
	* Test Case 3: User search spesific keyword with implement price filter
	* Test Case 4: User search spesific keyword with implement sort result
	* Test Case 5: User search spesific keyword with implement duration filter
* You can run this testing by running TestRunner file using JUnit, the directory of TestRunner is:
```
{YourProjectPath}/src/test/java/TestRunner
```
* After run this automation test, yout can see generated report file (json, xml, html) on:
```
{YourProjectPath}/target
```