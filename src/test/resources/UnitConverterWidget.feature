Feature: Unit Converter Widget
  Rule: The Unit Converter should convert valid unit combinations
    Scenario Outline: The Unit Converter should convert valid unit combinations
      Given the user is on the Homepage
      When the user wants to convert <from_value> "<from_unit>" to "<target_unit>"
      Then the unit converter should display "<expected_result>" in the result field

      Examples:
        | from_unit | from_value | target_unit | expected_result |
        | kmh       | 250.0      | mph         | 155.34          |
        | mph       | 155.0      | kmh         | 249.45          |
        | PS        | 200.0      | kW          | 147.10          |
