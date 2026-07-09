Feature: More Cars - Start Page

  Scenario: Start page contains node type sections
    When the user visits the start page
    Then the page should contain the following sections
      | Core Data     |
      | Motorsport    |
      | Press & Media |
      | Gaming        |

  Scenario: Each section contains at least one node type
    When the user visits the start page
    Then each section should contain at least one node type
