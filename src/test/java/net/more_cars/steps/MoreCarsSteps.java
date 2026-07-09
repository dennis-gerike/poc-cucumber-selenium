package net.more_cars.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.more_cars.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoreCarsSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MoreCarsSteps(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
        this.wait = driverManager.getWait();
    }

    @When("the user visits the start page")
    public void the_user_visits_the_start_page() {
        driver.get("https://more-cars.net");
    }

    @Then("the page should contain the following sections")
    public void the_page_should_contain_the_following_sections(List<String> sectionNames) {
        for (String sectionName : sectionNames) {
            By locator = By.xpath("//section//h2[contains(., '" + sectionName + "')]");
            assertTrue(driver.findElements(locator).size() > 0, "Missing section: " + sectionName);
        }
    }
}
