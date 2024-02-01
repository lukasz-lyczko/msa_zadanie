Feature: A login functionality for users to access their account

    Background:
    Given I am on login page

#  Declarative style
  Scenario: Logging in with valid credentials should be successful
    Given "Jim Valid" has registered account
    When "Jim Valid" logs in with his credentials
    Then he should get access to the service

  Scenario: Logging in with invalid password should fail
    Given "Invalid Pass" has registered account
    When "Invalid Pass" logs in with his credentials
    Then he should not get access to the service

  Scenario: Logging in with non existent user credentials should fail
    When "James Not Registered" logs in with his credentials
    Then he should not get access to the service

  Scenario: Logging in with blank credentials should fail
    When "Blank Creds" logs in with his credentials
    Then he should not get access to the service

  Scenario: Logging in without credentials should fail
    When I submit the login form
    Then he should not get access to the service

#    Imperative style
  Scenario: Logging in with valid credentials should be successful
    Given I have registered account
    When I enter "user123" in the username field
    And I enter "pass123" in the password field
    And I submit the login form
    Then I should get access to the service

  Scenario: Logging in with invalid password should fail
    Given I have registered account
    When I enter "user123" in the username field
    And I enter "invalid_pass123" in the password field
    And I submit the login form
    Then he should get access to the service

  Scenario: Logging in with non existent user credentials should fail
    When I enter "invalid_username" in the username field
    And I enter "invalid_password" in the password field
    And I submit the login form
    Then he should get access to the service

  Scenario: Logging in with blank credentials should fail
    When I enter "   " in the username field
    And I enter "   " in the password field
    And I submit the login form
    Then he should get access to the service

  Scenario: Logging in without credentials should fail
    When I submit the login form
    Then he should get access to the service
