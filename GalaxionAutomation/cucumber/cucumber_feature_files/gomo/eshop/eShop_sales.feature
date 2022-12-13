Feature: Customers place orders via the GoMo eShop

  Scenario: New Acquisition: New customer orders 1 offer
    Given I visit eshop
    When I select 1 offers
    And I complete customer details
    And I make a valid payment
    Then order number should be displayed
	
	@regression @omit
  Scenario: Cross-sell: Existing customer orders 1 additional offer
    Given I have completed registration
    And I visit eshop
    And I log in
    When I select 1 offers
    And I accept T&Cs
    And I pay using existing card details
    Then order number should be displayed

  Scenario: New Acquisition: New customer orders 2 offers
    Given I visit eshop
    When I select 2 offers
    And I complete customer details
    And I make a valid payment
    Then order number should be displayed

  Scenario: New Acquisition: New customer orders 3 offers
    Given I visit eshop
    When I select 3 offers
    And I complete customer details
    And I make a valid payment
    Then order number should be displayed

  Scenario: New Acquisition: New customer orders 4 offers
    Given I visit eshop
    When I select 4 offers
    And I complete customer details
    And I make a valid payment
    Then order number should be displayed