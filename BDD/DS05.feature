Feature: Score ranking

   At the end of the game a screen appears with the 10 best scores.

   Scenario: Show and save puntuation at the end of the game
   Given User start the aplication
   And Start the game
   When User lose
   And The game ends
   Then User see a score screen ranking
   And The puntuation is stored at a database


