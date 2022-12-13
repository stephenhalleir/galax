Feature: Customer manages their email address

	@regression
	Scenario: MyAccount > My Profile: Update Email Address
		Given I visit the MyAccount url
	    And I log into MyAccount
	    And I accept cookies
	    When I select option "My Profile" from the homepage
	    And I select Update Email
	    And I enter and confirm new email address
	    And I save my updated email
	    And I click my update email verification deep link