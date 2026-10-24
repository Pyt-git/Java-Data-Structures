// Directed cycle detection graph algorithm invariants:

// 1. Every discovered vertex is marked as visited. 
// 2. recursionStack contains every active DFS call. 
// 3. A directed cycle exists if DFS reaches a neighbor currently in the stack. 

// Example: 

// Suppose graph is: 
