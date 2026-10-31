Definition: Representation of all possible sequences of a move in a game. 
Components: nodes are decision points (game states), edges are actions taken by a player and leaf nodes represent final outcomes with associated payoffs. 

Example: 

         start
        /     \
    Attack   Defend

Adding opponent and tree alternates between players and payoffs indicate how good a move is for a player. 

Example: 

         start
        /     \
    Attack   Defend
    /   \     /  \
   X    Y    X    Y

Pick one path and tree means: start -> Attack -> opponent chooses move X -> payoff = +3 (one complete play of the game). 
Game tree invariant: A game tree is a tree whose nodes are game states, whose edges are actions, and whose leaves represent payoffs. 

#References: 
1. SAKOS, Iosif; "Lecture 13 - Foundations of Extensive Form Games"; November 6, 2025. 
2. KROER, Christian; "Lecture Note 6: Extensive-Form Games"; February 6, 2022. 
3. National Taiwan University; "ECON 4037 Chapter 2 The Extensive Form". 
