# Java-Data-Structures

This repository contains foundational data structure frameworks implemented from first principles using Java as the language. Then, it will explore standard tree structures and graph algorithms and game theoretic algorithms built using the foundation. 

Foundational data structure frameworks covered: 
- ArrayList;
- LinkedList;
- Stack (LIFO);
- Queue (FIFO presented as a circular array); 
- Deque;
- HashMap;  
- HashSet.

These data structure frameworks form the foundation to more elaborated structures in the following sections. 

Trees covered: 
- AVL tree; 
- Binary Search Tree;
- Binary Heap.

These structures lay out the foundation for advanced graph data structures and algorithms such as Dijkstra. 

Graph data structure frameworks covered: 
- Trie: finding the unique path for a word from the root to its end;
- Adjacency list graph;
- Adjacency matrix graph: matrix representation of a graph where rows are the starting vertices and columns are the destination. This framework determines whether a neighboring vertex is reachable from a starting vertex with bit encoding in binary. Such framework are used in unweighted graph algorithms via object collaboration.

Unweighted graph algorithms covered: 
- Breadth first search (BFS): Explores vertex depth by level to find the shortest unweighted path between a starting vertex and its destination.
- Depth first search (DFS): Used in subsequent graph algorithms.
- Connected components: Counts the number of graph components by tracking the number of DFS calls. 
- Undirected cycle detection: Detects cycles by tracking visited vertices;
- Directed cycle detection: Detects cycles by tracking active DFS calls via a recursion stack;
- Topological sort.

Weighted graph algorithms covered: 
- Dijkstra: Finds the shortest weighted path. A shortest path in BFS is not necessarily a shortest path in Dijkstra.
- Matrix representation of a weighted graph (backbone architecture): A square matrix where each cell adjacencyMatrix[sourceIndex][destinationIndex] represents an edge's weight. Based on a new architecture: removing or adding a graph component changes the matrix's size instead of yielding free indices.
- Other weighted graph algorithms remain to be determined.

Game AI algorithms covered: 
- Game tree: Representation of a sequence of possible moves by a player with leaves as payoffs;
- Minimax: Finds the best decision that a specific player (MAX or MIN) can make. Works bottom-up;
- Alpha-Beta pruning: Optimizer-like algorithm that eliminates unnecessary branches of a Minimax tree;
- Tic-tac-toe AI: deterministic AI programmed to never lose a Tic-Tac-Toe game. Scans available moves and finds the best one via alpha-beta pruning.

Such game AIs are implemented using game theory principles and mechanisms:
- Prisoner's dilemma to find opponent's possible replies to a specific move; 
- Dominance principle to find the strategy with the best payoff for a player;
- Best Response and Nash equilibrium.
