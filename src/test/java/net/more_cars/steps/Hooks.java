package net.more_cars.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.more_cars.driver.DriverManager;

public class Hooks {
    private final DriverManager driverManager;

    public Hooks(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Before
    public void setup() {
        // Driver is lazily initialized in DriverManager.getDriver()
        // but we can call it here if we want to ensure it's started before steps
        driverManager.getDriver();
    }

    @After
    public void tearDown() {
        driverManager.quitDriver();
    }
}
