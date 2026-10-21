// Depth first search graph algorithm: 
// Invariants: 

// DFS uses stack (LIFO). 
// All discovered vertices are marked visited while being pushed onto the stack. 
// Stack contains unprocessed vertices. 
// Complexity: O(V + E) since every vertex is visited at most once. 

// Example with LIFO: let A discover B and C, B discover D and E. 
// And B is discovered before C while D is discovered before E. 

// Stack when DFS reaches A: [A], when DFS reaches B and C: [B, C] and when DFS reaches D and E: [D, E]. 
// From [B, C]: pop C then pop B. From [D, E]: pop E then pop D. 

// Traversal order: [A, C, B, E, D] 

