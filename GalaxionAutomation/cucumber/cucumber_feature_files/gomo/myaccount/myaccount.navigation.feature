Feature: Customer navigates around MyGoMo

  Scenario: My Account > Customer navigates around MyAccount
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Alerts" from the homepage
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Bills" from the homepage
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Payments" from the homepage
    And I select option "My GoMo Home" from the nav bar
    And I select option "Account Details" from the homepage
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Profile" from the homepage
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Bills" from the homepage
    And I select option "My Alerts" from the nav bar
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Bills" from the homepage
    And I select option "My Payments" from the nav bar
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Bills" from the homepage
    And I select option "Account Details" from the nav bar
    And I select option "My GoMo Home" from the nav bar
    And I select option "My Bills" from the homepage
    And I select option "My Profile" from the nav bar
    And I select option "My GoMo Home" from the nav bar
    And I select option "Contact Us" from the homepage
    Then My correct page is displayed