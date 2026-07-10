# POC Cucumber Selenium

This project is a Proof of Concept (POC) for automated web testing using Cucumber, Selenium, and JUnit 5.

## Prerequisites

To run the tests, you need the following installed on your system:

- **Java 21** (LTS)
- **Maven 3.9** or higher
- **Google Chrome** browser
- **Docker** and **Docker Compose** (for Jenkins setup)

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

## Jenkins Setup

A Jenkins environment is provided via Docker Compose for CI/CD purposes.

### Starting Jenkins

1. Navigate to the `jenkins` directory:
   ```bash
   cd jenkins
   ```
2. Start the container:
   ```bash
   docker compose up -d
   ```

### Accessing Jenkins

- **Web UI**: [http://localhost:8080](http://localhost:8080)
- **Agent Port**: `50010` (mapped from Jenkins internal `50000`)

### Initial Admin Password

To retrieve the initial admin password, run:

```bash
docker logs jenkins-lts
```

Or check the file in the persisted volume:

```bash
cat jenkins_home/secrets/initialAdminPassword
```

### Features

- **Modern Infrastructure**: Uses Jenkins 2.568.1 (JDK 21) and custom-built `poc-selenium-test-runner` (JDK 21 + Chrome).
- **Persistence**: Data is persisted in the `jenkins/jenkins_home` directory on the host.
- **Docker-out-of-Docker (DooD)**: The Docker socket is mounted, allowing Jenkins to run Docker commands for building
  and testing containers.
- **Selenium Tests Job**: A Pipeline job named `selenium-tests` is pre-configured to run tests using the modern test runner.

### Running Selenium Tests in Jenkins

1. Access Jenkins at [http://localhost:8080](http://localhost:8080).
2. Login (if required).
3. Select the `selenium-tests` job.
4. Click **Build Now**.

The job will:

1. Start a `poc-selenium-test-runner` container (modern Maven 3.9 + JDK 21 + Chrome).
2. Use the Jenkins Docker DSL for execution.
3. Clone the repository from the local `/workspace` mount.
3. Execute `mvn test`.
4. Archive JUnit reports and Cucumber HTML reports.
5. Generate **Cucumber Reports** and **HTML Reports** directly in Jenkins for easy consumption.

### Viewing Test Results

After a build completes, you can view the results in several ways:

1. **Cucumber Reports**: Click the "Cucumber Reports" link in the left sidebar of the job to see a detailed, graphical breakdown of test results.
2. **HTML Report**: Click the "Cucumber HTML Report" link in the left sidebar to view the standard Cucumber HTML report directly in the browser.
3. **JUnit Results**: Click the "Test Result" link to see the standard JUnit-style report.

## Project Structure

- `src/test/java/net/more_cars/RunCucumberTest.java`: JUnit Suite runner for Cucumber.
- `src/test/java/net/more_cars/driver/DriverManager.java`: Manages the Selenium WebDriver instance.
- `src/test/java/net/more_cars/steps/`: Contains Step Definition classes.
- `src/test/resources/features/`: Contains Cucumber feature files.
- `src/test/resources/cucumber.properties`: Configuration for Cucumber.
- `jenkins/`: Contains Jenkins Docker Compose configuration and persisted data.
