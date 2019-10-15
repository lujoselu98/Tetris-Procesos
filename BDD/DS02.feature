Feature: New random piece

   Every 30 seconds a new piece appears on the board that goes down at a higher speed.

   Scenario: Choose play mode
   Given We start the aplication
   When We see the configuration screen
   And Choose new piece mode
   Then We play the game 
   And Every 30 seconds a new piece appears
