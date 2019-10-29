Feature: Choose color

   Scenario: See configuration 
   Given User choose aplication
   When Aplication start
   Then Configuration screen apears

   Configuration screen where you can choose diferents colors.

   Scenario: Choose color
   Given User start the aplication
   When User see the configuration screen
   And User choose one color option
   Then User play the game with the color configuration that he choose

