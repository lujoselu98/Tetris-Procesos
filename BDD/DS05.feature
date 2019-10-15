Feature: Score ranking

   At the end of the game a screen appears with the 10 best scores.

   Scenario: End the game
   Given We start the aplication
   And Start the game
   When The game ends
   Then We see a score screen ranking