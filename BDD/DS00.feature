Feature: Fix bugs

   Scenario: Check lose condition
   Given User Start the aplication
   And User Start the game
   When New piece can not appear
   Then User lose the game

