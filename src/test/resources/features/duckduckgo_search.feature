Feature: DuckDuckGo Search

  Scenario: Search for Cucumber on DuckDuckGo
    Given I am on the DuckDuckGo search page
    When I search for "Cucumber Testing" on DuckDuckGo
    Then the DuckDuckGo search results should contain "Cucumber"
