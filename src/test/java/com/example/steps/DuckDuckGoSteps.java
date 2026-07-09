package com.example.steps;

import com.example.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuckDuckGoSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DuckDuckGoSteps(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
        this.wait = driverManager.getWait();
    }

    @Given("I am on the DuckDuckGo search page")
    public void i_am_on_the_duck_duck_go_search_page() {
        driver.get("https://duckduckgo.com");
    }

    @When("I search for {string} on DuckDuckGo")
    public void i_search_for_on_duck_duck_go(String query) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("the DuckDuckGo search results should contain {string}")
    public void the_duck_duck_go_search_results_should_contain(String expectedText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("react-layout")));
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(expectedText), "Page source should contain " + expectedText);
    }
}
