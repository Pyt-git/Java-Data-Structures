State expansion definition: process of generating child states from the current game state. 
Diagram: Current state -> generate all legal moves -> apply each move -> create child states. 

Example with Tic-Tac-Toe: 
Suppose current board is: 

 X | O | X
-----------
 O | X |
-----------
   |   | O

And it's X's turn. Empty squares at positions (2,3), (3,1) and (3,2)
Possible child boards: 

Move 1 - 
 X | O | X
-----------
 O | X | X
-----------
   |   | O

Move 2 - 
 X | O | X
-----------
 O | X |
-----------
 X |   | O

Move 3 - 
 X | O | X
-----------
 O | X |
-----------
   | X | O

Then boards generated from Move 1, Move 2 and Move 3 are child states by state expansion. 

Second minimax invariant: The children of a game-state node are exactly the states reachable by one legal move from that state. 

References: 

