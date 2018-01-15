Feature: Create Merchant
  Scenario: Create merchant successfully
    Given I have a merchant account in the FastMoney Bank with name "Al" "Alsen" and CVR "65980983"
    When I ask DTU-Pay to create me a merchant account with name "Big Al" "AS" and CVR "65980983"
    Then The account was created sucessfully

  Scenario: Create merchant account, when one already exists in DTU-Pay
    Given I have a merchant account in the FastMoney Bank with name "Al" "Blsen" and CVR "61201241"
    When I ask DTU-Pay to create me a merchant account with name "Big Bl" "AS" and CVR "61201241"
    And I again ask DTU-Pay to create me a merchant account with name "Big Bl" "AS" and CVR "61201241"
    Then I receive a "An error occurred with the account" error from the system

  Scenario: Create merchant account, CVR-number is invalid
    Given I ask DTU-Pay to create me a merchant account with name "Not Real" "AS" and CVR "23afiluk"
    Then I receive a "Invalid input" error from the system

  Scenario: Create merchant account, CVR-number is invalid
    Given I ask DTU-Pay to create me a merchant account with name "Not Real" "AS" and CVR "1234567"
    Then I receive a "Invalid input" error from the system

  Scenario: Create merchant account, CVR-number is invalid
    Given I ask DTU-Pay to create me a merchant account with name "Not Real" "AS" and CVR "123456789"
    Then I receive a "Invalid input" error from the system

  Scenario: Create merchant account, name is invalid
    Given  I ask DTU-Pay to create me a merchant account with name "au48esx57zyw4" "notreal239408" and CVR "65980983"
    Then I receive a "Invalid input" error from the system

  Scenario: Create merchant account, no bank account
    Given That I do not have a merchant account in the FastMoney Bank
    When I ask DTU-Pay to create me a merchant account with name "Hej" "AS" and CVR "65980983"
    Then I receive a "An error occurred with the account" error from the system
