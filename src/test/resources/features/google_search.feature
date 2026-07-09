Feature: Google Search

  Scenario: Search for Cucumber
    Given I am on the Google search page
    When I search for "Cucumber"
    Then the search results should contain "Cucumber"

  Scenario: Search for Selenium
    Given I am on the Google search page
    When I search for "Selenium"
    Then the search results should contain "Selenium"
