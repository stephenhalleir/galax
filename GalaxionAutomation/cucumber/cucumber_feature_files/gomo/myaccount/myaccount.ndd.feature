Feature: MyAccount: Customer manages their NDD settings

	Scenario: My Account > My Profile: Customer updates NDD setting
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    And I click the right arrow icon
    And I click the right arrow icon
    And I randomly change my NDD setting
    Then correct NDD setting is displayed
    And I log out of MyAccount


	Scenario: My Account > My Profile: Customer checks NDD
    Given I visit the MyAccount url
    And I log into MyAccount
    And I accept cookies
    When I select option "My Profile" from the homepage
    And I click the right arrow icon
    And I click the right arrow icon
    Then correct NDD setting is displayed
    And I log out of MyAccount