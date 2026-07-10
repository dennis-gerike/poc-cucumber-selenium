Feature: More Cars - Start Page - Latest Additions
  As a returning visitor
  I want to see a list with the latest added cars
  So I can see what I missed since I last visited the page

  @UI
  Scenario: Start page contains a list with the latest cars
    When the user visits the start page
    Then there should be a "Latest Cars" section

  @misc
  Scenario: Latest cars list contains 50 entries
    When the user visits the start page
    Then the "Latest Cars" list should contain 50 entries
