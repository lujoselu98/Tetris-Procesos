Feature: Fantasy mode

   If a line is made, when it disappears, the colors of the rest of the blocks change.

   Scenario: Choose play mode
   Given We start the aplication
   When We see the configuration screen
   And Choose Fantasy mode
   Then We play the game 
   And The colors change