Feature: Balance random generation

   Method to make the game easier to play by avoiding the repetition of pieces created.

   Scenario: A new piece is laid down
   Given User start the aplication
   When User start the game
   Then Each Piece appears following and uniform distribution
