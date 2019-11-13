Feature: Speed up the piece

   There is a button that allows me to download the pieces faster.
	
   Scenario: Show button
   Given User start the aplication	
   When User start the game from the configuration screen
   Then Soft down button is shown

   Scenario: Click button
   Given User start the aplication	
   And User start the game from the configuration screen
   When User click the soft down button
   Then The piece go one block down
