Feature: End world mode

   Every 50 seconds two lines of the game screen are deleted.

   Scenario: Choose play mode
   Given User start the aplication
   When User see the configuration screen
   And User choose End world mode
   And User start the game
   Then Every 50 seconds the screen gets two lines smaller
