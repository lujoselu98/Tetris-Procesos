Feature: Fantasy mode

   If a line is made, when it disappears, the colors of the rest of the blocks change.

   Scenario: Fantasy mode one line
   Given User start the aplication
   When User see the configuration screen
   And User choose Fantasy mode
   And User make only one line
   Then Pieces that are already fixed change its color all to the same 

   Scenario: Fantasy mode more than one line
   Given User start the aplication
   When User see the configuration screen
   And User choose Fantasy mode
   And User make more than one line
   Then Pieces that are already fixed change its color to a random one 
