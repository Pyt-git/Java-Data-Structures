Alpha-Beta pruning serves as an optimized version of plain minimax. Equivalent relations: Alpha-beta pruning <-> minimax ~ Dijkstra <-> DFS. 
Optimization by avoiding searching branches that do not affect the final decision, reducing the number of nodes evaluated. 

Core example for pruning: 
Suppose MAX has already found 10 and discovers 3, a value enforced by the opponent. Since MAX will choose 10 over 3, the rest of the 3 branch is superfluous. 
Therefore, we stop searching after node 3. 

Alpha and Beta are bounds for pruning where: 
- Alpha represents the best value MAX has found so far (MAX lower bound) with initial value -infinity; 
- Beta represents the best value MIN has found so far (MIN upper bound) with initial value +infinity;

Invariant: if alpha >= beta, then searching stops (cutoff) while maintaining bounds. 

Example: 
Consider game tree: 

```
      MAX
    /     \
  MIN     MIN
 /   \   /   \
5    6  4     X
```
with unknown X. 
1. explore left subtree: MIN(5,6) = 5 => alpha = 5.
2. explore right subtree: first child is 4 => beta = 4. 
Comparing yields alpha >= beta (5>=4)

Final tree: 
```
      MAX
    /     \
  MIN     MIN
 /   \   /   
5    6  4     
```
with X pruned. 

Invariant: Once a branch is known to be worse than an already available alternative, further exploration cannot affect the final Minimax decision. 

Reference: KROER, Christian; "Lecture Note 6: Extensive-Form Games"; February 6, 2022. 
