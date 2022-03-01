package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.junit.Assert.*;

public class SearchSkillAcademy {

	WebDriver driver = null;
	Random rand = new Random();
	String keyword = "SMA";
	int amount = 0;
	
	@Given("User is on skillacademy homepage")
	public void skilacademy_homepage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://skillacademy.com/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Skill Academy Logo']"))
	    ));
	}
	
	@When("User fill search keyword")
	public void fill_search_keyword() {
		driver.findElement(By.xpath("//input[@data-testid='search-input-field']")).sendKeys(keyword);
	}
	
	@And("User click search button")
	public void click_search() {
		driver.findElement(By.xpath("//div[@data-testid='search-icon']")).click();
	}
	
	@Then("User see the search results that match with keyword")
	public void see_search_results() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'" + keyword + "')]"))
		));
	    List<WebElement> match_first_element = driver.findElements(By.xpath("//h2[contains(text(),'" + keyword + "')]"));
	    List<WebElement> match_first_element_upper_case = driver.findElements(By.xpath("//h2[contains(text(),'" + keyword.toUpperCase() + "')]"));
	    if (match_first_element.isEmpty() && match_first_element_upper_case.isEmpty()) {
	    	driver.findElement(By.xpath("//h2[contains(text(),'" + keyword.toLowerCase() + "')]"));
	    }
	    driver.close();
	}
	
	@When("User choose amount of result")
	public void choose_amount() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='pagination-per-page']"))
		));
		driver.findElement(By.xpath("//div[@data-testid='pagination-per-page']")).click();
		List<Integer> listLanguage = Arrays.asList(10,20,50,100);
		amount = listLanguage.get(rand.nextInt(listLanguage.size()));
		if (amount == 10) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'10')]")).click();
		}
		if (amount == 20) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'20')]")).click();
		}
		if (amount == 50) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'50')]")).click();
		}
		if (amount == 100) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'100')]")).click();
		}
	}
	
	@Then("User see amount of search result match with amount that has been chosen")
	public void amount_result_match() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='course-image']"))
		));
		List<WebElement> course_element = driver.findElements(By.xpath("//div[@data-testid='course-image']"));
		assertEquals(amount, course_element.size());
	    driver.close();
	}
}