Feature: MyAccount: Customer manages their contact preferences

@regression
  Scenario: My Account > My Profile: Customer checks that contact preferences are correct
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    And I click the right arrow icon
    Then my contact preferences (existing customer) are correctly displayed
    And my contact preferences (cancelled customer) are correctly displayed
    And I log out of MyAccount
        
Scenario: My Account > My Profile: Customer updates contact preferences for ACTIVE_CUSTOMER
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    And I click the right arrow icon
    And I select ACTIVE_CUSTOMER
    And I toggle all permissions
    And I save my permission changes
    Then my contact preferences (existing customer) are correctly displayed
    And I log out of MyAccount

Scenario: My Account > My Profile: Customer updates contact preferences for NO_LONGER_CUSTOMER
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    And I click the right arrow icon
    And I select NO_LONGER_CUSTOMER
    And I toggle all permissions
    And I save my permission changes
    And my contact preferences (cancelled customer) are correctly displayed
    And I log out of MyAccount