package de.dennisgerike.bdd.selenium.poc;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UnitConverterWidgetTest {
    private final WebDriver driver = new ChromeDriver();

    @Given("the user is on the Homepage")
    public void given() {
        driver.get("https://more-cars.net/");
    }

    @When("the user wants to convert {double} {string} to {string}")
    public void when(Double fromValue, String fromUnit, String toUnit) {
        Select from = new Select(driver.findElement(By.id("unit_converter_from_unit")));
        from.selectByValue(fromUnit);

        Select to = new Select(driver.findElement(By.id("unit_converter_to_unit")));
        to.selectByValue(toUnit);

        WebElement element = driver.findElement(By.name("from_value"));
        element.sendKeys(fromValue.toString());

        driver.findElement(By.tagName("form")).submit();
    }

    @Then("the unit converter should display {string} in the result field")
    public void then(String expectedResult) {
        WebElement element = driver.findElement(By.name("result"));
        Assertions.assertEquals(expectedResult, element.getAttribute("value"));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}