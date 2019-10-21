Feature: Balance random generation

   Method to make the game easier to play by avoiding the repetition of pieces created.

   Scenario: A new piece is laid down
   Given We create a new piece
   When We put the piece on the board
   Then We substract 6 points of weight to the type of piece laid and add 1 point of weight to all other types.
