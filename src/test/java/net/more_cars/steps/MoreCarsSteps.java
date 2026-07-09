package net.more_cars.steps;

import io.cucumber.java.en.When;
import net.more_cars.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoreCarsSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MoreCarsSteps(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
        this.wait = driverManager.getWait();
    }

    @When("the user visits the start page")
    public void when() {
        driver.get("https://more-cars.net");
    }
}
