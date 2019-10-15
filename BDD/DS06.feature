Feature: End world mode

   Every 50 seconds two lines of the game screen are deleted.

   Scenario: Choose play mode
   Given We start the aplication
   When We see the configuration screen
   And Choose End world mode
   Then We play the game 
   And Every 50 seconds the screen gets smaller