package net.more_cars.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.more_cars.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header div a")));
    }

    @Then("the page should contain the following sections")
    public void the_page_should_contain_the_following_sections(List<String> sectionNames) {
        for (String sectionName : sectionNames) {
            By locator = By.xpath("//section//h2[contains(., '" + sectionName + "')]");
            assertTrue(driver.findElements(locator).size() > 0, "Missing section: " + sectionName);
        }
    }

    @Then("each section should contain at least one node type")
    public void each_section_should_contain_at_least_one_node_type() {
        List<WebElement> sections = driver.findElements(By.cssSelector("section[data-testid='node-type-group-section']"));
        for (WebElement section : sections) {
            assertTrue(section.findElements(By.cssSelector("ul li")).size() > 0);
        }
    }

    @Then("there should be a {string} section")
    public void there_should_be_a_section(String sectionName) {
        String effectiveSectionName = sectionName;
        if (sectionName.equals("Latest Cars")) {
            effectiveSectionName = "Latest Additions";
        }
        By locator = By.xpath("//section//h2[contains(., '" + effectiveSectionName + "')]");
        assertTrue(driver.findElements(locator).size() > 0, "Missing section: " + sectionName);
    }

    @Then("the {string} list should contain {int} entries")
    public void the_list_should_contain_entries(String sectionName, int count) {
        String testId = "";
        if (sectionName.equals("Latest Cars")) {
            testId = "latest-additions-section";
        }
        By locator = By.cssSelector("section[data-testid='" + testId + "'] ul li");
        List<WebElement> entries = driver.findElements(locator);
        assertEquals(count, entries.size(), "Expected " + count + " entries but found " + entries.size());
    }
}
