package net.more_cars.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.more_cars.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleSearchSteps(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
        this.wait = driverManager.getWait();
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.get("https://www.google.com");
        // Handle cookie consent if it appears
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//div[contains(text(), 'Accept all') or contains(text(), 'Alle akzeptieren')]]")));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Cookie consent not found or already accepted.");
        }
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("the search results should contain {string}")
    public void the_search_results_should_contain(String expectedText) {
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(expectedText), "Page source should contain " + expectedText);
    }
}
