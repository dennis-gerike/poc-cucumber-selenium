# POC Cucumber Selenium

This project is a Proof of Concept (POC) for automated web testing using Cucumber, Selenium, and JUnit 5.

## Prerequisites

To run the tests, you need the following installed on your system:

- **Java 17** or higher
- **Maven 3.6** or higher
- **Google Chrome** browser

## Installation

1. Clone the repository (if not already done).
2. Navigate to the project root directory.
3. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

## Running Tests

To execute all Selenium tests, run the following command:

```bash
mvn test
```

The tests are configured to run in **headless mode** by default.

## Test Reports

After running the tests, you can find the execution reports in:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JUnit XML Report: `target/surefire-reports/TEST-net.more_cars.RunCucumberTest.xml`

## Project Structure

- `src/test/java/net/more_cars/RunCucumberTest.java`: JUnit Suite runner for Cucumber.
- `src/test/java/net/more_cars/driver/DriverManager.java`: Manages the Selenium WebDriver instance.
- `src/test/java/net/more_cars/steps/`: Contains Step Definition classes.
- `src/test/resources/features/`: Contains Cucumber feature files.
- `src/test/resources/cucumber.properties`: Configuration for Cucumber.
