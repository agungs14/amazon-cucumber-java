Feature: Registry and gifting
  As a user amazon, i want to registry and gifting
  So that i can use this site: amazon.com

  Scenario: registry and gifting
    Given i open the website amazon.com for registry and gifting
    And  I go to Registry page
    And make sure i am at page registry and gifting
    When i find registry with name john and gift list type: birthday gift
    And make sure the list result for john
    When i search criteria by range month from january 2021 to april 2021
    Then make sure the search result is an accordance with the specific time range