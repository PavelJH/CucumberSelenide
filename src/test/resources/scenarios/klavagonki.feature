Feature: Bot for klavaGonki webSite

  Background: I am on main page webSite
    Given Open webSite "https://klavogonki.ru/go?type=normal"

    Scenario: Bot start game and pui in field himself text
      When Begin game
      And Wait start game
      And Put highlight word in a cycle
      Then Fixing game is over and symbols in min more then 1500


  Scenario: Bot start game and pui in field himself text 3000
    When Begin game
    And Wait start game
    And Put highlight word in a cycle
    Then Fixing game is over and symbols in min more then 3500
