Feature: New random piece

   Every 30 seconds a new piece appears on the board that goes down at a higher speed.

   Scenario: Choose play mode
   Given User start the aplication
   When User see the configuration screen
   And User choose new piece mode
   Then User play the game 
   And New piece appears every 30 seconds

