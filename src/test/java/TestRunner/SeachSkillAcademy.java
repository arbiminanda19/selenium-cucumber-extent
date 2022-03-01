package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Feature\\SearchSkillAcademy.feature", glue="StepDefinitions", monochrome=true, tags="@Test4",  
plugin={"pretty", "html:target/HtmlReports.html", "json:target/JSONReports/report.json", "junit:target/JUnitReports/report.xml"})

public class SeachSkillAcademy {

}
