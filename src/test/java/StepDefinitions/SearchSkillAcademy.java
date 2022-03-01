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
	String keyword = "SMP";
	int amount = 0;
	int minPrice = 0;
	int maxPrice = 0;
	int sortType = 0;
	int duration = 0;
	
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
		List<Integer> listAmount = Arrays.asList(10,20,50,100);
		amount = listAmount.get(rand.nextInt(listAmount.size()));
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
	
	@When("User choose spesific price filter")
	public void choose_price() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Harga')]"))
		));
		driver.findElement(By.xpath("//div[contains(text(),'Harga')]")).click();
		List<Integer> listPrice = Arrays.asList(1,2,3,4);
		amount = listPrice.get(rand.nextInt(listPrice.size()));
		if (amount == 1) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'<100.000')]")).click();
			maxPrice = 99000;
		}
		if (amount == 2) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'100.000 - 149.999')]")).click();
			minPrice = 100000;
			maxPrice = 149000;
		}
		if (amount == 3) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'150.000 - 200.000')]")).click();
			minPrice = 150000;
			maxPrice = 200000;
		}
		if (amount == 4) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'>200.000')]")).click();
			minPrice = 200001;
			maxPrice = 99999999;
		}
	}
	
	@Then("User see the search results that match the price filter")
	public void price_result_match() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-testid='price-final']"))
		));
		String displayedPrice = driver.findElement(By.xpath("//p[@data-testid='price-final']")).getText();
		displayedPrice = displayedPrice.replace("Rp ","");
		displayedPrice = displayedPrice.replace(".","");
		assertTrue(minPrice <= Integer.parseInt(displayedPrice) && Integer.parseInt(displayedPrice) <= maxPrice);
	    driver.close();
	}
	
	@When("User choose spesific sort type")
	public void choose_sort() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Pilih')]"))
		));
		driver.findElement(By.xpath("//div[contains(text(),'Pilih')]")).click();
		List<Integer> listSort = Arrays.asList(1,2,4,5);
		sortType = listSort.get(rand.nextInt(listSort.size()));
		if (sortType == 1) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'Rating Tertinggi')]")).click();
		}
		if (sortType == 2) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'Review Terbanyak')]")).click();
		}
		if (sortType == 4) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'Harga Terendah')]")).click();
		}
		if (sortType == 5) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'Harga Tertinggi')]")).click();
		}
	}
	
	@Then("User see the new search result page loaded with sort type chosen")
	public void sort_match() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-testid='price-final']"))
		));
		List<WebElement> displayedPrice = driver.findElements(By.xpath("//p[@data-testid='price-final']"));
		String firstPrice = displayedPrice.get(0).getText();
		firstPrice = firstPrice.replace("Rp ","");
		firstPrice = firstPrice.replace(".","");
		String secondPrice = displayedPrice.get(0).getText();
		secondPrice = secondPrice.replace("Rp ","");
		secondPrice = secondPrice.replace(".","");
		if (sortType == 1) {
			List<WebElement> rating = driver.findElements(By.xpath("//span[@data-testid='rating-text']"));
			float firstRating = Float.parseFloat(rating.get(0).getText());
			float secondRating = Float.parseFloat(rating.get(1).getText());
			assertTrue(firstRating >= secondRating);
		}
		if (sortType == 2) {
			List<WebElement> review = driver.findElements(By.xpath("//span[@data-testid='rating-count']"));
			String firstReview = review.get(0).getText();
			firstReview = firstReview.replaceAll("[^0-9]", "");
			String secondReview = review.get(1).getText();
			secondReview = secondReview.replaceAll("[^0-9]", "");
			assertTrue(Float.parseFloat(firstReview) >= Float.parseFloat(secondReview));
		}
		if (sortType == 4) {
			assertTrue(Integer.parseInt(firstPrice) <= Integer.parseInt(secondPrice));
		}
		if (sortType == 5) {
			assertTrue(Integer.parseInt(firstPrice) >= Integer.parseInt(secondPrice));
		}
		driver.close();
	}
	
	@When("User choose spesific duration filter")
	public void choose_duration() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Durasi')]"))
		));
		driver.findElement(By.xpath("//div[contains(text(),'Durasi')]")).click();
		List<Integer> listDuration = Arrays.asList(1,2,3,4);
		duration = listDuration.get(rand.nextInt(listDuration.size()));
		if (duration == 1) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'<1 jam')]")).click();
		}
		if (duration == 2) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'1-2 jam')]")).click();
		}
		if (duration == 3) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'2-3 jam')]")).click();
		}
		if (duration == 4) {
			driver.findElement(By.xpath("//div[@tabindex='-1'][contains(text(),'>3 jam')]")).click();
		}
	}
	
	@Then("User see the new search result page loaded with duration filter")
	public void verify_duration_filter_page() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.or(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='course-image']"))
		));
		String url = driver.getCurrentUrl();
		char durationInURL = url.charAt(url.length()-1);
		assertTrue(duration == Integer.parseInt(String.valueOf(durationInURL)));
		driver.close();
	}
	
}