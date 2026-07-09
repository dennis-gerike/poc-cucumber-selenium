Feature: More Cars - Start Page

  Scenario: Start page contains a list of all node types
    When the user visits the start page
    Then the page should contain the following sections
      | Core Data     |
      | Motorsport    |
      | Press & Media |
      | Gaming        |
