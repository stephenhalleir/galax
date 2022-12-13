Feature: Customer performs some actions on MyGoMo	    
	
  Scenario: My Account > My Profile: Customer checks that contact details and billing address are correct
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    Then my contact details are correctly displayed
    And my billing address details are correctly displayed
    And I log out of MyAccount
